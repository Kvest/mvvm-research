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
        void setGender(int gender);
        void setFirstName(String firstName);
        void setLastName(String lastName);
        int getGenderValue();
        String getFirstNameValue();
        String getLastNameValue();
        void setSaveButtonEnabled(boolean enabled);

        void clearFirstNameError();
        void setFirstNameError();

        void clearLastNameError();
        void setLastNameError();
    }

    abstract class Presenter extends BasePresenter<View, Parcelable> {
        public abstract void save();
        public abstract void onGenderChanged(int newValue);
        public abstract void onFirstNameChanged(String newValue);
        public abstract void onLastNameChanged(String newValue);
    }
}
