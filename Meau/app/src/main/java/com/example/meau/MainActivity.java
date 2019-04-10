package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main);
    }

    /** Changing activity when touching anywhere on the screen */
    public void introScreen(View view) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }
}