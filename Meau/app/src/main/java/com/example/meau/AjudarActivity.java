package com.example.meau;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import static java.security.AccessController.getContext;

public class AjudarActivity extends AppCompatActivity {

    ImageButton buttonMargot;
    ImageButton buttonMarie;
    ImageButton buttonSancho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajudar);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);

        buttonMargot = (ImageButton) findViewById(R.id.margot);
        buttonMarie = (ImageButton) findViewById(R.id.marie);
        buttonSancho = (ImageButton) findViewById(R.id.sancho);

        buttonMargot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MargotScreen(v);
            }
        });

        buttonMarie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MarieScreen(v);
            }
        });

        buttonSancho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SanchoScreen(v);
            }
        });

    }

    public void MargotScreen(View v){
        Intent intent = new Intent(this, Margot.class);
        startActivity(intent);
    }

    public void MarieScreen(View v){
        Intent intent = new Intent(this, Marie.class);
        startActivity(intent);
    }

    public void SanchoScreen(View v){
        Intent intent = new Intent(this, Sancho.class);
        startActivity(intent);
    }

}