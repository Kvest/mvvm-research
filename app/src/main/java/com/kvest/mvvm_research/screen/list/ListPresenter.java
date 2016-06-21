package com.kvest.mvvm_research.screen.list;

import com.kvest.mvvm_research.common.data.DataSource;
import com.kvest.mvvm_research.common.datamodel.Item;
import com.kvest.mvvm_research.common.recycler_view_utils.FirebaseRecyclerViewCollection;
import com.kvest.mvvm_research.utils.Injection;

/**
 * Created by kvest on 17.06.16.
 */
public class ListPresenter extends ListContract.Presenter implements DataSource.LoadItemsCallback {
    private DataSource dataSource;
    private FirebaseRecyclerViewCollection<Item> data;
    private Item previousItem, tmpItem;

    public ListPresenter() {
        data = new FirebaseRecyclerViewCollection<>();
        previousItem = new Item(-1, null);
        tmpItem = new Item(-1, null);
    }

    @Override
    public void attachView(ListContract.View view) {
        super.attachView(view);

        //set data list
        if (view != null) {
            view.setItemsListData(data);
        }

        dataSource = Injection.provideDataSource();
        dataSource.subscribeForItems(this);
    }

    @Override
    public void detachView() {
        if (view != null) {
            view.clearItemsListData();
        }

        dataSource.unsubscribeForItems(this);
        dataSource = null;

        super.detachView();
    }

    @Override
    public void onEdit() {
        if (view != null) {
            view.showEditPanel();
        }
    }

    @Override
    public void delete(long id) {
        dataSource.deleteItem(id);

        if (view != null) {
            view.clearDeleteData();
        }
    }

    @Override
    public void edit(long id, String newName) {
        Item item = new Item(id, newName);
        dataSource.updateItem(item);

        if (view != null) {
            view.clearEditData();
        }
    }

    @Override
    public void add(long id, String name) {
        Item item = new Item(id, name);
        dataSource.addItem(item);

        if (view != null) {
            view.clearAddData();
        }
    }

    @Override
    public void onItemAdded(Item item, Long previousItemId) {
        if (previousItemId == null) {
            data.onItemAdded(item ,null);
        } else {
            previousItem.id = previousItemId;
            data.onItemAdded(item ,previousItem);
        }
    }

    @Override
    public void onItemChanged(long itemId, String value) {
        data.onItemChanged(new Item(itemId, value));
    }

    @Override
    public void onItemDeleted(long itemId) {
        tmpItem.id = itemId;
        data.onItemDeleted(tmpItem);
    }

    @Override
    public void onItemMoved(long itemId, Long previousItemId) {
        tmpItem.id = itemId;
        if (previousItemId == null) {
            data.onItemMoved(tmpItem, null);
        } else {
            previousItem.id = previousItemId;
            data.onItemMoved(tmpItem, previousItem);
        }
    }

    @Override
    public void onItemsLoadError() {
        if (view != null) {
            view.showItemsLoadError();
        }
    }
}
