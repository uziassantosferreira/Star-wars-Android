package br.com.startwars.data.store.realm;

import android.text.TextUtils;

import java.util.List;

import br.com.startwars.data.entity.PeopleEntity;
import br.com.startwars.data.store.PeopleCache;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Uzias on 18/01/17.
 */

public class RealmPeopleCache extends RealmCache implements PeopleCache {

    @Override
    public Single<PeopleEntity> getByUrl(String url) {
        return Single.create(new SingleOnSubscribe<PeopleEntity>() {
            @Override
            public void subscribe(SingleEmitter<PeopleEntity> e) throws Exception {
                RealmQuery<PeopleEntity> realmQuery = getRealm()
                        .where(PeopleEntity.class)
                        .equalTo("url", url);

                long realmResult = realmQuery.count();

                PeopleEntity peopleEntity = null;
                if (realmResult >= 1){
                    peopleEntity = getRealm().copyFromRealm(realmQuery.findFirst());
                }
                closeRealm();
                if (peopleEntity == null || TextUtils.isEmpty(peopleEntity.getName())){
                    e.onError(new Throwable());
                }else{
                    e.onSuccess(peopleEntity);
                }
            }
        });
    }

    @Override
    public Single<List<PeopleEntity>> getList() {
        return Single.create(new SingleOnSubscribe<List<PeopleEntity>>() {
            @Override
            public void subscribe(SingleEmitter<List<PeopleEntity>> e) throws Exception {
                RealmResults<PeopleEntity> realmResults = getRealm()
                        .where(PeopleEntity.class)
                        .findAll();
                List<PeopleEntity> peopleEntities = getRealm().copyFromRealm(realmResults);
                closeRealm();
                e.onSuccess(peopleEntities);
            }
        });
    }

    @Override
    public Single<PeopleEntity> save(PeopleEntity peopleEntity) {
        return Single.create(new SingleOnSubscribe<PeopleEntity>() {
            @Override
            public void subscribe(SingleEmitter<PeopleEntity> e) throws Exception {
                getRealm().beginTransaction();
                getRealm().copyToRealmOrUpdate(peopleEntity);
                getRealm().commitTransaction();
                closeRealm();
                e.onSuccess(peopleEntity);
            }
        });
    }

    @Override
    public Single<PeopleEntity> save(String url) {
        return Single.create(new SingleOnSubscribe<PeopleEntity>() {
            @Override
            public void subscribe(SingleEmitter<PeopleEntity> e) throws Exception {
                RealmQuery<PeopleEntity> realmQuery = getRealm().where(PeopleEntity.class).equalTo("url", url);
                long realmResult = realmQuery.count();

                PeopleEntity peopleEntity;
                if (realmResult >= 1){
                    peopleEntity = getRealm().copyFromRealm(realmQuery.findFirst());
                }else{
                    peopleEntity = new PeopleEntity();
                    peopleEntity.setUrl(url);
                    getRealm().beginTransaction();
                    getRealm().copyToRealm(peopleEntity);
                    getRealm().commitTransaction();
                }

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
