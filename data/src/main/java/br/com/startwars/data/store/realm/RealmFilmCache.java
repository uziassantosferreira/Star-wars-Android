package br.com.startwars.data.store.realm;

import br.com.startwars.data.entity.FilmEntity;
import br.com.startwars.data.entity.PeopleEntity;
import br.com.startwars.data.store.FilmCache;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.realm.RealmQuery;

/**
 * Created by Uzias on 18/01/17.
 */

public class RealmFilmCache extends RealmCache implements FilmCache {

    @Override
    public Single<FilmEntity> getByUrl(String url) {
        return Single.create(new SingleOnSubscribe<FilmEntity>() {
            @Override
            public void subscribe(SingleEmitter<FilmEntity> e) throws Exception {
                RealmQuery<FilmEntity> realmQuery = getRealm()
                        .where(FilmEntity.class)
                        .equalTo("url", url);

                Long count = realmQuery.count();
                FilmEntity filmEntity = null;
                if (count >= 1){
                    filmEntity = getRealm().copyFromRealm(realmQuery.findFirst());
                }
                closeRealm();
                if (filmEntity == null){
                    e.onError(new Throwable());
                }else{
                    e.onSuccess(filmEntity);
                }
            }
        });
    }



    @Override
    public Single<FilmEntity> save(FilmEntity filmEntity) {
        return Single.create(new SingleOnSubscribe<FilmEntity>() {
            @Override
            public void subscribe(SingleEmitter<FilmEntity> e) throws Exception {
                getRealm().beginTransaction();
                getRealm().copyToRealmOrUpdate(filmEntity);
                getRealm().commitTransaction();
                closeRealm();
                e.onSuccess(filmEntity);
            }
        });
    }

    @Override
    protected Class getEntityClass() {
        return PeopleEntity.class;
    }
}
