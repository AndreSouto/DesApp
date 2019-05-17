package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.example.meau.UserHelper;
import com.example.meau.MainFragment;
import com.example.meau.AnimalFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MyPetsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);
/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, MainFragment.newInstance())
                    .commitNow();
        }
*/
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && !bundle.isEmpty()) {
            if (bundle.getString("opt").matches("my_pets")) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_frame, AnimalFragment.newInstance())
                        .commitNow();
            } else if (bundle.getString("opt").matches("adopt")) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_frame, AnimalFragment.newInstance())
                        .commitNow();
            }
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, AnimalFragment.newInstance())
                    .commitNow();

        }

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

