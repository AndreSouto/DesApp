package com.example.meau;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroPessoalActivity extends AppCompatActivity {

    TextView nome, idade, email, estado, endereco, cidade, telefone;
    TextView nomeusu, senha, confsenha;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoal);
        //FirebaseApp.initializeApp(this);

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

        firebaseAuth = FirebaseAuth.getInstance();

    }

    /* Getting text from text views */
    public Usuario getText(View view) {

        Usuario usuario = new Usuario();
        usuario.setName(nome.getText().toString());
        usuario.setAge(idade.getText().toString());
        usuario.setAddress(endereco.getText().toString());
        usuario.setState(estado.getText().toString());
        usuario.setCity(cidade.getText().toString());
        usuario.setPhoneNumber(telefone.getText().toString());
        usuario.setNameUsu(nomeusu.getText().toString());

        return usuario;

    }

    /* Save java object Usuario into firebase */
    public boolean saveFirebase(final Usuario obj){

        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CadastroPessoalActivity.this,
                                                        "Registration completed", Toast.LENGTH_LONG).show();

                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(CadastroPessoalActivity.this, task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                        }
                    }
                });

        return true;

    }

    /* On click button */
    public void cadastrarPessoa(View view){

        boolean r = saveFirebase(getText(view));

        if (r) {
            Intent intent = new Intent(this, LoginPessoal.class);
            startActivity(intent);
        }
    }
}
