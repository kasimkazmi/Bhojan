package com.example.bhojan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    // Declare the FirebaseAuth instance
    private FirebaseAuth firebaseAuth;

    // Declare the EditTexts for email and password
    private EditText emailEditText, passwordEditText;

    // Declare the Button for login
    private Button loginButton;

    // Declare a boolean variable to track double back presses
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        // Initialize the FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Get references to the EditTexts for email and password
        emailEditText = findViewById(R.id.edit_text_email);
        passwordEditText = findViewById(R.id.edit_text_password);

        // Get reference to the login Button
        loginButton = findViewById(R.id.button_login);

        // Get the current user from FirebaseAuth
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        // Check if the user is already logged in
        if (currentUser != null) {
            // User is already logged in. Start MainActivity and finish LoginActivity
            startMainActivity();
            finish();
        } else {
            // User is not logged in. Proceed with the login process

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the email and password from the input fields
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    // Check if the email and password are not empty
                    if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                        // Sign in the user with the given email and password
                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Login successful
                                            FirebaseUser user = firebaseAuth.getCurrentUser();
                                            startMainActivity(); // Start the MainActivity
                                            finish(); // Finish the LoginActivity
                                        } else {
                                            // Login failed
                                            Toast.makeText(LoginActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        // Show a message to the user to enter their email and password
                        Toast.makeText(LoginActivity.this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        TextView signupTextView = findViewById(R.id.text_view_signup);
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}