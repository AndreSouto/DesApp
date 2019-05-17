package com.example.meau;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meau.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AnimalProfile extends AppCompatActivity {

    ImageView mImgImage;
    TextView mTxtName, mTxtGender, mTxtPostage, mTxtAge, mTxtDisease, mTxtAbout, mTxtAboutTitle;
    Button mButton;
    Animal mPetModel;
    final int ACCEPT_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_profile);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);

        // Referência das Views
        mImgImage = findViewById(R.id.perfil_animal_img_fotoanimal);
        mTxtName = findViewById(R.id.perfil_animal_txt_nome);
        mTxtGender = findViewById(R.id.perfil_animal_txt_sexo);
        mTxtPostage = findViewById(R.id.perfil_animal_txt_porte);
        mTxtAge = findViewById(R.id.perfil_animal_txt_idade);
        mTxtAbout = findViewById(R.id.perfil_animal_txt_about);
        mButton = findViewById(R.id.perfil_animal_btn_adotar);
        mTxtAboutTitle = findViewById(R.id.perfil_animal_txt_title_about);

        // Recebe os argumentos do Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && !bundle.isEmpty()) {
            mPetModel = (Animal) bundle.getSerializable(Animal.class.getName());
            if (mPetModel == null) {
                String petUid = bundle.getString("UID_PET", "");

                if (petUid.isEmpty()) finish();

                PetDatabaseHelper.getPetWithUid(petUid, new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) finish();

                        mPetModel = dataSnapshot.getValue(Animal.class);
                        fillPet();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        finish();
                    }
                });
            } else {
                fillPet();
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.cancelAll();
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

    // Preenche os dados na tela
    private void fillPet() {
        // Preenche os dados
        mImgImage.setImageResource(R.drawable.alec);
        mTxtName.setText(mPetModel.getNome());
        mTxtGender.setText(mPetModel.getSexo());
        mTxtPostage.setText(mPetModel.getPorte());
        mTxtAge.setText(mPetModel.getIdade());
        mTxtAboutTitle.setText(String.format(getString(R.string.mais_sobre), mPetModel.getNome()));

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid = currentFirebaseUser.getUid();

    }

    public void Adopt(View view){

        Intent intent;
        intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ACCEPT_ACTIVITY && resultCode == RESULT_OK) {
            finish();
        }
    }
}
