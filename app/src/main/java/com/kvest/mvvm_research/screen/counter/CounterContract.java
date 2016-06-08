package com.kvest.mvvm_research.screen.counter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import com.kvest.mvvm_research.common.mvp.BasePresenter;
import com.kvest.mvvm_research.common.mvp.BaseView;

/**
 * Created by kvest on 08.06.16.
 */
public class CounterContract {
    interface View extends BaseView {
        void setCounterValue(int value);
    }

    interface Presenter extends BasePresenter<View, Bundle> {
        void increment();
        void decrement();
    }
}
