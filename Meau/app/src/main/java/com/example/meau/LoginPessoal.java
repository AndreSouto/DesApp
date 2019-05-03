package com.example.meau;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class LoginPessoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pessoal);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FF32C8BE");
        window.setStatusBarColor(statusBarColor);
    }
}
