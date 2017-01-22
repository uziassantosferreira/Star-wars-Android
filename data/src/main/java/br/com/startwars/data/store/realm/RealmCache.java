package br.com.startwars.data.store.realm;

import io.realm.Realm;

/**
 * Created by Uzias on 18/01/17.
 */

public abstract class RealmCache {

    private Realm realm;


     Realm getRealm() {
        if (realm == null || realm.isClosed()) {
            realm = Realm.getDefaultInstance();
           // Logger.d(Thread.currentThread().getName());
        }
        return realm;
    }

    void closeRealm() {
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
        realm = null;
    }

    public boolean clear() {
        getRealm().beginTransaction();
        boolean result = getRealm().where(getEntityClass()).findAll().deleteAllFromRealm();
        getRealm().commitTransaction();
        closeRealm();
        return result;
    }

    protected abstract Class getEntityClass();
}

