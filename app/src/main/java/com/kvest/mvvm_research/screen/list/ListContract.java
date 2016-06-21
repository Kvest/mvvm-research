package com.kvest.mvvm_research.screen.list;

import android.os.Parcelable;

import com.kvest.mvvm_research.common.datamodel.Item;
import com.kvest.mvvm_research.common.mvp.BasePresenter;
import com.kvest.mvvm_research.common.mvp.BaseView;
import com.kvest.mvvm_research.common.recycler_view_utils.RecyclerViewCollection;

/**
 * Created by kvest on 17.06.16.
 */
public interface ListContract {
    interface View extends BaseView {
        void showEditPanel();
        void clearAddData();
        void clearDeleteData();
        void clearEditData();

        void showItemsLoadError();
        void setItemsListData(RecyclerViewCollection<Item> data);
        void clearItemsListData();
    }

    abstract class Presenter extends BasePresenter<View, Parcelable> {
        public abstract void onEdit();
        public abstract void delete(long id);
        public abstract void edit(long id, String newName);
        public abstract void add(long id, String name);
    }
}
