package com.what2mix.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.what2mix.R;
import com.what2mix.business.UserBO;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.User;
import com.what2mix.exception.InputEmailException;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputPasswordException;

public class SignUpActivity extends AppCompatActivity {

    private UserBO bo;
    private User user = null;
    private EditText etSignUpName, etSignUpEmail, etSignUpPassword;
    private Button btSignUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        assignElements();
        auth = FirebaseConfig.getFirebaseAuth();

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void assignElements() {
        etSignUpName = findViewById(R.id.etSignUpName);
        etSignUpEmail = findViewById(R.id.etSignUpEmail);
        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        btSignUp = findViewById(R.id.btSignUp);
    }

    // FIXME Erro de parâmetro nulo
    private void register() {
        String name = etSignUpName.getText().toString();
        String email = etSignUpEmail.getText().toString();
        String password = etSignUpPassword.getText().toString();

        try {

            user = bo.validateRegister(name, email, password);
            System.out.println("===========================================");
            System.out.println("validou");
            System.out.println("===========================================");
            auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        System.out.println("===========================================");
                        System.out.println("registrou no autenticate");
                        System.out.println("===========================================");
                        Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                        bo.register(user);
                        System.out.println("===========================================");
                        System.out.println("registuo no database");
                        System.out.println("===========================================");

                        finish();

                    } else {
                        String exception = "";

                        try {
                            throw task.getException();

                        } catch (FirebaseAuthWeakPasswordException e) {
                            exception = "Senha inválida!\nDigite uma senha mais forte.";

                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            exception = "Email inválido!\nDigite um email válido.";

                        } catch (FirebaseAuthUserCollisionException e) {
                            exception = "Esse usuário já cadastrado!";

                        } catch (Exception e) {
                            exception = "Erro ao cadastrar usuário!" + e.getMessage();
                            e.printStackTrace();

                        }
                        Toast.makeText(getApplicationContext(), exception, Toast.LENGTH_LONG).show();

                    }
                }
            });

        } catch (InputNameException e) {
            etSignUpName.setError(e.getMessage());

        } catch (InputEmailException e) {
            etSignUpEmail.setError(e.getMessage());

        } catch (InputPasswordException e) {
            etSignUpPassword.setError(e.getMessage());
        }
    }
}
