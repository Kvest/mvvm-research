package com.kvest.mvvm_research.common.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kvest.mvvm_research.common.datamodel.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kvest on 10.06.16.
 */
public class FirebaseDataSource implements DataSource {
    private static final String USER_PATH = "user";

    private FirebaseDatabase database;

    private Map<LoadUserCallback, UserValueListener> loadUserCallbacks;

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
    }

    @Override
    public void subscribeForUser(LoadUserCallback callback) {
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
    public void unsubscribeForUser(LoadUserCallback callback) {
        UserValueListener listener = loadUserCallbacks.remove(callback);

        if (listener != null) {
            //subscribe for changes
            DatabaseReference userRef = database.getReference(USER_PATH);
            userRef.removeEventListener(listener);
        }
    }

    private static class UserValueListener implements ValueEventListener {
        private LoadUserCallback callback;

        public UserValueListener(LoadUserCallback callback) {
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
}
