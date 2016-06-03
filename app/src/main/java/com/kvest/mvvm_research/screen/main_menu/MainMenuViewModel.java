package com.kvest.mvvm_research.screen.main_menu;

import android.content.Context;
import android.os.Parcelable;

import com.kvest.mvvm_research.common.mvvm.ViewModel;
import com.kvest.mvvm_research.screen.counter.CounterActivity;

/**
 * Created by kvest on 03.06.16.
 */
public class MainMenuViewModel extends ViewModel {
    @Override
    public Parcelable getInstanceState() {
        return null;
    }

    @Override
    public void restoreInstanceState(Parcelable savedInstanceState) {
        //nope
    }

    public void showCounterScreen(Context context) {
        CounterActivity.start(context);
    }

    public void showListScreen(Context context) {
        //TODO
    }
}
