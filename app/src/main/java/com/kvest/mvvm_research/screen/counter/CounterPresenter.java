package com.kvest.mvvm_research.screen.counter;

import android.os.Bundle;

/**
 * Created by kvest on 08.06.16.
 */
public class CounterPresenter extends CounterContract.Presenter {
    private static final String KEY_COUNTER = "counter";

    private int counter;

    @Override
    public void increment() {
        counter++;
        updateViewCounter();
    }

    @Override
    public void decrement() {
        counter--;
        updateViewCounter();
    }

    private void updateViewCounter() {
        if (view != null) {
            view.setCounterValue(counter);
        }
    }

    @Override
    public Bundle getInstanceState() {
        Bundle bundle = new Bundle(1);
        bundle.putInt(KEY_COUNTER, counter);

        return bundle;
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        counter = (savedInstanceState.getInt(KEY_COUNTER, 0));
        updateViewCounter();
    }
}
