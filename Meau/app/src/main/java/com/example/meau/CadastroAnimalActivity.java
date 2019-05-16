package com.example.meau;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

public class CadastroAnimalActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TextView txtName, txtDiesease, txtAbout;
    Button btImage, btSave;
    RadioGroup rgEspecie, rgGender, rgPostage, rgAge;
    CheckBox cbTemperamentBrinc, cbTemperamentTim, cbTemperamentCalm, cbTemperamentGuard, cbTemperamentAmor,
            cbTemperamentPreg, cbVaccinated, cbDewormed, cbCastrated, cbRequisitsTerms, cbRequisitsPhoto,
            cbRequisitsVisit, cbRequisitsAdopt;

    Animal mAnimal;
    Usuario mUsuario;
    String specie, gender, postage, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animal);

        // Suporte para ActionBar
        mToolbar = findViewById(R.id.cad_animal_Toolbar);
        mToolbar.setTitle(R.string.register_animal);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        // Referência das Views
        txtName = findViewById(R.id.cad_animal_edit_nomedoanimal);
        rgEspecie = findViewById(R.id.cad_animal_RG_especie);
        rgGender = findViewById(R.id.cad_animal_RG_sexo);
        rgPostage = findViewById(R.id.cad_animal_RG_porte);
        rgAge = findViewById(R.id.cad_animal_RG_idade);
        cbTemperamentBrinc = findViewById(R.id.cad_animal_CHKB_temperamento_brincalhao);
        cbTemperamentTim = findViewById(R.id.cad_animal_CHKB_temperamento_timido);
        cbTemperamentCalm = findViewById(R.id.cad_animal_CHKB_temperamento_calmo);
        cbTemperamentGuard = findViewById(R.id.cad_animal_CHKB_temperamento_guarda);
        cbTemperamentAmor = findViewById(R.id.cad_animal_CHKB_temperamento_amoroso);
        cbTemperamentPreg = findViewById(R.id.cad_animal_CHKB_temperamento_preguicoso);
        cbRequisitsTerms = findViewById(R.id.cad_animal_CHKB_termoadocao);
        cbRequisitsVisit = findViewById(R.id.cad_animal_CHKB_visita);
        cbRequisitsPhoto = findViewById(R.id.cad_animal_CHKB_fotoscasa);
        cbRequisitsAdopt = findViewById(R.id.cad_animal_CHKB_acompanhamento);
        cbVaccinated = findViewById(R.id.cad_animal_CHKB_saude_vacinado);
        cbDewormed = findViewById(R.id.cad_animal_CHKB_saude_vermifugado);
        cbCastrated = findViewById(R.id.cad_animal_CHKB_saude_castrado);
        txtDiesease = findViewById(R.id.cad_animal_edit_doencas);
        txtAbout = findViewById(R.id.cad_animal_edit_sobre);
        btImage = findViewById(R.id.cad_animal_btn_fotosanimal);
        btSave = findViewById(R.id.cad_animal_btn_colocarAdocao);

        rgEspecie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cad_animal_RB_especie_cachorro:
                        specie = "Cachorro";
                        break;
                    case R.id.cad_animal_RB_especie_gato:
                        specie = "Gato";
                        break;
                }
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cad_animal_RB_sexo_macho:
                        gender = "Macho";
                        break;
                    case R.id.cad_animal_RB_sexo_femea:
                        gender = "Fêmea";
                        break;
                }
            }
        });

        rgAge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cad_animal_RB_idade_filhote:
                        age = "Filhote";
                        break;
                    case R.id.cad_animal_RB_idade_adulto:
                        age = "Adulto";
                        break;
                    case R.id.cad_animal_RB_idade_idoso:
                        age = "Idoso";
                        break;
                }
            }
        });

        rgPostage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cad_animal_RB_porte_pequeno:
                        postage = "Pequeno";
                        break;
                    case R.id.cad_animal_RB_porte_medio:
                        postage = "Médio";
                        break;
                    case R.id.cad_animal_RB_porte_grande:
                        postage = "Grande";
                        break;
                }
            }
        });

        mUsuario = UserHelper.getUserModel(this);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimal = new Animal(mUsuario.getUid(),
                        txtName.getText().toString(),
                        gender,
                        age,
                        postage,
                        mUsuario.getCity(),
                        mUsuario.getImageUrl(),
                        txtDiesease.getText().toString(),
                        getTemperaments(),
                        getRequisits(),
                        txtAbout.getText().toString(),
                        cbCastrated.isChecked(),
                        cbDewormed.isChecked(),
                        cbVaccinated.isChecked(),
                        true); // No momento do cadastro, o animal é disponível para adoção por padrão
                PetDatabaseHelper.createPet(mAnimal, new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(CadastroAnimalActivity.this, "Animal Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
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

    // Constrói a String que contém os temperamentos
    private String getTemperaments() {
        String s = "";

        if (cbTemperamentBrinc.isChecked()) s += "Brincalhão";
        if (cbTemperamentTim.isChecked()) {
            if (s.length() > 0)
                s += ", Tímido";
            else
                s += "Tímido";
        }
        if (cbTemperamentCalm.isChecked()) {
            if (s.length() > 0)
                s += ", Calmo";
            else
                s += "Calmo";
        }
        if (cbTemperamentGuard.isChecked()) {
            if (s.length() > 0)
                s += ", Guarda";
            else
                s += "Guarda";
        }
        if (cbTemperamentAmor.isChecked()) {
            if (s.length() > 0)
                s += ", Amoroso";
            else
                s += "Amoroso";
        }
        if (cbTemperamentPreg.isChecked()) {
            if (s.length() > 0)
                s += ", Preguiçoso";
            else
                s += "Preguiçoso";
        }

        return s;
    }

    // Constrói a String que contém os requisitos
    private String getRequisits() {
        String s = "";

        if (cbRequisitsTerms.isChecked()) s += "Termo de Adoção";
        if (cbRequisitsVisit.isChecked()) {
            if (s.length() > 0)
                s += ", Visita Prévia";
            else
                s += "Visita Prévia";
        }
        if (cbRequisitsPhoto.isChecked()) {
            if (s.length() > 0)
                s += ", Fotos de Casa";
            else
                s += "Fotos de Casa";
        }
        if (cbRequisitsAdopt.isChecked()) {
            if (s.length() > 0)
                s += ", Acompanhamento pós adoção";
            else
                s += "Acompanhamento pós adoção";
        }

        return s;
    }
}
