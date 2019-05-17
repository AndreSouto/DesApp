package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    String email;
    TextView userName, userEmail, userAge, userLocation, userAddress,
             userPhone, userUserName, userHistoric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FF32C8BE");
        window.setStatusBarColor(statusBarColor);

        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userAge = findViewById(R.id.user_age);
        userLocation = findViewById(R.id.user_location);
        userAddress = findViewById(R.id.user_address);
        userPhone = findViewById(R.id.user_phone);
        userUserName = findViewById(R.id.user_username);
        userHistoric = findViewById(R.id.user_historic);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children){
                    Usuario usr = child.getValue(Usuario.class);

                    if (usr.getEmail().equals(email)){
                        userName.setText(usr.getName().toString());
                        userEmail.setText(usr.getEmail().toString());
                        userAge.setText(usr.getAge().toString());
                        userLocation.setText(usr.getCity().toString());
                        userAddress.setText(usr.getAddress().toString());
                        userPhone.setText(usr.getPhoneNumber().toString());
                        userUserName.setText(usr.getNameUsu().toString());
                        userHistoric.setText("0");
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /* Changing activity when touching anywhere on the screen */
    public void cadastroScreen(View view) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }
}
