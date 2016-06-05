package com.kvest.mvvm_research.screen.counter;

import android.databinding.ObservableInt;
import android.os.Bundle;

import com.kvest.mvvm_research.common.mvvm.ViewModel;

/**
 * Created by kvest on 03.06.16.
 */
public class CounterViewModel extends ViewModel<Bundle> {
    private static final String KEY_COUNTER = "counter";

    private final ObservableInt counter;

    public CounterViewModel() {
        counter = new ObservableInt(0);
    }

    @Override
    public Bundle getInstanceState() {
        Bundle bundle = new Bundle(1);
        bundle.putInt(KEY_COUNTER, counter.get());
        return bundle;
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        counter.set(savedInstanceState.getInt(KEY_COUNTER, 0));
    }

    public ObservableInt getCounter() {
        return counter;
    }

    public void increment() {
        counter.set(counter.get() + 1);
    }

    public void decrement() {
        counter.set(counter.get() - 1);
    }
}
