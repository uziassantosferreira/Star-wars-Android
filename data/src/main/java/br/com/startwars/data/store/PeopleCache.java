package br.com.startwars.data.store;

import java.util.List;

import br.com.startwars.data.entity.PeopleApiEntity;
import br.com.startwars.data.entity.PeopleEntity;
import io.reactivex.Single;

/**
 * Created by Uzias on 18/01/17.
 */

public interface PeopleCache extends CacheStore {

    Single<PeopleEntity> getByUrl(String url);

    Single<List<PeopleEntity>> getList();

    Single<PeopleEntity> save(PeopleEntity peopleApiEntity);


}
