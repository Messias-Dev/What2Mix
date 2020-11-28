package com.what2mix.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.what2mix.R;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.fragment.CreateFragment;
import com.what2mix.fragment.SearchFragment;
import com.what2mix.fragment.UserFragment;

public class UserActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setStatusAndActionBar();
        setFragmentOnOpen();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_search:
                        selectedFragment = new SearchFragment();
                        break;

                    case R.id.nav_create:
                        selectedFragment = new CreateFragment();
                        break;

                    case R.id.nav_user:
                        selectedFragment = new UserFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;

            }
        });
    }

    private void setFragmentOnOpen(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new SearchFragment()).commit();
    }

    public void setStatusAndActionBar(){
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}