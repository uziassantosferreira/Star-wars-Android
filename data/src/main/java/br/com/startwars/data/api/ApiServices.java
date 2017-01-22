package br.com.startwars.data.api;

import br.com.startwars.data.entity.FilmApiEntity;
import br.com.startwars.data.entity.PeopleApiEntity;
import br.com.startwars.data.entity.ResponseMovieApiEntity;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Uzias on 18/01/17.
 */

public interface ApiServices {

    @GET("people/{id}/")
    Single<Response<PeopleApiEntity>> getPeople(@Path("id") long id);

    @GET("films/{id}/")
    Single<Response<FilmApiEntity>> getFilm(@Path("id") long id);

    @GET("search/movie")
    Single<Response<ResponseMovieApiEntity>> getMovie(@Query("query") String query, @Query("api_key") String apiKey);

}
