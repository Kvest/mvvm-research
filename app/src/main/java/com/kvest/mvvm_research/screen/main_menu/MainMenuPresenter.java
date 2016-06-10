package com.kvest.mvvm_research.screen.main_menu;

import android.content.Context;

import com.kvest.mvvm_research.screen.counter.CounterActivity;
import com.kvest.mvvm_research.screen.list.ListActivity;
import com.kvest.mvvm_research.screen.user.UserActivity;

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

    @Override
    void showUserScreen(Context context) {
        UserActivity.start(context);
    }
}
