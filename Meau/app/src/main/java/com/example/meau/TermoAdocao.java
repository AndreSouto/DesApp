package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TermoAdocao extends AppCompatActivity {

    Button termoadt,termoapd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_adocao);

        termoadt = (Button)findViewById(R.id.termoadocaobutao);
        termoadt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdotarScreen(v);
            }
        });

        termoapd = (Button)findViewById(R.id.termoapadrinhamentobotao);
        termoapd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmaTermo(v);
            }
        });
    }

    /* Changing activity when touching anywhere on the screen */
    public void AdotarScreen(View view) {
        Intent intent = new Intent(this, MainFActivity.class);
        startActivity(intent);
    }

    public void ConfirmaTermo(View view){
        Intent intent = new Intent(this, ConfimaTermos.class);
        startActivity(intent);
    }
}
