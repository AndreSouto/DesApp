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

public class AjudaCadastro extends AppCompatActivity {

    Button Adocao,Apadrinhar,proc_ajuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda_cadastro);

        Adocao = (Button)findViewById(R.id.adocao);
        Apadrinhar = (Button)findViewById(R.id.apadrinhar);
        proc_ajuda = (Button)findViewById(R.id.procurar_ajuda);

        Adocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeScreen(v,AdocaoCadastro.class);
            }
        });

        Apadrinhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeScreen(v,ApadrinhamentoCadastro.class);
            }
        });

        proc_ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcuraAjuda(v);
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

    /*pega todos os atributos do campo de ajuda da tela e retorna*/

    private TipoAjuda getAttrAjd(){
        TipoAjuda ajd = new TipoAjuda();

        boolean alimento = getCheckedBvalue(R.id.alimentacao);
        ajd.setAlimento(alimento);
        boolean auxfin = getCheckedBvalue(R.id.auxfinanceiro);
        ajd.setAuxfinanceiro(auxfin);
        boolean medicamento = getCheckedBvalue(R.id.medicamento);
        ajd.setMedicamento(medicamento);
        boolean objetos = getCheckedBvalue(R.id.objetos);
        ajd.setObjetos(objetos);

        String nomemed = getTextvalue(R.id.nome_medicamento);
        ajd.setNomemedicamento(nomemed);
        String specobj = getTextvalue(R.id.esp_objetos);
        ajd.setSpecobjetos(specobj);
        return ajd;
    }

    public void ProcuraAjuda(View v){
        Animal anm = getAttrAnimal();
        TipoAjuda ajd = getAttrAjd();
        anm.SetTipo(2); /*tipo ajuda*/
        ChangeScreen(v, SucessoCadastroAnimal.class);
    }

    public void ChangeScreen(View v,Class screen){
        Intent i = new Intent(this,screen);
        startActivity(i);
    }
}
