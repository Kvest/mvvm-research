package com.kvest.mvvm_research.screen.main_menu;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.mvp.BaseFragment;
import com.kvest.mvvm_research.databinding.MainMenuFragmentBinding;

/**
 * Created by kvest on 03.06.16.
 */
public class MainMenuFragment extends BaseFragment<MainMenuContract.Presenter> implements MainMenuContract.View {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainMenuFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.main_menu_fragment, container, false);
        binding.setViewModel(new ViewModel());
        return binding.getRoot();
    }

    @NonNull
    @Override
    protected MainMenuContract.Presenter createPresenter() {
        return new MainMenuPresenter();
    }

    public class ViewModel {
        private ViewModel() {}

        public void showCounterScreen(Context context) {
            if (presenter != null) {
                presenter.showCounterScreen(context);
            }
        }

        public void showListScreen(Context context) {
            if (presenter != null) {
                presenter.showListScreen(context);
            }
        }

        public void showUserInfo(Context context) {
            if (presenter != null) {
                presenter.showUserScreen(context);
            }
        }
    }
}
