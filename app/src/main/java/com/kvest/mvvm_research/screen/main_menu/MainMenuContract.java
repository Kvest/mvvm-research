package com.kvest.mvvm_research.screen.main_menu;

import android.content.Context;
import android.os.Parcelable;

import com.kvest.mvvm_research.common.mvp.BasePresenter;
import com.kvest.mvvm_research.common.mvp.BaseView;

/**
 * Created by roman on 6/7/16.
 */
interface MainMenuContract {
    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<View, Parcelable> {
        abstract void showCounterScreen(Context context);
        abstract void showListScreen(Context context);
    }
}
