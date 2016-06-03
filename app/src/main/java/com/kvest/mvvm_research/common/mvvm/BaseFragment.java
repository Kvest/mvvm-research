package com.kvest.mvvm_research.common.mvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by kvest on 03.06.16.
 */
public abstract class BaseFragment<T extends ViewModel> extends Fragment {
    private static final String EXTRA_VIEW_MODEL_STATE = "com.kvest.mvvm_research.extra.VIEWMODELSTATE";

    protected T viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = createViewModel();
        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_VIEW_MODEL_STATE)) {
            viewModel.restoreInstanceState(savedInstanceState.getParcelable(EXTRA_VIEW_MODEL_STATE));
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (viewModel != null) {
            viewModel.onStart();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (viewModel != null) {
            outState.putParcelable(EXTRA_VIEW_MODEL_STATE, viewModel.getInstanceState());
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (viewModel != null) {
            viewModel.onStop();
        }
    }

    @NonNull
    protected abstract T createViewModel();
}
