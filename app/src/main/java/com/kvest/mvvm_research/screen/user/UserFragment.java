package com.kvest.mvvm_research.screen.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.mvp.BaseFragment;
import com.kvest.mvvm_research.databinding.UserFragmentBinding;

/**
 * Created by roman on 6/10/16.
 */
public class UserFragment extends BaseFragment<UserContract.Presenter> implements UserContract.View {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       UserFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false);
        //binding.setViewModel(new ViewModel());
        return binding.getRoot();
    }

    @NonNull
    @Override
    protected UserContract.Presenter createPresenter() {
        return new UserPresenter();
    }
}
