package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;

public class InterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter_activity);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }


    /* Changing activity when touching anywhere on the screen */
    public void introScreen(View view) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }

}
