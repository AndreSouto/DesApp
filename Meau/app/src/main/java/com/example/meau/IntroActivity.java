package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class IntroActivity extends AppCompatActivity {

    Button adt,ajuda,cadastrar,login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

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
                CadastroScreen(v);
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
        if( mAuth.getCurrentUser() != null )
        {
            FirebaseAuth.getInstance().signOut();
            login.setText("Login");
        }
        else
        {
            Intent intent = new Intent(this, LoginPessoal.class);
            startActivity(intent);
        }
    }

    /* Changing screen */
    public void CadastroScreen(View view) {

        Intent intent;
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            intent = new Intent(this, AdocaoCadastro.class);
        }
        else {
            intent = new Intent(this, CadastroActivity.class);
        }
        startActivity(intent);
    }

    /* Changing activity when touching anywhere on the screen */
    public void AjudarScreen(View view) {
        Intent intent = new Intent(this, AjudarActivity.class);
        startActivity(intent);
    }

    public void TermoScreen(View view){

        Intent intent;
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            intent = new Intent(this, TermoAdocao.class);
        }
        else {
            intent = new Intent(this, CadastroActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            login.setText("LOGOUT");
        }
    }

}
