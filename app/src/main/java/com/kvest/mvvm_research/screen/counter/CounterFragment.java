package com.kvest.mvvm_research.screen.counter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.mvp.BaseFragment;
import com.kvest.mvvm_research.databinding.CounterFragmentBinding;

/**
 * Created by kvest on 03.06.16.
 */
public class CounterFragment extends BaseFragment<CounterContract.Presenter> implements CounterContract.View  {
    private ViewModel viewModel;

    public static CounterFragment newInstance() {
        return new CounterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModel();

        CounterFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.counter_fragment, container, false);
        binding.setCounterModel(viewModel);
        return binding.getRoot();
    }

    @NonNull
    @Override
    protected CounterContract.Presenter createPresenter() {
        return new CounterPresenter();
    }

    @Override
    public void setCounterValue(int value) {
        viewModel.counter.set(value);
    }

    public class ViewModel {
        private final ObservableInt counter;

        public ViewModel() {
            counter = new ObservableInt(0);
        }

        public ObservableInt getCounter() {
            return counter;
        }

        public void increment() {
            if (presenter != null) {
                presenter.increment();
            }
        }

        public void decrement() {
            if (presenter != null) {
                presenter.decrement();
            }
        }
    }
}
