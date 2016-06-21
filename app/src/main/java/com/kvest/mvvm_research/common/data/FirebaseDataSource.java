package com.kvest.mvvm_research.common.data;

import android.support.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kvest.mvvm_research.common.datamodel.Item;
import com.kvest.mvvm_research.common.datamodel.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kvest on 10.06.16.
 */
public class FirebaseDataSource implements DataSource {
    private static final String USER_PATH = "user";
    private static final String ITEMS_PATH = "items";

    private FirebaseDatabase database;

    private Map<LoadUserCallback, UserValueListener> loadUserCallbacks;
    private Map<LoadItemsCallback, ItemsValueListener> loadItemsCallbacks;

    private static volatile FirebaseDataSource instance;

    public static FirebaseDataSource getInstance() {
        if (instance == null) {
            synchronized (FirebaseDataSource.class) {
                if (instance == null) {
                    instance = new FirebaseDataSource();
                }
            }
        }

        return instance;
    }

    private FirebaseDataSource() {
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);

        loadUserCallbacks = new HashMap<>();
        loadItemsCallbacks = new HashMap<>();
    }

    @Override
    public void subscribeForUser(@NonNull LoadUserCallback callback) {
        //create and remember listener
        UserValueListener listener = new UserValueListener(callback);
        UserValueListener oldListener = loadUserCallbacks.remove(callback);
        loadUserCallbacks.put(callback, listener);

        //subscribe for changes
        DatabaseReference userRef = database.getReference(USER_PATH);
        if (oldListener != null) {
            userRef.removeEventListener(oldListener);
        }
        userRef.addValueEventListener(listener);
    }

    @Override
    public void unsubscribeForUser(@NonNull LoadUserCallback callback) {
        UserValueListener listener = loadUserCallbacks.remove(callback);

        if (listener != null) {
            //unsubscribe
            DatabaseReference userRef = database.getReference(USER_PATH);
            userRef.removeEventListener(listener);
        }
    }

    @Override
    public void saveUser(@NonNull User user) {
        DatabaseReference userRef = database.getReference(USER_PATH);
        userRef.setValue(user);
    }

    @Override
    public void subscribeForItems(@NonNull LoadItemsCallback callback) {
        //create and remember listener
        ItemsValueListener listener = new ItemsValueListener(callback);
        ItemsValueListener oldListener = loadItemsCallbacks.remove(callback);
        loadItemsCallbacks.put(callback, listener);

        //subscribe for changes
        DatabaseReference itemsRef = database.getReference(ITEMS_PATH);
        if (oldListener != null) {
            itemsRef.removeEventListener(oldListener);
        }
        itemsRef.orderByValue().addChildEventListener(listener);
    }

    @Override
    public void unsubscribeForItems(@NonNull LoadItemsCallback callback) {
        ItemsValueListener listener = loadItemsCallbacks.remove(callback);

        if (listener != null) {
            //ubsubscribe
            DatabaseReference itemsRef = database.getReference(ITEMS_PATH);
            itemsRef.removeEventListener(listener);
        }
    }

    @Override
    public void addItem(@NonNull Item item) {
        DatabaseReference itemRef = database.getReference(ITEMS_PATH + "/" + item.id);
        itemRef.setValue(item.name);
    }

    @Override
    public void deleteItem(@NonNull Long itemId) {
        DatabaseReference itemRef = database.getReference(ITEMS_PATH + "/" + itemId);
        itemRef.removeValue();
    }

    @Override
    public void updateItem(@NonNull final Item item) {
        DatabaseReference itemsRef = database.getReference(ITEMS_PATH + "/" + item.id);
        itemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.getRef().setValue(item.name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static class UserValueListener implements ValueEventListener {
        private LoadUserCallback callback;

        public UserValueListener(@NonNull LoadUserCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            User user = dataSnapshot.getValue(User.class);

            callback.onUserLoaded(user);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            callback.onUserLoadError();
        }
    }

    private static class ItemsValueListener implements ChildEventListener {
        private LoadItemsCallback callback;

        public ItemsValueListener(@NonNull LoadItemsCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Item item = new Item(Long.parseLong(dataSnapshot.getKey()), dataSnapshot.getValue(String.class));
            Long previousItemId = previousChildName != null ? Long.parseLong(previousChildName) : null;

            callback.onItemAdded(item, previousItemId);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            long itemId = Long.parseLong(dataSnapshot.getKey());
            String value = dataSnapshot.getValue(String.class);

            callback.onItemChanged(itemId, value);
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            long itemId = Long.parseLong(dataSnapshot.getKey());

            callback.onItemDeleted(itemId);
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            long itemId = Long.parseLong(dataSnapshot.getKey());
            Long previousItemId = previousChildName != null ? Long.parseLong(previousChildName) : null;

            callback.onItemMoved(itemId, previousItemId);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            callback.onItemsLoadError();
        }
    }
}
