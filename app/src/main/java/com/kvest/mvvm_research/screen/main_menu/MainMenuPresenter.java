package com.kvest.mvvm_research.screen.main_menu;

import android.content.Context;
import android.os.Parcelable;

import com.kvest.mvvm_research.screen.counter.CounterActivity;
import com.kvest.mvvm_research.screen.list.ListActivity;

/**
 * Created by roman on 6/7/16.
 */
public class MainMenuPresenter implements MainMenuContract.Presenter {
    @Override
    public void showCounterScreen(Context context) {
        CounterActivity.start(context);
    }

    @Override
    public void showListScreen(Context context) {
        ListActivity.start(context);
    }

    @Override
    public void attachView(MainMenuContract.View view) {
        //nothing to do
    }

    @Override
    public void detachView() {
        //nothing to do
    }

    @Override
    public void onStart() {
        //nothing to do
    }

    @Override
    public void onStop() {
        //nothing to do
    }

    @Override
    public Parcelable getInstanceState() {
        return null;
    }

    @Override
    public void restoreInstanceState(Parcelable savedInstanceState) {
        //nothing to do
    }
}
