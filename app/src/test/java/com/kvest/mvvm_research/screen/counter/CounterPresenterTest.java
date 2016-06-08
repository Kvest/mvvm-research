package com.kvest.mvvm_research.screen.counter;

import android.os.Bundle;

import com.kvest.mvvm_research.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;

/**
 * Created by kvest on 08.06.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class CounterPresenterTest {
    @Mock
    private CounterContract.View view;

    private CounterContract.Presenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new CounterPresenter();
        presenter.attachView(view);
    }

    @Test
    public void testIncrement() throws Exception {
        presenter.increment();
        verify(view).setCounterValue(1);

        presenter.increment();
        presenter.increment();
        verify(view).setCounterValue(3);
    }

    @Test
    public void testDecrement() throws Exception {
        presenter.decrement();
        verify(view).setCounterValue(-1);

        presenter.decrement();
        presenter.decrement();
        verify(view).setCounterValue(-3);
    }

    @Test
    public void testSaveInstanceState() throws Exception {
        presenter.increment();
        presenter.increment();

        verify(view).setCounterValue(2);

        //recreate presenter with restoring state
        Bundle bundle = presenter.getInstanceState();
        presenter = new CounterPresenter();
        presenter.attachView(view);
        presenter.restoreInstanceState(bundle);

        verify(view, times(2)).setCounterValue(2);
    }

    @Test
    public void testWithoutView() throws Exception {
        presenter.detachView();

        //test NPE not happens and view methods are not called after view was detached
        presenter.increment();
        presenter.decrement();

        verify(view, never()).setCounterValue(anyInt());

        //test without attaching view(only for NPE)
        presenter = new CounterPresenter();
        presenter.increment();
        presenter.decrement();
    }
}