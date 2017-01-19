package br.com.startwars.data.store.realm;

import java.util.List;

import br.com.startwars.data.entity.PeopleEntity;
import br.com.startwars.data.store.PeopleCache;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by Uzias on 18/01/17.
 */

public class RealmPeopleCache extends RealmCache implements PeopleCache {

    @Override
    public Single<PeopleEntity> getPeopleEntity() {
        return null;
    }

    @Override
    public Single<List<PeopleEntity>> getListPeople() {
        return Single.create(new SingleOnSubscribe<List<PeopleEntity>>() {
            @Override
            public void subscribe(SingleEmitter<List<PeopleEntity>> e) throws Exception {
                List<PeopleEntity> result = getRealm().where(PeopleEntity.class).findAll();
                closeRealm();
                e.onSuccess(result);
            }
        });
    }

    @Override
    protected Class getEntityClass() {
        return PeopleEntity.class;
    }
}
