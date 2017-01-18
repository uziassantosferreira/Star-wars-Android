package br.com.starwars.domain.providers;

import io.reactivex.Scheduler;

/**
 * Created by Uzias on 18/01/17.
 */

public interface SchedulerProvider {
    Scheduler io();
    Scheduler mainThread();
    Scheduler computation();
}
