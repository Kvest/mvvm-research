package com.kvest.mvvm_research.screen.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kvest.mvvm_research.R;

/**
 * Created by roman on 6/10/16.
 */
public class UserActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
    }
}
