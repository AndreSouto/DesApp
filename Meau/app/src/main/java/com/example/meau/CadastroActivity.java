package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FF32C8BE");
        window.setStatusBarColor(statusBarColor);
    }

    /* Changing activity when touching anywhere on the screen */
    public void introScreen(View view) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }

    /* Changing screen */
    public void LoginPessoalScreen(View view) {
        Intent intent = new Intent(this, LoginPessoal.class);
        startActivity(intent);
    }

    /* Changing screen */
    public void CadastrarScreen(View view) {
        Intent intent = new Intent(this, CadastroPessoalActivity.class);
        startActivity(intent);
    }

}
