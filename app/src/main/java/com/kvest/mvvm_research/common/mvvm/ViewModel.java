package com.kvest.mvvm_research.common.mvvm;

import android.os.Parcelable;
import android.support.annotation.CallSuper;
/**
 * Created by kvest on 03.06.16.
 */
public abstract class ViewModel {
    public abstract Parcelable getInstanceState();
    public abstract void restoreInstanceState(Parcelable savedInstanceState);

    @CallSuper
    public void onStart() {}

    @CallSuper
    public void onStop() {}
}
