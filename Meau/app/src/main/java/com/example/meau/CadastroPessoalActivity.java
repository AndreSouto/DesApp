package com.example.meau;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroPessoalActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TextView txtFullName, txtAge, txtEmail, txtEstate, txtCity, txtAdress, txtNumber, txtPass, txtConfirmPass;
    Button btSave, btImage;
    Usuario usuario;
    private boolean isBundled = true;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoal);

        mAuth = FirebaseAuth.getInstance();
        usuario = new Usuario();

        // Referência das Views
        txtFullName = findViewById(R.id.cad_pessoa_edit_nomecompleto);
        txtAge = findViewById(R.id.cad_pessoa_edit_idade);
        txtEmail = findViewById(R.id.cad_pessoa_edit_email);
        txtEstate = findViewById(R.id.cad_pessoa_edit_UF);
        txtCity = findViewById(R.id.cad_pessoa_edit_cidade);
        txtAdress = findViewById(R.id.cad_pessoa_edit_endereco);
        txtNumber = findViewById(R.id.cad_pessoa_edit_telefone);
        txtPass = findViewById(R.id.cad_pessoa_edit_senha);
        txtConfirmPass = findViewById(R.id.cad_pessoa_edit_confsenha);
        btSave = findViewById(R.id.cad_pessoa_btn_fazercadastro);
        //btImage = findViewById(R.id.cad_pessoa_btn_fotoperfil);

        // Preenche parte das informações do usuário
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && !bundle.isEmpty()) {
            usuario = (Usuario) bundle.getSerializable(Usuario.class.getName());

            txtEmail.setText(usuario.getEmail());
        } else {
            isBundled = false;
        }

        // Eventos de click
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && !isBundled) {
            Toast.makeText(CadastroPessoalActivity.this, "Usuário já está logado.",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void saveUser() {
        usuario.setFullName(txtFullName.getText().toString());

        String[] nameSplit = usuario.getFullName().split(" ");
        int last = nameSplit.length - 1;

        usuario.setShortName(nameSplit[0] + " " + nameSplit[last]);
        usuario.setAge(Integer.valueOf(txtAge.getText().toString()));
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setState(txtEstate.getText().toString());
        usuario.setCity(txtCity.getText().toString());
        usuario.setAddress(txtAdress.getText().toString());
        usuario.setPhone(txtNumber.getText().toString());
        usuario.setPassword(txtPass.getText().toString());

        if (isBundled) {
            UserDatabaseHelper.createUser(usuario, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Toast.makeText(CadastroPessoalActivity.this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else {
            mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("EmailPassword", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                usuario.setUid(user.getUid());
                                UserDatabaseHelper.createUser(usuario, new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        Toast.makeText(CadastroPessoalActivity.this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d("EmailPassword", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(CadastroPessoalActivity.this, "Criação falhou.\nVerifique sua conexão e tente novamente.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });

        }
    }
}
