package com.kvest.mvvm_research.screen.counter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kvest.mvvm_research.R;

/**
 * Created by kvest on 03.06.16.
 */
public class CounterActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, CounterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_activity);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.first_counter, CounterFragment.newInstance());
            transaction.add(R.id.second_counter, CounterFragment.newInstance());
            transaction.commit();
        }
    }
}
