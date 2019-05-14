package com.example.meau;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class AdocaoCadastro extends AppCompatActivity {

    Button ajuda, colocarad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adocao_cadastro);

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
                ColocaAdocaoScreen(v);
            }
        });
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

        String nomeanimal = getTextvalue(R.id.nomeanimal);
        anm.SetNome(nomeanimal);
        String especie = getRadiovalue(R.id.especie);
        anm.SetEspecie(especie);
        String sexo = getRadiovalue(R.id.sexo);
        anm.SetSexo(sexo);
        String porte = getRadiovalue(R.id.porte);
        anm.SetPorte(porte);
        String idade = getRadiovalue(R.id.idade);
        anm.SetIdade(idade);
        boolean brinc = getCheckedBvalue(R.id.brincalhao);
        anm.SetBrincalhao(brinc);
        boolean timido = getCheckedBvalue(R.id.timido);
        anm.SetTimido(timido);
        boolean calmo = getCheckedBvalue(R.id.calmo);
        anm.SetCalmo(calmo);
        boolean guarda = getCheckedBvalue(R.id.guarda);
        anm.SetGuarda(guarda);
        boolean amoroso = getCheckedBvalue(R.id.amoroso);
        anm.SetAmoroso(amoroso);
        boolean preguicoso = getCheckedBvalue(R.id.preguiçoso);
        anm.SetPreguicoso(preguicoso);
        boolean vacinado = getCheckedBvalue(R.id.vacinado);
        anm.SetVacinado(vacinado);
        boolean vermifugado = getCheckedBvalue(R.id.vermifugado);
        anm.SetVermifugado(vermifugado);
        boolean castrado = getCheckedBvalue(R.id.castrado);
        anm.SetCastrado(castrado);
        boolean doente = getCheckedBvalue(R.id.doente);
        anm.SetDoente(doente);
        String doencas = getTextvalue(R.id.doencas);
        anm.SetDoencas(doencas);
        String hist = getTextvalue(R.id.historia);
        anm.SetHist(hist);

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
}
