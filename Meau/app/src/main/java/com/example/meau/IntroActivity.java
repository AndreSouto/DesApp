package com.example.meau;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class IntroActivity extends AppCompatActivity {

    Button mBtLogin, mBtAdopt, mBtRegisterPet;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);

        createNotificationChannel();

        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        UserHelper.getUserModel(this);

        // Confirm token
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Usuario usuario = UserHelper.getUserModel(IntroActivity.this);
                        if (!usuario.getToken().equals(token)) {
                            usuario.setToken(token);
                            UserHelper.setUserModel(IntroActivity.this, usuario);
                        }
                    }
                });

        // Referência de Views
        mBtLogin = findViewById(R.id.bt_main_login);
        mBtAdopt = findViewById(R.id.bt_main_adopt);
        mBtRegisterPet = findViewById(R.id.init_btn_cadastrar_animal);

        // Eventos de Click
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    FirebaseAuth.getInstance().signOut();
                    UserHelper.setUserModel(getApplicationContext(), new Usuario());

                    mBtLogin.setText(R.string.login);
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        mBtAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                Bundle bundle;
                if (mAuth.getCurrentUser() == null) {
                    intent = new Intent(getApplicationContext(), ErroSessaoActivity.class);
                } else {
                    bundle = new Bundle();
                    bundle.putString("opt", "adopt");
                    intent = new Intent(getApplicationContext(), InterActivity.class);
                    intent.putExtras(bundle);
                }

                startActivity(intent);
            }
        });

        mBtRegisterPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                if (mAuth.getCurrentUser() == null) {
                    intent = new Intent(getApplicationContext(), ErroSessaoActivity.class);
                } else intent = new Intent(getApplicationContext(), CadastroAnimalActivity.class);

                startActivity(intent);
            }
        });
    }

    /* Changing activity when touching anywhere on the screen */
    public void AjudarScreen(View view) {
        Intent intent = new Intent(this, AjudarActivity.class);
        startActivity(intent);
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mBtLogin.setText("LOGOUT");
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "meau_notification";
            String description = "meau_notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("meau_notification", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
