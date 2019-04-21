package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

    }

    /* Changing screen */
    public void LoginPessoalScreen(View view) {
        Intent intent = new Intent(this, LoginPessoal.class);
        startActivity(intent);
    }

    /* Changing screen */
    public void CadastroPessoalScreen(View view) {
        Intent intent = new Intent(this, CadastroPessoal.class);
        startActivity(intent);
    }

    /* Changing activity when touching anywhere on the screen */
    public void AjudarScreen(View view) {
        Intent intent = new Intent(this, AjudarActivity.class);
        startActivity(intent);
    }

    /* Changing activity when touching anywhere on the screen */
    public void AdotarScreen(View view) {
        Intent intent = new Intent(this, AdotarActivity.class);
        startActivity(intent);
    }

}
