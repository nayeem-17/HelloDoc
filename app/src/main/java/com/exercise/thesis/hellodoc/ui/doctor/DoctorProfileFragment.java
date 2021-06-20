package com.exercise.thesis.hellodoc.ui.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.exercise.thesis.hellodoc.R;
import com.exercise.thesis.hellodoc.repository.DoctorAuthRepository;
import com.exercise.thesis.hellodoc.ui.DrawerLocker;

public class DoctorProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((DrawerLocker) getActivity()).setDrawerEnabled(true);

        Button logOutButton = view.findViewById(R.id.log_out);
        logOutButton.setOnClickListener(v -> {

            DoctorAuthRepository.getInstance(getActivity().getApplication()).getFirebaseAuth().signOut();

            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            ((DrawerLocker) getActivity()).setDrawerEnabled(false);

            getActivity().getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            Navigation.findNavController(view).navigate(R.id.action_doctorProfileFragment_to_welcomeFragment);
        });
    }
}