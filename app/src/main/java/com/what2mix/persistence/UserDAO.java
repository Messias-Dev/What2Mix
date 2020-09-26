package com.what2mix.persistence;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.User;


public class UserDAO {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public String signUp(User user){
        auth = FirebaseConfig.getFirebaseAuth();
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        final String message = "";

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                message.concat("Usuário cadastrado com sucesso!");
                } else {
                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        message.concat(e.getMessage());
                    }
                }
            }
        });
        return message;
    }

}
