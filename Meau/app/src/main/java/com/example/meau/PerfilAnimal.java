package com.example.meau;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.functions.FirebaseFunctions;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

public class PerfilAnimal extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mImgImage;
    TextView mTxtName, mTxtGender, mTxtPostage, mTxtAge, mTxtCity, mTxtCastrated, mTxtDewormed, mTxtVaccinated, mTxtDisease, mTxtTemperament, mTxtRequiriments, mTxtAbout, mTxtAboutTitle;
    Button mButton, mBtnAvail, mBtnInterest;
    Animal mAnimal;
    final int ACCEPT_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_animal);

        // Suporte para ActionBar
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        // Referência das Views
        mImgImage = findViewById(R.id.perfil_animal_img_fotoanimal);
        mTxtName = findViewById(R.id.perfil_animal_txt_nome);
        mTxtGender = findViewById(R.id.perfil_animal_txt_sexo);
        mTxtPostage = findViewById(R.id.perfil_animal_txt_porte);
        mTxtAge = findViewById(R.id.perfil_animal_txt_idade);
        mTxtCity = findViewById(R.id.perfil_animal_txt_local);
        mTxtCastrated = findViewById(R.id.perfil_animal_txt_castrado);
        mTxtDewormed = findViewById(R.id.perfil_animal_txt_vermifugado);
        mTxtVaccinated = findViewById(R.id.perfil_animal_txt_vacinado);
        mTxtDisease = findViewById(R.id.perfil_animal_txt_doencas);
        mTxtTemperament = findViewById(R.id.perfil_animal_txt_temper);
        mTxtRequiriments = findViewById(R.id.perfil_animal_txt_exigencias);
        mTxtAbout = findViewById(R.id.perfil_animal_txt_about);
        mButton = findViewById(R.id.perfil_animal_btn_adotar);
        mTxtAboutTitle = findViewById(R.id.perfil_animal_txt_title_about);
        mBtnAvail = findViewById(R.id.perfil_animal_btn_mudar_disp);
        mBtnInterest = findViewById(R.id.perfil_animal_btn_ver_inter);

        // Recebe os argumentos do Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && !bundle.isEmpty()) {
            mAnimal = (Animal) bundle.getSerializable(Animal.class.getName());
            if (mAnimal == null) {
                String petUid = bundle.getString("UID_PET", "");

                if (petUid.isEmpty()) finish();

                PetDatabaseHelper.getPetWithUid(petUid, new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) finish();

                        mAnimal = dataSnapshot.getValue(Animal.class);
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
        mToolbar.setTitle(mAnimal.getName());
        Glide.with(this).load(mAnimal.getImageUrl()).into(mImgImage);
        mTxtName.setText(mAnimal.getName());
        mTxtGender.setText(mAnimal.getGender());
        mTxtPostage.setText(mAnimal.getPostage());
        mTxtAge.setText(mAnimal.getAge());
        mTxtCity.setText(mAnimal.getCity());
        mTxtDisease.setText(mAnimal.getDisease());
        mTxtTemperament.setText(mAnimal.getTemperament());
        mTxtRequiriments.setText(mAnimal.getRequiriments());
        mTxtAbout.setText(mAnimal.getAbout());
        mTxtCastrated.setText(mAnimal.isCastrated() ? R.string.yess : R.string.Noo);
        mTxtDewormed.setText(mAnimal.isDewormed() ? R.string.yess : R.string.Noo);
        mTxtVaccinated.setText(mAnimal.isVaccinated() ? R.string.yess : R.string.Noo);
        mTxtAboutTitle.setText(String.format(getString(R.string.mais_sobre), mAnimal.getName()));

        if (mAnimal.getUserUid().equals(UserHelper.getUserModel(this).getUid())) {
            mButton.setVisibility(View.GONE);
            if (mAnimal.isAvailable()) {
                mBtnAvail.setText(R.string.perfil_animal_tornar_indisponível);
            } else {
                mBtnAvail.setText(R.string.perfil_animal_tornar_disponível);
            }
            mBtnAvail.setVisibility(View.VISIBLE);
            mBtnInterest.setVisibility(View.VISIBLE);
        } else {
            mBtnAvail.setVisibility(View.GONE);
            mBtnInterest.setVisibility(View.GONE);
            mButton.setVisibility(View.VISIBLE);
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(PerfilAnimal.this)
                        .setTitle("Atenção!")
                        .setMessage("Tem certeza que deseja adotar este animal?")
                        .setPositiveButton(R.string.yess, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PetUserInterestModel petUserInterestModel = new PetUserInterestModel(mAnimal.getUid(), UserHelper.getUserModel(PerfilAnimal.this).getUid());
                                InterestDatabaseHelper.createInterest(petUserInterestModel, new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        Toast.makeText(PerfilAnimal.this, "Interesse realizado com sucesso", Toast.LENGTH_SHORT).show();

                                        // Notificação
                                        UserDatabaseHelper.getUserWithUid(mAnimal.getUserUid(), new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot snapshot) {
                                                if (snapshot.getValue() == null) return;

                                                Usuario usuario = snapshot.getValue(Usuario.class);
                                                if (usuario.getToken() == null || usuario.getToken().isEmpty())
                                                    return;

                                                // Envia a notificação
                                                Map<String, Object> data = new HashMap<>();
                                                data.put("type", "adopt");
                                                data.put("pet", mAnimal.getName());
                                                data.put("petUid", mAnimal.getUid());
                                                data.put("user", UserHelper.getUserModel(PerfilAnimal.this).getShortName());
                                                data.put("token", usuario.getToken());
                                                FirebaseFunctions.getInstance()
                                                        .getHttpsCallable("sendNotification")
                                                        .call(data);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError error) {

                                            }
                                        });

                                        finish();
                                    }
                                });
                            }
                        })
                        .setNegativeButton(R.string.Noo, null).show();
            }
        });

        mBtnInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mAnimal.isAvailable()) {
                    Toast.makeText(PerfilAnimal.this, "O animal deve estar disponível para adoção.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), AcceptActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable(Animal.class.getName(), mAnimal);
                    intent.putExtras(b);
                    startActivityForResult(intent, ACCEPT_ACTIVITY);
                }
            }
        });

        mBtnAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PerfilAnimal.this)
                        .setTitle("Atenção!")
                        .setMessage("Tem certeza que deseja mudar a disponibilidade desse animal?")
                        .setPositiveButton(R.string.yess, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAnimal.setAvailable(!mAnimal.isAvailable());
                                PetDatabaseHelper.updatePet(mAnimal, new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        Toast.makeText(PerfilAnimal.this, "A disponibilidade foi alterada.",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                                if (mAnimal.isAvailable()) {
                                    mBtnAvail.setText(R.string.perfil_animal_tornar_indisponível);
                                } else {
                                    mBtnAvail.setText(R.string.perfil_animal_tornar_disponível);
                                }
                            }
                        })
                        .setNegativeButton(R.string.Noo, null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ACCEPT_ACTIVITY && resultCode == RESULT_OK) {
            finish();
        }
    }
}
