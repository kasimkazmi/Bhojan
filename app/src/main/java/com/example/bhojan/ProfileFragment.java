package com.example.bhojan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bhojan.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.List;

public class ProfileFragment extends Fragment {

    private Button logoutButton;
    private TextView usernameTextView, emailTextView, loginMethodTextView;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize the FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize the GoogleSignInClient instance
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        // Get references to the UI elements
        logoutButton = rootView.findViewById(R.id.button_logout);
        usernameTextView = rootView.findViewById(R.id.userNameTextView);
        emailTextView = rootView.findViewById(R.id.emailTextView);
        loginMethodTextView = rootView.findViewById(R.id.loginMethodTextView);

        // Set up the logout button click listener
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogout();
            }
        });

        // Fetch and display user details
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String username = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            String loginMethod = getLoginMethod(currentUser);
            if (username != null) {
                usernameTextView.setText(username);
            }
            if (email != null) {
                emailTextView.setText(email);
            }
            if (loginMethod != null) {
                loginMethodTextView.setText("Login Method: "+ loginMethod);
            }
        }

        return rootView;
    }

    private String getLoginMethod(FirebaseUser user) {
        List<? extends UserInfo> providerData = user.getProviderData();
        for (UserInfo userInfo : providerData) {
            String providerId = userInfo.getProviderId();
            if (providerId.equals("google.com")) {
                return "Google";
            } else if (providerId.equals("password")) {
                return "Email";
            }
        }
        return null;
    }

    private void handleLogout() {
        // Check if the user is signed in with Google
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (account != null) {
            // Sign out the user from Google
            googleSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // Sign out the user from Firebase
                    firebaseAuth.signOut();

                    // Show a toast message to indicate successful logout
                    Toast.makeText(getActivity(), "Logout successful", Toast.LENGTH_SHORT).show();

                    // Start the LoginActivity
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                    // Finish the MainActivity
                    getActivity().finish();
                }
            });
        } else {
            // Sign out the user from Firebase
            firebaseAuth.signOut();

            // Show a toast message to indicate successful logout
            Toast.makeText(getActivity(), "Logout successful", Toast.LENGTH_SHORT).show();

            // Start the LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

            // Finish the MainActivity
            getActivity().finish();
        }
    }

    // Initialize UI elements and perform other setup tasks here
}