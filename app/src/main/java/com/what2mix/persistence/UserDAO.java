package com.what2mix.persistence;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.User;

public class UserDAO {

    public void signUp(User user) throws FirebaseAuthException {
        boolean status = false;
        FirebaseAuth mAuth = FirebaseConfig.getFirebaseAuth();
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });

    }

}
