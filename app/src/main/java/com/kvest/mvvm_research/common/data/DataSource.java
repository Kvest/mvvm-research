package com.kvest.mvvm_research.common.data;

import android.support.annotation.NonNull;

import com.kvest.mvvm_research.common.datamodel.Item;
import com.kvest.mvvm_research.common.datamodel.User;

/**
 * Created by kvest on 10.06.16.
 */
public interface DataSource {
    interface LoadUserCallback {
        void onUserLoaded(User user);
        void onUserLoadError();
    }

    interface LoadItemsCallback {
        void onItemAdded(Item item, Long previousItemId);
        void onItemChanged(long itemId, String value);
        void onItemDeleted(long itemId);
        void onItemMoved(long itemId, Long previousItemId);
        void onItemsLoadError();
    }

    void subscribeForUser(@NonNull LoadUserCallback callback);
    void unsubscribeForUser(@NonNull LoadUserCallback callback);

    void saveUser(@NonNull User user);

    void subscribeForItems(@NonNull LoadItemsCallback callback);
    void unsubscribeForItems(@NonNull LoadItemsCallback callback);

    void addItem(@NonNull Item item);
    void deleteItem(@NonNull Long itemId);
    void updateItem(@NonNull Item item);
}
