package com.kvest.mvvm_research.screen.user;

import android.text.TextUtils;
import android.util.Log;

import com.kvest.mvvm_research.common.data.DataSource;
import com.kvest.mvvm_research.common.datamodel.User;
import com.kvest.mvvm_research.utils.Injection;

import java.util.Objects;

/**
 * Created by roman on 6/10/16.
 */
public class UserPresenter extends UserContract.Presenter implements DataSource.LoadUserCallback {
    private static final int MASK_EMPTY = 0;
    private static final int MASK_GENDER = 1 << 0;
    private static final int MASK_FIRST_NAME = 1 << 1;
    private static final int MASK_LAST_NAME = 1 << 2;

    private DataSource dataSource;
    private User shownUser;

    private int errors = MASK_EMPTY;
    private int changes = MASK_EMPTY;

    @Override
    public void attachView(UserContract.View view) {
        super.attachView(view);

        errors = MASK_EMPTY;
        changes = MASK_EMPTY;
        view.setSaveButtonEnabled(false);

        dataSource = Injection.provideDataSource();
        dataSource.subscribeForUser(this);
    }

    @Override
    public void detachView() {
        dataSource.unsubscribeForUser(this);
        dataSource = null;

        super.detachView();
    }

    @Override
    public void onUserLoaded(User user) {
        User oldUser = shownUser;
        shownUser = user;

        if (view == null) {
            return;
        }

        if (oldUser == null) {
            //this is first set of the user
            view.setGender(user.gender);
            view.setFirstName(user.firstName);
            view.setLastName(user.lastName);

            return;
        }

        //set new values only if they are different
        //Also don't change shown values for the new ones if the user sis changes via UI
        //gender
        int shownGender = view.getGenderValue();
        if (shownGender == oldUser.gender) {
            //Value was not changed by user
            if (user.gender != shownGender) {
                view.setGender(user.gender);
            }
        } else {
            //Value was changed by user
            onGenderChanged(shownGender);
        }

        //first name
        String shownValue = view.getFirstNameValue();
        if (Objects.equals(oldUser.firstName, shownValue)) {
            //Value was not changed by user
            if (!Objects.equals(user.firstName, shownValue)) {
                view.setFirstName(user.firstName);
            }
        } else {
            //Value was changed by user
            onFirstNameChanged(shownValue);
        }

        //last name
        shownValue = view.getLastNameValue();
        if (Objects.equals(oldUser.lastName, shownValue)) {
            //Value was not changed by user
            if (!Objects.equals(user.lastName, shownValue)) {
                view.setLastName(user.lastName);
            }
        } else {
            //Value was changed by user
            onLastNameChanged(shownValue);
        }
    }

    @Override
    public void onUserLoadError() {
        if (view != null) {
            view.showUserLoadError();
        }
    }

    @Override
    public void onGenderChanged(int newValue) {
        if (shownUser != null) {
            if (shownUser.gender == newValue) {
                unsetChanges(MASK_GENDER);
            } else {
                setChanges(MASK_GENDER);
            }
        }

        updateSaveButton();
    }

    @Override
    public void onFirstNameChanged(String newValue) {
        Log.d("KVEST_TAG", "first name=" + newValue);
        //check for errors
        if (!TextUtils.isEmpty(newValue.trim())) {
            unsetErrors(MASK_FIRST_NAME);
            if (view != null) {
                view.clearFirstNameError();
            }
        } else {
            setErrors(MASK_FIRST_NAME);
            if (view != null) {
                view.setFirstNameError();
            }
        }

        if (shownUser != null) {
            //check for changes
            if (Objects.equals(newValue, shownUser.firstName)) {
                unsetChanges(MASK_FIRST_NAME);
            } else {
                setChanges(MASK_FIRST_NAME);
            }
        }

        updateSaveButton();
    }

    @Override
    public void onLastNameChanged(String newValue) {
        //check for errors
        if (!TextUtils.isEmpty(newValue.trim())) {
            unsetErrors(MASK_LAST_NAME);
            if (view != null) {
                view.clearLastNameError();
            }
        } else {
            setErrors(MASK_LAST_NAME);
            if (view != null) {
                view.setLastNameError();
            }
        }

        if (shownUser != null) {
            //check for changes
            if (Objects.equals(newValue, shownUser.lastName)) {
                unsetChanges(MASK_LAST_NAME);
            } else {
                setChanges(MASK_LAST_NAME);
            }
        }

        updateSaveButton();
    }

    @Override
    public void save() {
        if (view == null) {
            return;
        }

        User user = new User();
        user.gender = view.getGenderValue();
        user.firstName = view.getFirstNameValue();
        user.lastName = view.getLastNameValue();

        dataSource.saveUser(user);
    }

    private void updateSaveButton() {
        if (view != null) {
            view.setSaveButtonEnabled(errors == MASK_EMPTY && changes != MASK_EMPTY);
        }
    }

    private void setChanges(int mask) {
        changes |= mask;
    }

    private void unsetChanges(int mask) {
        changes &= (~mask);
    }

    private void setErrors(int mask) {
        errors |= mask;
    }

    private void unsetErrors(int mask) {
        errors &= (~mask);
    }
}
