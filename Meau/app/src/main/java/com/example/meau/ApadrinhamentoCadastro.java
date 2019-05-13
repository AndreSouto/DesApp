package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meau.R;

public class ApadrinhamentoCadastro extends AppCompatActivity {

    Button ajuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apadrinhamento_cadastro);

        ajuda = (Button)findViewById(R.id.ajuda);

        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeScreen(v, AjudaCadastro.class);
            }
        });
    }
    public void ChangeScreen(View v,Class screen){
        Intent i = new Intent(this,screen);
        startActivity(i);
    }
}
