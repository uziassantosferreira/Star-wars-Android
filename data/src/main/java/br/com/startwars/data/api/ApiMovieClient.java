package br.com.startwars.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import br.com.startwars.data.Utils;
import br.com.startwars.data.entity.FilmEntity;
import br.com.startwars.data.entity.MovieApiEntity;
import br.com.startwars.data.entity.MovieEntity;
import br.com.startwars.data.entity.PeopleEntity;
import br.com.startwars.data.mappers.FilmEntityMapper;
import br.com.startwars.data.mappers.Mapper;
import br.com.startwars.data.mappers.MovieEntityMapper;
import br.com.startwars.data.mappers.PeopleEntityMapper;
import br.com.startwars.data.store.FilmCache;
import br.com.startwars.data.store.MovieCache;
import br.com.startwars.data.store.PeopleCache;
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

public class ApiMovieClient {

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

    public static Single<MovieEntity> getMovie(String query, MovieCache movieCache) {
        return getApiServices().getMovie(query, urlProvider.getApiKey())
                .compose(ApiClient.mapResponse(new MovieEntityMapper(urlProvider.getMovieBaseUrlImage())))
                .compose(ApiClient.verifyRequestError())
                .flatMap(movieEntity -> movieCache.save(movieEntity, query));
    }


    public static void initUrls(UrlProvider provider) {
        ApiMovieClient.urlProvider = provider;
    }

    public interface UrlProvider {
        String getApiEndpoint();
        String getApiKey();
        String getMovieBaseUrlImage();
    }

}