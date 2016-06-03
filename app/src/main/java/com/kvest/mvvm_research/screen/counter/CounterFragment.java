package com.kvest.mvvm_research.screen.counter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.mvvm.BaseFragment;
import com.kvest.mvvm_research.databinding.CounterFragmentBinding;

/**
 * Created by kvest on 03.06.16.
 */
public class CounterFragment extends BaseFragment<CounterViewModel> {
    public static CounterFragment newInstance() {
        return new CounterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CounterFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.counter_fragment, container, false);
        binding.setCounterModel(viewModel);
        return binding.getRoot();
    }

    @NonNull
    @Override
    protected CounterViewModel createViewModel() {
        return new CounterViewModel();
    }
}
