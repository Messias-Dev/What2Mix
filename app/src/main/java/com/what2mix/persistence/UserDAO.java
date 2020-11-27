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
import com.what2mix.domain.Recipe;
import com.what2mix.domain.User;

public class UserDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("users");
    private String email = null;
    private User user = null;

    // Registra usuário no banco de dados
    public void writeNewUser(User userParameter) {

        user = userParameter;

        // Anula a senha do usuário
        user.setPassword(null);
        database.push().setValue(user);

    }

    // Consulta usuário por email no banco de dados
    // FIXME melhorar arquitetura (deixar para final)
    public User findByEmail(final String emailParameter) {

        user = new User();
        email = emailParameter;

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Varre cada usuário no banco de dados
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    // Verifica se o parâmetro corresponde
                    if (email.equals(data.child("email").getValue())) {

                        // Resgata o objeto do banco de dados e popula o com atributos de nome igual ao construtor
                        user.setName(data.child("name").getValue().toString());
                        user.setEmail(data.child("email").getValue().toString());

                        // Seta o id
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
