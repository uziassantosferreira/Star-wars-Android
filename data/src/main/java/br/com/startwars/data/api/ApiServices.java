package br.com.startwars.data.api;

import br.com.startwars.data.entity.FilmApiEntity;
import br.com.startwars.data.entity.PeopleApiEntity;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Uzias on 18/01/17.
 */

public interface ApiServices {

    @GET("people/{id}/")
    Single<Response<PeopleApiEntity>> getPeople(@Path("id") long id);

    @GET("films/{id}/")
    Single<Response<FilmApiEntity>> getFilm(@Path("id") long id);

}
