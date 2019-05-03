package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.FirebaseApp;

public class IntroActivity extends AppCompatActivity {

    Button adt,ajuda,cadastrar,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        FirebaseApp.initializeApp(this);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);

        adt = (Button)findViewById(R.id.adotar);
        adt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermoScreen(v);
            }
        });

        ajuda = (Button)findViewById(R.id.ajudar);
        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjudarScreen(v);
            }
        });

        cadastrar = (Button)findViewById(R.id.cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastroPessoalScreen(v);
            }
        });

        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPessoalScreen(v);
            }
        });
    }

    /* Changing screen */
    public void LoginPessoalScreen(View view) {
        Intent intent = new Intent(this, LoginPessoal.class);
        startActivity(intent);
    }

    /* Changing screen */
    public void CadastroPessoalScreen(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    /* Changing activity when touching anywhere on the screen */
    public void AjudarScreen(View view) {
        Intent intent = new Intent(this, AjudarActivity.class);
        startActivity(intent);
    }

    public void TermoScreen(View view){
        Intent intent = new Intent(this, TermoAdocao.class);
        startActivity(intent);
    }

}
