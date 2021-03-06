package com.example.meau;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class AdocaoCadastro extends AppCompatActivity {

    Button ajuda, colocarad;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adocao_cadastro);

        Window window = getWindow();
        int statusBarColor = Color.parseColor("#FFFDC72E");
        window.setStatusBarColor(statusBarColor);

        ajuda = (Button)findViewById(R.id.ajuda);
        colocarad = (Button)findViewById(R.id.colocar_adocao);

        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjudaScreen(v);
            }
        });

        colocarad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarAnimal(v);
                ColocaAdocaoScreen(v);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
    }

    /*pega o valor do edit text*/
    private String getTextvalue(int id){
        TextView text = (TextView) findViewById(id);
        return text.getText().toString();
    }

    /*pega o valor do Radiobutton*/
    private String getRadiovalue(int id){
        RadioGroup rgp;
        RadioButton rbtn;

        rgp = (RadioGroup) findViewById(id);
        rbtn = (RadioButton)findViewById(rgp.getCheckedRadioButtonId());

        return rbtn.getText().toString();
    }
    /*pega o valor de um checkbox*/

    private boolean getCheckedBvalue(int id){
        CheckBox chk = (CheckBox) findViewById(id);
        return chk.isChecked();
    }
/*pega os atributos dos elementos do template e coloca em uma classe*/
    private Animal getAttrAnimal(){
        Animal anm = new Animal();

        String nomeanimal = getTextvalue(R.id.cad_animal_edit_nomedoanimal);
        anm.SetNome(nomeanimal);
        String especie = getRadiovalue(R.id.cad_animal_RG_especie);
        anm.SetEspecie(especie);
        String sexo = getRadiovalue(R.id.cad_animal_RG_sexo);
        anm.SetSexo(sexo);
        String porte = getRadiovalue(R.id.cad_animal_RG_porte);
        anm.SetPorte(porte);
        String idade = getRadiovalue(R.id.cad_animal_RG_idade);
        anm.SetIdade(idade);
        boolean brinc = getCheckedBvalue(R.id.cad_animal_CHKB_temperamento_brincalhao);
        anm.SetBrincalhao(brinc);
        boolean timido = getCheckedBvalue(R.id.cad_animal_CHKB_temperamento_timido);
        anm.SetTimido(timido);
        boolean calmo = getCheckedBvalue(R.id.cad_animal_CHKB_temperamento_calmo);
        anm.SetCalmo(calmo);
        boolean guarda = getCheckedBvalue(R.id.cad_animal_CHKB_temperamento_guarda);
        anm.SetGuarda(guarda);
        boolean amoroso = getCheckedBvalue(R.id.cad_animal_CHKB_temperamento_amoroso);
        anm.SetAmoroso(amoroso);
        boolean preguicoso = getCheckedBvalue(R.id.cad_animal_CHKB_temperamento_preguicoso);
        anm.SetPreguicoso(preguicoso);
        boolean vacinado = getCheckedBvalue(R.id.cad_animal_CHKB_saude_vacinado);
        anm.SetVacinado(vacinado);
        boolean vermifugado = getCheckedBvalue(R.id.cad_animal_CHKB_saude_vermifugado);
        anm.SetVermifugado(vermifugado);
        boolean castrado = getCheckedBvalue(R.id.cad_animal_CHKB_saude_castrado);
        anm.SetCastrado(castrado);
        boolean doente = getCheckedBvalue(R.id.cad_animal_CHKB_saude_doente);
        anm.SetDoente(doente);
        String doencas = getTextvalue(R.id.cad_animal_edit_doencas);
        anm.SetDoencas(doencas);
        String hist = getTextvalue(R.id.cad_animal_txt_sobre);
        anm.SetHist(hist);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        anm.setUid(currentFirebaseUser.getUid());

        return anm;
    }
    /*set um checkbox em cinza escuro*/
    private void setBlackBox(int id){
        CheckBox chk = (CheckBox)findViewById(id);
        chk.setTextColor(Color.parseColor("#757575"));
    }
    /*verifica se a checkbox idl ta checada e muda a cor id1/id2/id3*/
    private boolean getCheckedBSB(int idl,int id1,int id2, int id3){
        boolean check = getCheckedBvalue(idl);
        if(check){
            setBlackBox(id1);
            setBlackBox(id2);
            setBlackBox(id3);
        }
        return check;
    }
   /*pega todos os atributos dos campos de adoçao da tela e retorna*/
    private TipoAdocao getAttrAdt(){
        TipoAdocao adt = new TipoAdocao();
        boolean termoadt = getCheckedBvalue(R.id.checktermoad);
        adt.setTermoadocao(termoadt);
        boolean fotoscasa = getCheckedBvalue(R.id.fotoscasa);
        adt.setFotoscasa(fotoscasa);
        boolean visitap = getCheckedBvalue(R.id.visitaprevia);
        adt.setVisitaprevia(visitap);
        boolean acomp = getCheckedBSB(R.id.acompanhamento,R.id.ummes,R.id.tresmes,R.id.seismes);
        adt.setAcompanhamento(acomp);
        boolean ummes = getCheckedBvalue(R.id.ummes);
        adt.setUmmes(ummes);
        boolean tresmes = getCheckedBvalue(R.id.tresmes);
        adt.setTresmes(tresmes);
        boolean seismes = getCheckedBvalue(R.id.seismes);
        adt.setSeismes(seismes);
        return adt;
    }

    public void AjudaScreen(View v){
        Intent intent = new Intent(this, AjudaCadastro.class);
        startActivity(intent);
    }

    public void ColocaAdocaoScreen(View v){
        Animal anim = getAttrAnimal();
        TipoAdocao tpadt = getAttrAdt();
        anim.SetTipo(0); /*0 tipo adocao*/
        Intent i = new Intent(this,SucessoCadastroAnimal.class);
        startActivity(i);
    }

    /* Save java object Usuario into firebase */
    public boolean saveFirebase(final Animal obj){

        FirebaseDatabase.getInstance().getReference("Animals")
               .child(obj.getNome())
               .setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                      Toast.makeText(AdocaoCadastro.this,
                     "Registration completed", Toast.LENGTH_LONG).show();

                } else {
                 //display a failure message
                }
            }
        });


        return true;

    }

    /* On click button */
    public void cadastrarAnimal(View view){

        boolean r = saveFirebase(getAttrAnimal());

        if (r) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}
