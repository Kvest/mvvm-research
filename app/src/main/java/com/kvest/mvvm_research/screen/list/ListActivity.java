package com.kvest.mvvm_research.screen.list;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;


import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.mvvm.BaseActivity;
import com.kvest.mvvm_research.databinding.ListActivityBinding;

/**
 * Created by kvest on 04.06.16.
 */
public class ListActivity extends BaseActivity<ListViewModel> {
    private BottomSheetBehavior bottomSheetBehavior;

    public static void start(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.list_activity);
        binding.setViewModel(viewModel);

        initView(binding);
    }

    private void initView(ListActivityBinding binding) {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.editPanel);

        binding.fab.setOnClickListener(v -> showEditPanel());
    }

    private void showEditPanel() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @NonNull
    @Override
    protected ListViewModel createViewModel() {
        return new ListViewModel();
    }
}
