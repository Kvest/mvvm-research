package com.kvest.mvvm_research.screen.counter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kvest on 03.06.16.
 */
public class CounterActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, CounterActivity.class);
        context.startActivity(intent);
    }
}
