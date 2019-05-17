package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPessoal extends AppCompatActivity {

    TextView email, password;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pessoal);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FF32C8BE");
        window.setStatusBarColor(statusBarColor);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    /* On click button */
    public void loginUsr(View view){

        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginPessoal.this, ProfileActivity.class));
                            }
                            else{
                                Toast.makeText(LoginPessoal.this, task.getException().getMessage(),
                                               Toast.LENGTH_LONG).show();

                            }
                        }
                    });
    }
}
