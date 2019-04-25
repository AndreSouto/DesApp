package com.example.meau;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import static java.security.AccessController.getContext;

public class AjudarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajudar);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);
    }

}
