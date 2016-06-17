package com.kvest.mvvm_research.screen.list;

import android.os.Parcelable;

import com.kvest.mvvm_research.common.mvp.BasePresenter;
import com.kvest.mvvm_research.common.mvp.BaseView;

/**
 * Created by kvest on 17.06.16.
 */
public interface ListContract {
    interface View extends BaseView {
        void showEditPanel();
    }

    abstract class Presenter extends BasePresenter<View, Parcelable> {
        public abstract void onEdit();
        public abstract void delete(long id);
        public abstract void edit(long id, String newName);
        public abstract void add(long id, String name);
        public abstract void insert(long id, String name, int position);
    }
}
