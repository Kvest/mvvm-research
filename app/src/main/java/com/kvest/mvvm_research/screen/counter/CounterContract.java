package com.kvest.mvvm_research.screen.counter;

import android.os.Bundle;

import com.kvest.mvvm_research.common.mvp.BasePresenter;
import com.kvest.mvvm_research.common.mvp.BaseView;

/**
 * Created by kvest on 08.06.16.
 */
public interface CounterContract {
    interface View extends BaseView {
        void setCounterValue(int value);
    }

    abstract class Presenter extends BasePresenter<View, Bundle> {
        abstract void increment();
        abstract void decrement();
    }
}
