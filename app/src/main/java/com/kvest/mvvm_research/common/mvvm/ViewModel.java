package com.kvest.mvvm_research.common.mvvm;

import android.os.Parcelable;
import android.support.annotation.CallSuper;
/**
 * Created by kvest on 03.06.16.
 */
public abstract class ViewModel<T extends Parcelable> {
    public abstract T getInstanceState();
    public abstract void restoreInstanceState(T savedInstanceState);

    @CallSuper
    public void onStart() {}

    @CallSuper
    public void onStop() {}
}
