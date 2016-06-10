package com.kvest.mvvm_research.screen.main_menu;

import android.content.Context;

import com.kvest.mvvm_research.screen.counter.CounterActivity;
import com.kvest.mvvm_research.screen.list.ListActivity;

/**
 * Created by roman on 6/7/16.
 */
public class MainMenuPresenter extends MainMenuContract.Presenter {
    @Override
    public void showCounterScreen(Context context) {
        CounterActivity.start(context);
    }

    @Override
    public void showListScreen(Context context) {
        ListActivity.start(context);
    }
}
