package br.com.starwars.utils;

import br.com.starwars.domain.providers.SchedulerProvider;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Uzias on 18/01/17.
 */

public class ThreadUtil implements SchedulerProvider {
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }
}