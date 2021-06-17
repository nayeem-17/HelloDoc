package com.exercise.thesis.hellodoc.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.exercise.thesis.hellodoc.R;


public class SigninFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button loginButton = view.findViewById(R.id.login);
        Button registerButton = view.findViewById(R.id.signInToRegister);
        loginButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_signinFragment_to_doctorProfileFragment);
        });
        registerButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_signinFragment_to_signupFragment);
        });
    }
}