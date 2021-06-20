package com.exercise.thesis.hellodoc.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.atomic.AtomicBoolean;

import static android.content.ContentValues.TAG;

public class DoctorAuthRepository {
    private Application application;
    private static DoctorAuthRepository authRepository;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> firebaseUser;


    public DoctorAuthRepository(Application application) {
        this.application = application;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseUser = new MutableLiveData<>();
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public static DoctorAuthRepository getInstance(Application application) {
        if (authRepository == null) {
            authRepository = new DoctorAuthRepository(application);
        }
        return authRepository;
    }

    public boolean signIn(String email, String password) {
        AtomicBoolean isSuccess = new AtomicBoolean(false);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                isSuccess.set(true);
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");
                Toast.makeText(application, "AAAAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
                firebaseUser.postValue(firebaseAuth.getCurrentUser());
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(application, "Fucked Up", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                isSuccess.set(false);
            }
        });
        return isSuccess.get();
    }

    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                firebaseUser.postValue(firebaseAuth.getCurrentUser());
                Toast.makeText(application, "AAAAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<FirebaseUser> getFirebaseUser() {
        return firebaseUser;
    }
}
