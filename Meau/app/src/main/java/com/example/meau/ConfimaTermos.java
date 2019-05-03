package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfimaTermos extends AppCompatActivity {

    Button leg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confima_termos);

        leg = (Button)findViewById(R.id.legs);
        leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrivacidadeScreen(v);
            }
        });

    }

    public void PrivacidadeScreen(View v){
        Intent intent = new Intent(this, Privacidade.class);
        startActivity(intent);
    }
}
