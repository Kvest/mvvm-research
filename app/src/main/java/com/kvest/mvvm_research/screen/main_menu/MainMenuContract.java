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

    interface Presenter extends BasePresenter<View, Parcelable> {
        void showCounterScreen(Context context);
        void showListScreen(Context context);
    }
}
