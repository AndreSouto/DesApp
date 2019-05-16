package com.example.meau;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    Button mBtnLogin;
    EditText mTxtEmail, mTxtSenha;
    GoogleApiClient mGoogleApiClient;
    SignInButton mBtSignInGoogle;
    private FirebaseAuth mAuth;
    private final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        // Referência das Views
        mBtnLogin = findViewById(R.id.login_btn_login);
        mTxtEmail = findViewById(R.id.login_edit_email);
        mTxtSenha = findViewById(R.id.login_edit_senha);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailSignIn();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Toast.makeText(LoginActivity.this, "Usuário já está logado!",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void emailSignIn() {
        mAuth.signInWithEmailAndPassword(mTxtEmail.getText().toString(), mTxtSenha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("EmailPasswordLogin", "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Autenticado com sucesso!",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), PerfilUsuario.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("EmailPasswordLogin", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Autenticação falhou.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        // ...
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount acct = task.getResult(ApiException.class);
//                    GoogleSignInAccount acct = result.getSignInAccount();
                    AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
                    mAuth.signInWithCredential(credential)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        final FirebaseUser user = mAuth.getCurrentUser();
                                        UserDatabaseHelper.getUserWithUid(user.getUid(), new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.getValue() == null) {
                                                    Usuario usuario = new Usuario(user.getUid(), user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString());

                                                    Intent intent = new Intent(LoginActivity.this, CadastroPessoalActivity.class);
                                                    Bundle bundle = new Bundle();
                                                    bundle.putSerializable(Usuario.class.getName(), usuario);
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                                else {
                                                    UserHelper.setUserModel(LoginActivity.this, dataSnapshot.getValue(Usuario.class));
                                                    finish();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                            }
                                        });
                                        Toast.makeText(getApplicationContext(), "Você fez login como " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), "Erro! Não foi possível realizar o login.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } catch (ApiException ignored) {
                }

            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("ErrorLogin", connectionResult.getErrorMessage());
    }
}
