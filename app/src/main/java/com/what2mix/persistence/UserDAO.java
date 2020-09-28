package com.what2mix.persistence;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.User;

public class UserDAO {

   private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("users");
   private Long maxId = 0L;

    private void writeNewUser(User user) {

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxId = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database.child("users").child(String.valueOf(maxId+1)).setValue(user);
    }
}
