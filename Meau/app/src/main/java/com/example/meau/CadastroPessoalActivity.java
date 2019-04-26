package com.example.meau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroPessoalActivity extends AppCompatActivity {

    TextView nome, idade, email, estado, endereco, cidade, telefone;
    TextView nomeusu, senha, confsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoal);
        FirebaseApp.initializeApp(this);

        nome = findViewById(R.id.nome);
        idade = findViewById(R.id.idade);
        email = findViewById(R.id.email);
        estado = findViewById(R.id.estado);
        endereco = findViewById(R.id.endereco);
        cidade = findViewById(R.id.cidade);
        telefone = findViewById(R.id.telefone);
        nomeusu = findViewById(R.id.nomeusuario);
        senha = findViewById(R.id.senha);
        confsenha = findViewById(R.id.confsenha);

    }

    /* Getting text from text views */
    public Usuario getText(View view) {

        Usuario usuario = new Usuario();
        usuario.setName(nome.getText().toString());
        usuario.setAge(idade.getText().toString());
        usuario.setAddress(endereco.getText().toString());
        usuario.setState(estado.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setCity(cidade.getText().toString());
        usuario.setPhoneNumber(telefone.getText().toString());
        usuario.setNameUsu(nomeusu.getText().toString());
        usuario.setPassword(senha.getText().toString());

        return usuario;

    }

    /* Save java object Usuario into firebase */
    public void saveFirebase(Usuario obj){

        FirebaseApp.initializeApp(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue(obj);

    }

    /* On click button */
    public void cadastrarPessoa(View view){

        saveFirebase(getText(view));

    }
}
