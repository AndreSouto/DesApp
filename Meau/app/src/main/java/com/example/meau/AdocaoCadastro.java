package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AdocaoCadastro extends AppCompatActivity {

    Button ajuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adocao_cadastro);

        ajuda = (Button)findViewById(R.id.ajuda);

        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjudaScreen(v);
            }
        });
    }

    public void AjudaScreen(View v){
        Intent intent = new Intent(this, AjudaCadastro.class);
        startActivity(intent);
    }
}
