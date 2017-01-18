package br.com.starwars.domain.interactor;

import br.com.starwars.domain.executor.ThreadExecutor;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Uzias on 18/01/17.
 */

public abstract class UseCase {


    protected ThreadExecutor subscriberOn, observerOn;
    private CompositeDisposable subscription = new CompositeDisposable();
    private Flowable observable;

    public UseCase(final ThreadExecutor subscriberOn, final ThreadExecutor observerOn) {
        this.subscriberOn = subscriberOn;
        this.observerOn = observerOn;
    }

    protected Flowable withObservable(final Flowable observable) {
        this.observable = observable;
        return this.observable;
    }

    public void execute(final Consumer consumer) {
        Disposable disposable = observable
                .subscribeOn(subscriberOn.getScheduler())
                .observeOn(observerOn.getScheduler())
                .subscribe(consumer);
        this.subscription.add(disposable);
    }

    public void unsubscribe() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }
}

