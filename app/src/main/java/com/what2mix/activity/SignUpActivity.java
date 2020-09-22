package com.what2mix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.what2mix.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText etSignUpName, etSignUpEmail, etSignUpPassword;
    private Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        assignElements();

    }

    private void assignElements(){
        etSignUpName = findViewById(R.id.etSignUpName);
        etSignUpEmail = findViewById(R.id.etLoginEmail);
        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        btSignUp = findViewById(R.id.btSignUp);
    }

}