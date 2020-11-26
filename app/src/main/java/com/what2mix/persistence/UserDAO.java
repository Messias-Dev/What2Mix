package com.what2mix.persistence;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.Ingredient;
import com.what2mix.domain.User;

public class UserDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("users");
    private String email = null;
    private User user = new User();

    public void writeNewUser(User userParameter) {

        user = userParameter;

        user.setPassword(null);
        database.push().setValue(user);

    }

    // FIXME melhorar arquitetura (deixar para final)
    public User findByEmail(final String emailParameter) {

        email = emailParameter;

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (email.equals(data.child("email").getValue())) {
                        user.setName(data.child("name").getValue().toString());
                        user.setEmail(data.child("email").getValue().toString());
                        user.setId(data.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return user;
    }
}
