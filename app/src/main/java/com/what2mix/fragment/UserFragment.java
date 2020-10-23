package com.what2mix.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import com.what2mix.R;
import com.what2mix.activity.IngredientsImprovisedActivity;
import com.what2mix.activity.LoginActivity;
import com.what2mix.activity.SignUpActivity;
import com.what2mix.business.UserBO;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.User;

public class UserFragment extends Fragment {

    private UserBO bo = new UserBO();
    private TextView tvUserName;
    private Button btnLoggout;
    private DatabaseReference reference;
    private FirebaseAuth auth = FirebaseConfig.getFirebaseAuth();
    private FirebaseUser firebaseUser = auth.getCurrentUser();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        tvUserName = view.findViewById(R.id.tvUsername);

        btnLoggout =  view.findViewById(R.id.btnLoggout);

        tvUserName.setText(firebaseUser.getDisplayName());

        btnLoggout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        super.onViewCreated(view, savedInstanceState);

    }

}
