package br.com.startwars.data.store.realm;



import br.com.startwars.data.entity.MovieEntity;
import br.com.startwars.data.store.MovieCache;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by Uzias on 18/01/17.
 */

public class RealmMovieCache extends RealmCache implements MovieCache {

    @Override
    public Single<MovieEntity> save(MovieEntity movieEntity, String query) {
        return Single.create(new SingleOnSubscribe<MovieEntity>() {
            @Override
            public void subscribe(SingleEmitter<MovieEntity> e) throws Exception {
                movieEntity.setName(query);
                getRealm().beginTransaction();
                getRealm().copyToRealmOrUpdate(movieEntity);
                getRealm().commitTransaction();
                closeRealm();
                e.onSuccess(movieEntity);
            }
        });
    }

    @Override
    public Single<MovieEntity> getMovie(String name) {
        return Single.create(new SingleOnSubscribe<MovieEntity>() {
            @Override
            public void subscribe(SingleEmitter<MovieEntity> e) throws Exception {
                MovieEntity realmResult = getRealm()
                        .where(MovieEntity.class)
                        .equalTo("name", name)
                        .findFirst();
                MovieEntity movieEntity = getRealm().copyFromRealm(realmResult);
                closeRealm();
                if (movieEntity == null){
                    e.onError(new Throwable());
                }else{
                    e.onSuccess(movieEntity);
                }
            }
        });
    }

    @Override
    protected Class getEntityClass() {
        return MovieEntity.class;
    }
}
