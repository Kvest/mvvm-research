package com.kvest.mvvm_research.common.mvp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by roman on 6/17/16.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    private static final String EXTRA_PRESENTER_STATE = "com.kvest.mvvm_research.extra.PRESENTER_STATE";

    protected T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_PRESENTER_STATE)) {
            presenter.restoreInstanceState(savedInstanceState.getParcelable(EXTRA_PRESENTER_STATE));
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (presenter != null) {
            Parcelable presenterState = presenter.getInstanceState();

            if (presenterState != null) {
                outState.putParcelable(EXTRA_PRESENTER_STATE, presenterState);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (presenter != null) {
            presenter.onStop();
        }
    }

    @NonNull
    protected abstract T createPresenter();
}
