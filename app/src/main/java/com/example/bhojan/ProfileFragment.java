package com.example.bhojan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private Button logoutButton;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        logoutButton = rootView.findViewById(R.id.button_logout);
        firebaseAuth = FirebaseAuth.getInstance();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogout();
            }
        });

        return rootView;
    }

    private void handleLogout() {
        firebaseAuth.signOut();
        Toast.makeText(getActivity(), "Logout successful", Toast.LENGTH_SHORT).show();

        // Start the LoginActivity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

        // Finish the MainActivity
        getActivity().finish();
    }

    // Initialize UI elements and perform other setup tasks here

//        return rootView;
}


