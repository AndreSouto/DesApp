package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meau.R;

public class AjudaCadastro extends AppCompatActivity {

    Button Adocao,Apadrinhar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda_cadastro);

        Adocao = (Button)findViewById(R.id.adocao);
        Apadrinhar = (Button)findViewById(R.id.apadrinhar);

        Adocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeScreen(v,AdocaoCadastro.class);
            }
        });

        Apadrinhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeScreen(v,ApadrinhamentoCadastro.class);
            }
        });
    }

    public void ChangeScreen(View v,Class screen){
        Intent i = new Intent(this,screen);
        startActivity(i);
    }
}
