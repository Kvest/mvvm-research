package com.kvest.mvvm_research.screen.list;

import com.kvest.mvvm_research.common.data.DataSource;
import com.kvest.mvvm_research.utils.Injection;

/**
 * Created by kvest on 17.06.16.
 */
public class ListPresenter extends ListContract.Presenter {
    private DataSource dataSource;

    @Override
    public void attachView(ListContract.View view) {
        super.attachView(view);

        dataSource = Injection.provideDataSource();
        //TODO attach listener
    }

    @Override
    public void detachView() {
        //TODO detach listener
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
        //TODO
    }

    @Override
    public void edit(long id, String newName) {
        //TODO
    }

    @Override
    public void add(long id, String name) {
        //TODO
    }

    @Override
    public void insert(long id, String name, int position) {
        //TODO
    }
}
