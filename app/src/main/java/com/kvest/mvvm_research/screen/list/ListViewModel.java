package com.kvest.mvvm_research.screen.list;

import android.databinding.BaseObservable;
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
    private final EditInfo editInfo;

    public ListViewModel() {
        addId = new ObservableField<>("");
        addName = new ObservableField<>("");
        addPosition = new ObservableField<>("");
        deleteId = new ObservableField<>("");
        editInfo = new EditInfo();
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

    public EditInfo getEditInfo() {
        return editInfo;
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

    public static class EditInfo extends BaseObservable {
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
    }
}
