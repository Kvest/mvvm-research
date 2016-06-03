package com.kvest.mvvm_research.screen.counter;

import android.databinding.ObservableInt;
import android.os.Parcelable;

import com.kvest.mvvm_research.common.mvvm.ViewModel;

/**
 * Created by kvest on 03.06.16.
 */
public class CounterViewModel extends ViewModel {
    private ObservableInt counter;

    public CounterViewModel() {
        counter = new ObservableInt(0);
    }

    @Override
    public Parcelable getInstanceState() {
        return null;
    }

    @Override
    public void restoreInstanceState(Parcelable savedInstanceState) {
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
