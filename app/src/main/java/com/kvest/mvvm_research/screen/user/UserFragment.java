package com.kvest.mvvm_research.screen.user;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.datamodel.Gender;
import com.kvest.mvvm_research.common.mvp.BaseFragment;
import com.kvest.mvvm_research.databinding.UserFragmentBinding;

/**
 * Created by roman on 6/10/16.
 */
public class UserFragment extends BaseFragment<UserContract.Presenter> implements UserContract.View, PopupMenu.OnMenuItemClickListener {
    private ViewModel viewModel;

    private EditText firstName;
    private EditText lastName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModel();

        UserFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false);
        binding.setViewModel(viewModel);

        //store widgets
        firstName = binding.firstName;
        lastName = binding.lastName;

        return binding.getRoot();
    }

    @Override
    public int getGenderValue() {
        return viewModel.gender.get();
    }

    @Override
    public String getFirstNameValue() {
        return viewModel.firstName.get();
    }

    @Override
    public String getLastNameValue() {
        return viewModel.lastName.get();
    }

    @NonNull
    @Override
    protected UserContract.Presenter createPresenter() {
        return new UserPresenter();
    }

    @Override
    public void showUserLoadError() {
        Context context = getActivity();
        if (context != null) {
            Toast.makeText(context, "Error loading user", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setGender(int gender) {
        viewModel.gender.set(gender);
    }

    @Override
    public void setFirstName(String firstName) {
        viewModel.firstName.set(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        viewModel.lastName.set(lastName);
    }

    @Override
    public void setSaveButtonEnabled(boolean enabled) {
        viewModel.saveButtonEnabled.set(enabled);
    }

    @Override
    public void clearFirstNameError() {
        firstName.setError(null);
    }

    @Override
    public void setFirstNameError() {
        firstName.setError(getString(R.string.invalid_first_name));
    }

    @Override
    public void clearLastNameError() {
        lastName.setError(null);
    }

    @Override
    public void setLastNameError() {
        lastName.setError(getString(R.string.invalid_last_name));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.male :
                viewModel.gender.set(Gender.MALE);
                viewModel.onGenderChanged(Gender.MALE);
                return true;
            case R.id.female :
                viewModel.gender.set(Gender.FEMALE);
                viewModel.onGenderChanged(Gender.FEMALE);
                return true;
        }

        return false;
    }

    public class ViewModel {
        public ObservableInt gender;
        public ObservableField<String> firstName;
        public ObservableField<String> lastName;
        public ObservableBoolean saveButtonEnabled;

        public ViewModel() {
            gender = new ObservableInt(Gender.MALE);
            firstName = new ObservableField<>("");
            lastName = new ObservableField<>("");
            saveButtonEnabled = new ObservableBoolean(false);
        }

        public void save() {
            if (presenter != null) {
                presenter.save();
            }
        }

        public void selectGender(View view) {
            PopupMenu popupMenu = new PopupMenu(getActivity(), view, Gravity.RIGHT);
            popupMenu.inflate(R.menu.gender_selection_menu);
            popupMenu.setOnMenuItemClickListener(UserFragment.this);
            popupMenu.show();
        }

        public void onFirstNameChanged(CharSequence s, int start, int before, int count) {
            if (presenter != null) {
                presenter.onFirstNameChanged(s.toString());
            }
        }

        public void onLastNameChanged(CharSequence s, int start, int before, int count) {
            if (presenter != null) {
                presenter.onLastNameChanged(s.toString());
            }
        }

        public void onGenderChanged(int newValue) {
            if (presenter != null) {
                presenter.onGenderChanged(newValue);
            }
        }
    }
}
