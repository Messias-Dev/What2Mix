package com.what2mix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.what2mix.R;
import com.what2mix.config.FirebaseConfig;

public class UserActivity extends AppCompatActivity {

    private Button bt;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);
        bt = findViewById(R.id.btSignOut);
        auth = FirebaseConfig.getFirebaseAuth();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void signOut(){
        auth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

}