package com.example.meau;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.meau.R;

public class ApadrinhamentoCadastro extends AppCompatActivity {

    Button ajuda,procpadrin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apadrinhamento_cadastro);

        ajuda = (Button)findViewById(R.id.ajuda);
        procpadrin = (Button)findViewById(R.id.procurar_padrinho);

        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeScreen(v, AjudaCadastro.class);
            }
        });

        procpadrin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcuraPadrim(v);
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
    /*pega todos os atributos dos campos de apadrinhamento da tela e retorna*/
    private TipoApadrinhamento getAttrAp(){
        TipoApadrinhamento apd = new TipoApadrinhamento();

        boolean termoap = getCheckedBvalue(R.id.checktermoap);
        apd.setTermoap(termoap);
        boolean auxfinanceiro = getCheckedBSB(R.id.auxfinanceiro,R.id.alimentacao,R.id.saude,R.id.objetos);
        apd.setAuxfinanceiro(auxfinanceiro);
        boolean alimentacao = getCheckedBvalue(R.id.alimentacao);
        apd.setAlimentacao(alimentacao);
        boolean saude = getCheckedBvalue(R.id.saude);
        apd.setSaude(saude);
        boolean objetos = getCheckedBvalue(R.id.objetos);
        apd.setObjetos(objetos);
        boolean visita = getCheckedBvalue(R.id.visitas);
        apd.setVisita(visita);

        return apd;
    }

    public void ProcuraPadrim(View v){
        Animal anm = getAttrAnimal();
        TipoApadrinhamento apd = getAttrAp();
        anm.SetTipo(1); /*tipo apadrinhamento*/
        ChangeScreen(v, SucessoCadastroAnimal.class);
    }

    public void ChangeScreen(View v,Class screen){
        Intent i = new Intent(this,screen);
        startActivity(i);
    }
}
