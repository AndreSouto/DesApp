package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class AdotarActivity extends AppCompatActivity {

    ImageButton buttonPequi;
    ImageButton buttonBidu;
    ImageButton buttonAlec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adotar);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);

        buttonPequi = (ImageButton) findViewById(R.id.pequi);
        buttonBidu = (ImageButton) findViewById(R.id.bidu);
        buttonAlec = (ImageButton) findViewById(R.id.alec);

        buttonPequi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PequiScreen(v);
            }
        });

        buttonBidu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BiduScreen(v);
            }
        });

        buttonAlec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlecScreen(v);
            }
        });

    }

    public void PequiScreen(View v){
        Intent intent = new Intent(this, Pequi.class);
        startActivity(intent);
    }

    public void BiduScreen(View v){
        Intent intent = new Intent(this, Bidu.class);
        startActivity(intent);
    }

    public void AlecScreen(View v){
        Intent intent = new Intent(this, Alec.class);
        startActivity(intent);
    }
}