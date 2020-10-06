package com.what2mix.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import com.what2mix.R;
import com.what2mix.business.UserBO;
import com.what2mix.domain.User;

public class UserFragment extends Fragment {

    private UserBO bo = new UserBO();
    private TextView tvUserName;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser = auth.getCurrentUser();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //TODO atribuir referência ao "tvUserName"
        tvUserName.setText(getTvUserName());

        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private String getTvUserName(){
        User user = testReturnUser();

        String name = user.getName();

        return name;
    }

    private User testReturnUser() {
        User user = null;
        if (firebaseUser != null) {
            user = bo.getLoggedUser(firebaseUser.getEmail().toString());
        }
        return user;
    }

}
