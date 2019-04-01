package renanz.calculadora;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calc extends AppCompatActivity {
//Elementos da activity

    TextView result;
    EditText number1,number2;
    Button soma,sub,divs,mult,sets;
    float result_num;

//Operações que o usuário pode realizar
    private static final int SOMA = 0;
    private static final int SUB = 1;
    private static final int DIV = 2;
    private static final int MULT = 3;


    //DatabaseHelper mdatabaseHelper;

    //Mensagem para o usuário do tipo toasty
    private void toastmsg(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
/*
    public void addData(String dado){
        boolean insertData = mdatabaseHelper.addOp(dado);

        if(insertData){
            toastmsg("Dado inserido com sucesso");
        }else{
            toastmsg("Falha na inserção");
        }
    }
*/
    /*recebe uma operação dois elementos de editar texto correspondentes aos números inseridos
    pelo usuário e um textView para o resultado*/
    public void Operate(int op, EditText nm1, EditText nm2, TextView result){
        float n1, n2, result_num = 0; //n1 e n2 representam os dois operandos e result_num o resultado
        //String data;

//os valores digitados nas caixas de textos são convertidos em floats e colocados nas várias n1 e n2

        n1 = Float.parseFloat(nm1.getText().toString());
        n2 = Float.parseFloat(nm2.getText().toString());

//A operção é realizada através da variável int op, a qual indica a operação escolhida

        switch (op){
            case SOMA:
                result_num = n1 + n2;
                break;
            case SUB:
                result_num = n1 - n2;
                break;
            case MULT:
                result_num = n1 * n2;
                break;
            case DIV:
                result_num = n1 / n2;
                break;
        }

        result.setText(String.valueOf(result_num));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        //resultado
        result = (TextView)findViewById(R.id.result);
        //operadores
        number1 = (EditText)findViewById(R.id.number1);
        number2 = (EditText)findViewById(R.id.number2);
        //operações
        soma = (Button)findViewById(R.id.soma);
        sub = (Button)findViewById(R.id.sub);
        divs = (Button)findViewById(R.id.divs);
        mult = (Button)findViewById(R.id.multi);

        //configs
        sets = (Button)findViewById(R.id.sets);

        soma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle precision = getIntent().getExtras();
                /*if (precision != null) {
                    int pr = precision.getInt("key");
                  */
                Operate(SOMA,number1,number2,result);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operate(SUB,number1,number2,result);
            }
        });

        divs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operate(DIV,number1,number2,result);
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operate(MULT,number1,number2,result);
            }
        });

        sets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(calc.this,Settings.class));
                finish();
            }
        });
    }
}
