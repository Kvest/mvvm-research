package com.kvest.mvvm_research.screen.list;

import android.databinding.ObservableField;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.kvest.mvvm_research.common.mvvm.ViewModel;

/**
 * Created by kvest on 04.06.16.
 */
public class ListViewModel extends ViewModel {
    private final ObservableField<String> addId;
    private final ObservableField<String> addName;
    private final ObservableField<String> addPosition;
    private final ObservableField<String> deleteId;
    private final ObservableField<String> editId;
    private final ObservableField<String> editNewName;

    public ListViewModel() {
        addId = new ObservableField<>("");
        addName = new ObservableField<>("");
        addPosition = new ObservableField<>("");
        deleteId = new ObservableField<>("");
        editId = new ObservableField<>("");
        editNewName = new ObservableField<>("");
    }

    public ObservableField<String> getAddId() {
        return addId;
    }

    public ObservableField<String> getAddName() {
        return addName;
    }

    public ObservableField<String> getAddPosition() {
        return addPosition;
    }

    public ObservableField<String> getDeleteId() {
        return deleteId;
    }

    public ObservableField<String> getEditId() {
        return editId;
    }

    public ObservableField<String> getEditNewName() {
        return editNewName;
    }

    public void add() {
        //TODO
        Log.d("KVEST_TAG", "addId=" + addId.get() + ", addName=" + addName.get() + ", addPosition=" + addPosition.get());
    }

    public void delete() {
        //TODO
    }

    public void edit() {
        //TODO
    }

    @Override
    public Parcelable getInstanceState() {
        return null;
    }

    @Override
    public void restoreInstanceState(Parcelable savedInstanceState) {}
}
