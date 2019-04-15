package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    Button menu,adotar,ajudar,cadastrar,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Buttons
        menu = findViewById(R.id.menu);
        adotar = findViewById(R.id.adotar);
        ajudar = findViewById(R.id.ajudar);
        cadastrar = findViewById(R.id.cadastrar);
        login = findViewById(R.id.login);

//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(calc.this,Settings.class));
//                finish();
//            }
//        });

//        adotar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(calc.this,Settings.class));
//                finish();
//            }
//        });

//        ajudar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(calc.this,Settings.class));
//                finish();
//            }
//        });

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
}
