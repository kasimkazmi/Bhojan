package com.example.bhojan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {

    // Declare EditText fields for username, email, and password
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    // Initialize Firebase Authentication object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        // Initialize EditText fields with corresponding views
        usernameEditText = findViewById(R.id.edit_text_username_signup);
        emailEditText = findViewById(R.id.edit_text_email_signup);
        passwordEditText = findViewById(R.id.edit_text_password_signup);
        confirmPasswordEditText = findViewById(R.id.edit_text_confirm_password_signup);

        // Initialize Firebase Authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        // Set click listener for signup button
        Button signupButton = findViewById(R.id.button_signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Validate user input
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new user account with Firebase Authentication
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign up successful
                                    FirebaseUser user = firebaseAuth.getCurrentUser();

                                    // Set the user's display name to the username
                                    user.updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(username).build());

                                    // Get the current username and login time
                                    String username = user.getDisplayName();
                                    String loginTime = new SimpleDateFormat("EEEE, MMM d", Locale.getDefault()).format(new Date());

                                    // Start HomeFragment with the username and login time
                                    startHomeActivity(username, loginTime);

                                    // Start MainActivity
                                    startMainActivity();

                                    // Finish SignUpActivity
                                    finish();
                                } else {
                                    // Sign up failed
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthException e) {
                                        Toast.makeText(SignUpActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        });
            }
        });

        // Set click listener for login text view
        TextView loginTextView = findViewById(R.id.text_view_login);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    // Start MainActivity
    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Start HomeFragment with the given username and login time
    private void startHomeActivity(String username, String loginTime) {
        // Create a new Bundle object
        Bundle bundle = new Bundle();

        // Put the username and login time as key-value pairs
        bundle.putString("username", username);
        bundle.putString("login_time", loginTime);

        // Create a new instance of the HomeFragment
        HomeFragment homeFragment = new HomeFragment();

        // Pass the Bundle object to the HomeFragment using the setArguments() method
        homeFragment.setArguments(bundle);

        // Start MainActivity and add HomeFragment to the container
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}