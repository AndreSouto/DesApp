package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

public class PerfilUsuario extends AppCompatActivity {

    String email;
    TextView mTxtFullName, mTxtAge, mTxtEmail, mtxtLocal, mTxtAdress, mTxtPhone, mTxtHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FF32C8BE");
        window.setStatusBarColor(statusBarColor);

        // Referência das Views
        mTxtFullName = findViewById(R.id.perfil_usuario_txt_nomecompleto);
        mTxtAge = findViewById(R.id.perfil_usuario_txt_idade);
        mTxtEmail = findViewById(R.id.perfil_usuario_txt_email);
        mTxtAdress = findViewById(R.id.perfil_usuario_txt_endereco);
        mtxtLocal = findViewById(R.id.perfil_usuario_txt_local);
        mTxtPhone = findViewById(R.id.perfil_usuario_txt_telefone);
        mTxtHistory = findViewById(R.id.perfil_usuario_txt_historico);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children){
                    Usuario usr = child.getValue(Usuario.class);

                    if (usr.getEmail().equals(email)){
                        mTxtFullName.setText(usr.getFullName().toString());
                        mTxtEmail.setText(usr.getEmail().toString());
                        mTxtAge.setText(String.valueOf(usr.getAge()));
                        mtxtLocal.setText(usr.getCity().toString());
                        mTxtAdress.setText(usr.getAddress().toString());
                        mTxtPhone.setText(String.valueOf(usr.getPhone()));
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return false;
        }
    }

    /* Changing activity when touching anywhere on the screen */
    public void cadastroScreen(View view) {
        Intent intent = new Intent(this, CadastroAnimalActivity.class);
        startActivity(intent);
    }
}
