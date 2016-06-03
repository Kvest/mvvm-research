package com.kvest.mvvm_research.screen.main_menu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.mvvm.BaseFragment;
import com.kvest.mvvm_research.databinding.MainMenuFragmentBinding;

/**
 * Created by kvest on 03.06.16.
 */
public class MainMenuFragment extends BaseFragment<MainMenuViewModel> {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainMenuFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.main_menu_fragment, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @NonNull
    @Override
    protected MainMenuViewModel createViewModel() {
        return new MainMenuViewModel();
    }
}
