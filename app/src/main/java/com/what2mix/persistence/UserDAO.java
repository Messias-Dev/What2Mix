package com.what2mix.persistence;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.User;

import java.util.concurrent.Executor;

public class UserDAO {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void signUp(String email, String password) throws FirebaseAuthException {

        System.out.println("chegou no signUp");
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = auth.getCurrentUser();
                            System.out.println("==================================================================================");
                            System.out.println("funcionou");
                            System.out.println("==================================================================================");

                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.println("==================================================================================");
                            System.out.println("não funcionou");
                            System.out.println("==================================================================================");
                        }

                        // ...
                    }
                });

    }

}
