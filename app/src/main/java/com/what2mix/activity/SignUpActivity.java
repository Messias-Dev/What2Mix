package com.what2mix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.what2mix.R;
import com.what2mix.business.UserBO;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.exception.DataInsufficientException;

public class SignUpActivity extends AppCompatActivity {

    private EditText etSignUpName, etSignUpEmail, etSignUpPassword;
    private Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        assignElements();

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void assignElements(){
        etSignUpName = findViewById(R.id.etSignUpName);
        etSignUpEmail = findViewById(R.id.etSignUpEmail);
        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        btSignUp = findViewById(R.id.btSignUp);
    }

    private void register(){
        String name = etSignUpName.getText().toString();
        String email = etSignUpEmail.getText().toString();
        String password = etSignUpPassword.getText().toString();

        try {
            new UserBO().register(name, email, password);
        } catch (FirebaseAuthException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            System.out.println("==================================================================================");
            System.out.println(e.getMessage());
            System.out.println("==================================================================================");
        } catch (DataInsufficientException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            System.out.println("==================================================================================");
            System.out.println(e.getMessage());
            System.out.println("==================================================================================");
        }
    }

}