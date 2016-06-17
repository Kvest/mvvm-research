package com.kvest.mvvm_research.common.data;

import com.kvest.mvvm_research.common.datamodel.User;

/**
 * Created by kvest on 10.06.16.
 */
public interface DataSource {
    interface LoadUserCallback {
        void onUserLoaded(User user);
        void onUserLoadError();
    }

    void subscribeForUser(LoadUserCallback callback);
    void unsubscribeForUser(LoadUserCallback callback);

    void saveUser(User user);
}
