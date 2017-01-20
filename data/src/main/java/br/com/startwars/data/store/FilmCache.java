package br.com.startwars.data.store;

import br.com.startwars.data.entity.FilmEntity;
import io.reactivex.Single;

/**
 * Created by Uzias on 18/01/17.
 */

public interface FilmCache extends CacheStore {

    Single<FilmEntity> getByUrl(String url);


    Single<FilmEntity> save(FilmEntity filmEntity);


}
