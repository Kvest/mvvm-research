package com.kvest.mvvm_research.screen.user;

import android.os.Parcelable;

import com.kvest.mvvm_research.common.datamodel.User;
import com.kvest.mvvm_research.common.mvp.BasePresenter;
import com.kvest.mvvm_research.common.mvp.BaseView;

/**
 * Created by roman on 6/10/16.
 */
public interface UserContract {
    interface View extends BaseView {
        void showUserLoadError();
        void showUser(User user);
        int getGenderValue();
        String getFirstNameValue();
        String getLastNameValue();
    }

    abstract class Presenter extends BasePresenter<View, Parcelable> {
        public abstract void save();
    }
}
