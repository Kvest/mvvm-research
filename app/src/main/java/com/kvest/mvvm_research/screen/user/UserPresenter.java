package com.kvest.mvvm_research.screen.user;

import com.kvest.mvvm_research.common.data.DataSource;
import com.kvest.mvvm_research.common.datamodel.User;
import com.kvest.mvvm_research.utils.Injection;

/**
 * Created by roman on 6/10/16.
 */
public class UserPresenter extends UserContract.Presenter implements DataSource.LoadUserCallback {
    private DataSource dataSource;

    @Override
    public void attachView(UserContract.View view) {
        super.attachView(view);

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
        if (view != null) {
            view.showUser(user);
        }
    }

    @Override
    public void onUserLoadError() {
        if (view != null) {
            view.showUserLoadError();
        }
    }
}
