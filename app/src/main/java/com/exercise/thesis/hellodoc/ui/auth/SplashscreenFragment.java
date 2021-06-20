package com.exercise.thesis.hellodoc.ui.auth;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.exercise.thesis.hellodoc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashscreenFragment extends Fragment {

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splashscreen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        new Handler(Looper.myLooper()).postDelayed(() -> {
            String isFirstTime = sharedPreferences.getString("first_time", "");
            if (!isFirstTime.equals("NO")) {
                Navigation.findNavController(view).navigate(R.id.action_splashscreenFragment_to_aboutFragment);
                sharedPreferences.edit().putString("first_time", "NO").apply();
            } else {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();
                    Toast.makeText(getContext(), " " + email, Toast.LENGTH_SHORT).show();
                    if (email == null)
                        Navigation.findNavController(view).navigate(R.id.action_splashscreenFragment_to_homepageFragment);
                    else
                        Navigation.findNavController(view).navigate(R.id.action_splashscreenFragment_to_doctorProfileFragment);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_splashscreenFragment_to_welcomeFragment);

                }
            }
        }, 1500);
    }
}