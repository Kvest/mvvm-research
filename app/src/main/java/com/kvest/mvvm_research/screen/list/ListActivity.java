package com.kvest.mvvm_research.screen.list;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.Toast;


import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.datamodel.Item;
import com.kvest.mvvm_research.common.mvp.BaseActivity;
import com.kvest.mvvm_research.databinding.ListActivityBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvest on 04.06.16.
 */
public class ListActivity extends BaseActivity<ListContract.Presenter> implements ListContract.View {
    private BottomSheetBehavior bottomSheetBehavior;

    private ListViewModel listViewModel;
    private AddViewModel addViewModel;
    private EditViewModel editViewModel;
    private DeleteViewModel deleteViewModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.list_activity);
        createViewModels();
        setViewModels(binding);
        initViews(binding);
    }

    private void setViewModels(ListActivityBinding binding) {
        binding.setListViewModel(listViewModel);
        binding.setAddViewModel(addViewModel);
        binding.setEditViewModel(editViewModel);
        binding.setDeleteViewModel(deleteViewModel);
    }

    private void createViewModels() {
        listViewModel = new ListViewModel();
        addViewModel = new AddViewModel();
        editViewModel = new EditViewModel();
        deleteViewModel = new DeleteViewModel();
    }

    private void initViews(ListActivityBinding binding) {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.editPanel);

        //init items list
        binding.itemsList.setHasFixedSize(true);
        binding.itemsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Item> INITIAL_DATA = new ArrayList<Item>(){{add(new Item(1, "Data 1")); add(new Item(2, "Data 2"));
            add(new Item(3, "Data 3")); add(new Item(4, "Data 4"));}};
        binding.itemsList.setAdapter(new ItemsListAdapter(INITIAL_DATA));
    }

    @Override
    public void showEditPanel() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void clearAddData() {
        addViewModel.setId("");
        addViewModel.setName("");
    }

    @Override
    public void clearDeleteData() {
        deleteViewModel.setId("");
    }

    @Override
    public void clearEditData() {
        editViewModel.setId("");
        editViewModel.setNewName("");
    }

    @Override
    public void showItemsLoadError() {
        Toast.makeText(this, "Error loading items", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    protected ListContract.Presenter createPresenter() {
        return new ListPresenter();
    }

    public class ListViewModel {
        public void onEdit() {
            if (presenter != null) {
                presenter.onEdit();
            }
        }
    }

    public class AddViewModel extends BaseObservable {
        private String id = "";
        private String name = "";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            notifyChange();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            notifyChange();
        }

        public boolean isValid() {
            return (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(name));
        }

        public void add() {
            if (presenter != null) {
                presenter.add(Long.parseLong(id), name);
            }
        }
    }

    public class EditViewModel extends BaseObservable {
        private String id = "";
        private String newName = "";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            notifyChange();
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
            notifyChange();
        }

        public boolean isValid() {
            return (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(newName));
        }

        public void edit() {
            if (presenter != null) {
                presenter.edit(Long.parseLong(id), newName);
            }
        }
    }

    public class DeleteViewModel extends BaseObservable {
        private String id = "";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            notifyChange();
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(id);
        }

        public void delete() {
            if (presenter != null) {
                presenter.delete(Long.parseLong(id));
            }
        }
    }
}
