package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    /* Changing screen */
    public void LoginPessoalScreen(View view) {
        Intent intent = new Intent(this, LoginPessoal.class);
        startActivity(intent);
    }

    /* Changing screen */
    public void CadastrarScreen(View view) {
        Intent intent = new Intent(this, LoginPessoal.class);
        startActivity(intent);
    }

}
