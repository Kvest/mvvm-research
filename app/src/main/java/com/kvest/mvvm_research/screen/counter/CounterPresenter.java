package com.kvest.mvvm_research.screen.counter;

import android.os.Bundle;

/**
 * Created by kvest on 08.06.16.
 */
public class CounterPresenter implements CounterContract.Presenter {
    private CounterContract.View view;
    private int counter;

    @Override
    public void increment() {
        counter++;
        if (view != null) {
            view.setCounterValue(counter);
        }
    }

    @Override
    public void decrement() {
        counter--;
        if (view != null) {
            view.setCounterValue(counter);
        }
    }

    @Override
    public void attachView(CounterContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onStart() {}

    @Override
    public void onStop() {}

    @Override
    public Bundle getInstanceState() {
        //TODO
        return null;
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        //TODO
    }
}
