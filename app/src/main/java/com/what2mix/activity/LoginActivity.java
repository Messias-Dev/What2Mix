package com.what2mix.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.what2mix.R;
import com.what2mix.business.UserBO;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.User;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputPasswordException;

public class LoginActivity extends AppCompatActivity {

    private UserBO bo = new UserBO();
    private User user = null;
    private EditText etLoginEmail, etLoginPassword;
    private Button btLogin;
    private TextView tvLoginSignUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        assignElements();
        auth = FirebaseConfig.getFirebaseAuth();

        tvLoginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void assignElements(){
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btLogin = findViewById(R.id.btLogin);
        tvLoginSignUp = findViewById(R.id.tvLoginSignUp);
    }

    private void login(){
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        try {

            user = bo.validateLogin(email, password);

            if (user != null){
                auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            openUserActivity();
                        }

                        else{

                            String exception = "";

                            try{
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                exception = "Usuário e senha não correspondem";
                            } catch(FirebaseAuthInvalidUserException e){
                                exception = "Usuário inválido ou não cadastrado";
                            } catch (Exception e){
                                exception = "Erro ao realizar login";
                                e.printStackTrace();
                            }

                            Toast.makeText(getApplicationContext(), exception, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        } catch (InputNameException e) {
            etLoginEmail.setError(e.getMessage());
        } catch (InputPasswordException e) {
            etLoginPassword.setError(e.getMessage());
        }
    }

    private void openUserActivity(){
        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null){
            openUserActivity();
        }
    }

    public void goToAddIngredient(View view) {
        Intent intent = new Intent(getApplicationContext(), IngredientsImprovisedActivity.class);
        startActivity(intent);
    }
}