package br.com.starwars.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by Uzias on 18/01/17.
 */

public class ThreadExecutor {

    private Scheduler scheduler;

    public ThreadExecutor(final Scheduler scheduler){
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}