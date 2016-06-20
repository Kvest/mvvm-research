package com.kvest.mvvm_research.screen.list;

import com.kvest.mvvm_research.common.data.DataSource;
import com.kvest.mvvm_research.common.datamodel.Item;
import com.kvest.mvvm_research.utils.Injection;

/**
 * Created by kvest on 17.06.16.
 */
public class ListPresenter extends ListContract.Presenter implements DataSource.LoadItemsCallback {
    private DataSource dataSource;

    @Override
    public void attachView(ListContract.View view) {
        super.attachView(view);

        dataSource = Injection.provideDataSource();
        dataSource.subscribeForItems(this);
    }

    @Override
    public void detachView() {
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
        //TODO
    }

    @Override
    public void onItemChanged(long itemId, String value) {
        //TODO
    }

    @Override
    public void onItemDeleted(long itemId) {
        //TODO
    }

    @Override
    public void onItemMoved(long itemId, Long previousItemId) {
        //TODO
    }

    @Override
    public void onItemsLoadError() {
        if (view != null) {
            view.showItemsLoadError();
        }
    }
}
