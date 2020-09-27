package com.what2mix.persistence;

import com.google.firebase.auth.FirebaseAuth;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.User;

public class UserDAO {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void signUp(User user){
        auth = FirebaseConfig.getFirebaseAuth();
        String email = user.getEmail();
        String password = user.getPassword();

        auth.createUserWithEmailAndPassword(email, password);
    }
}
