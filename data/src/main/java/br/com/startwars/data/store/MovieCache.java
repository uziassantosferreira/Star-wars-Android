package br.com.startwars.data.store;


import br.com.startwars.data.entity.MovieEntity;
import br.com.starwars.domain.models.Movie;
import io.reactivex.Single;

/**
 * Created by Uzias on 18/01/17.
 */

public interface MovieCache extends CacheStore {

    Single<MovieEntity> save(MovieEntity movieEntity, String query);

    Single<MovieEntity> getMovie(String name);

    Single<MovieEntity> save(MovieEntity movieEntity);
}
