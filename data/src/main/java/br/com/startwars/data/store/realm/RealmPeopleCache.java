package br.com.startwars.data.store.realm;

import java.util.ArrayList;
import java.util.List;

import br.com.startwars.data.entity.PeopleApiEntity;
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
    public Single<PeopleEntity> getByUrl(String url) {
        return Single.create(new SingleOnSubscribe<PeopleEntity>() {
            @Override
            public void subscribe(SingleEmitter<PeopleEntity> e) throws Exception {
                PeopleEntity result = getRealm().where(PeopleEntity.class).equalTo("url", url).findFirst();
                closeRealm();
                if (result == null){
                    e.onError(new Throwable());
                }else{
                    e.onSuccess(result);
                }


            }
        });
    }

    @Override
    public Single<List<PeopleEntity>> getList() {
        return Single.create(new SingleOnSubscribe<List<PeopleEntity>>() {
            @Override
            public void subscribe(SingleEmitter<List<PeopleEntity>> e) throws Exception {
                List<PeopleEntity> result = getRealm().where(PeopleEntity.class).findAll();
                e.onSuccess(result);
            }
        });
    }

    @Override
    public Single<PeopleEntity> save(PeopleEntity peopleEntity) {
        return Single.create(new SingleOnSubscribe<PeopleEntity>() {
            @Override
            public void subscribe(SingleEmitter<PeopleEntity> e) throws Exception {
                getRealm().beginTransaction();
                getRealm().copyToRealm(peopleEntity);
                getRealm().commitTransaction();
                closeRealm();
                e.onSuccess(peopleEntity);

            }
        });
    }

    @Override
    protected Class getEntityClass() {
        return PeopleEntity.class;
    }
}
