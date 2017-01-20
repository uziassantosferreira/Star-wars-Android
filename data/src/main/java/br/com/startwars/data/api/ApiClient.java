package br.com.startwars.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import br.com.startwars.data.entity.PeopleEntity;
import br.com.startwars.data.mappers.Mapper;
import br.com.startwars.data.mappers.PeopleEntityMapper;
import br.com.startwars.data.store.PeopleCache;
import br.com.startwars.data.store.realm.RealmPeopleCache;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Uzias on 18/01/17.
 */

public class ApiClient {

    private static ApiServices apiServices;
    private static UrlProvider urlProvider;
    private static Gson gson;

    private static ApiServices getApiServices() {
        if (apiServices == null) {
            buildApiServices();
        }
        return apiServices;
    }

    private static void buildApiServices() {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .baseUrl(urlProvider.getApiEndpoint())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);
    }

    public static Single<PeopleEntity> getPeople(String id, PeopleCache peopleCache) {
        return getApiServices().getPeople(1)
                .compose(mapResponse(new PeopleEntityMapper()))
                .compose(verifyRequestError()).flatMap(peopleEntity -> peopleCache.save(peopleEntity));
    }

    private static SingleTransformer<Response<Void>, Response<Void>> mapResponse() {
        return upstream -> upstream.doOnSuccess(response -> {
            if (!response.isSuccessful()) {
                throw new RequestException(response.code(), response.errorBody());
            }
        });
    }

    private static <T, U> SingleTransformer<Response<T>, U> mapResponse(Mapper<T, U> mapper) {
        return upstream -> upstream.map(response -> {
            if (response.isSuccessful()) {
                return mapper.transform(response.body());
            }
            throw new RequestException(response.code(), response.errorBody());
        });
    }

    private static <T> SingleTransformer<T, T> verifyRequestError() {
        return upstream -> upstream.onErrorResumeNext(throwable -> {
            if (throwable instanceof RequestException) {
                return Single.error(throwable);
            }
            return Single.error(new RequestException(throwable));
        });
    }

    public static void initUrls(UrlProvider provider) {
        ApiClient.urlProvider = provider;
    }

    public interface UrlProvider {
        String getApiEndpoint();
    }

}