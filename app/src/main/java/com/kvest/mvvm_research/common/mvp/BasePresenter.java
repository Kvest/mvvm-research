package com.kvest.mvvm_research.common.mvp;

import android.os.Parcelable;
import android.support.annotation.CallSuper;

/**
 * Created by roman on 6/7/16.
 */
public interface BasePresenter<V extends BaseView, T extends Parcelable> {
    void attachView(V view);
    void detachView();
    void onStart();
    void onStop();
    T getInstanceState();
    void restoreInstanceState(T savedInstanceState);
}
