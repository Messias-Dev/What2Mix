package com.what2mix.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import com.what2mix.R;
import com.what2mix.activity.LoginActivity;

public class UserFragment extends Fragment {

    private TextView tvUserName, tvSignOut;
    private FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();

        assignLayoutElements(view);
        setUserName();

        tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

    }

    private void signOut() {
        auth.signOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void assignLayoutElements(View view) {
        tvUserName = view.findViewById(R.id.tvUsername);
        tvSignOut =  view.findViewById(R.id.tvSignOut);
    }

    private void setUserName() {
        String name = auth.getCurrentUser().getDisplayName();
        tvUserName.setText(name);
    }

}
