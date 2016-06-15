package com.kvest.mvvm_research.screen.user;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.datamodel.Gender;
import com.kvest.mvvm_research.common.datamodel.User;
import com.kvest.mvvm_research.common.mvp.BaseFragment;
import com.kvest.mvvm_research.databinding.UserFragmentBinding;

/**
 * Created by roman on 6/10/16.
 */
public class UserFragment extends BaseFragment<UserContract.Presenter> implements UserContract.View {
    private ViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModel();

        UserFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false);
        binding.setViewModel(viewModel);
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
    public void showUser(User user) {
        viewModel.firstName.set(user.firstName);
        viewModel.lastName.set(user.lastName);
    }

    public class ViewModel {
        public ObservableInt gender;
        public ObservableField<String> firstName;
        public ObservableField<String> lastName;

        public ViewModel() {
            gender = new ObservableInt(Gender.MALE);
            firstName = new ObservableField<>("");
            lastName = new ObservableField<>("");
        }

        public void save() {
            if (presenter != null) {
                presenter.save();
            }
        }

        public void onFirstNameChanged(CharSequence s, int start, int before, int count) {
            //TODO
            Log.d("KVEST_TAG", "onFirstNameChanged " + s);
        }

        public void onLastNameChanged(CharSequence s, int start, int before, int count) {
            //TODO
            Log.w("KVEST_TAG", "onLastNameChanged " + s);
        }
    }
}
