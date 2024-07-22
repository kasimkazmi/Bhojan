package com.example.bhojan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bhojan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    // Declare the FirebaseAuth instance
    private FirebaseAuth firebaseAuth;

    // Declare the EditText for email
    private EditText emailEditText;
    private ProgressBar progressBar;

    // Declare the Button for reset password
    private Button resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        // Hide the loader by default
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        // Initialize the FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Get references to the EditText and Button
        emailEditText = findViewById(R.id.edit_text_email);
        resetPasswordButton = findViewById(R.id.button_send_reset_link);
// Add a TextWatcher to the EditText to enable/disable the reset button based on the input
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this use case
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check if the email input field is empty or not
                if (TextUtils.isEmpty(s)) {
                    // Disable the reset button if the email input field is empty
                    resetPasswordButton.setEnabled(false);
                } else {
                    // Enable the reset button if the email input field is not empty
                    resetPasswordButton.setEnabled(true);
                    resetPasswordButton.setBackgroundColor(getResources().getColor(R.color.themeColor));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this use case
            }
        });
        // Set up the reset password button click listener
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();

                // Check if the email is not empty
                if (!TextUtils.isEmpty(email)) {
                    // Disable the button to prevent double clicks
                    resetPasswordButton.setEnabled(false);

                    // Show the ProgressBar before sending the reset password email
                    progressBar.setVisibility(View.VISIBLE);

                    // Send the reset password email
                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(ForgotPassword.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // Hide the ProgressBar
                                    progressBar.setVisibility(View.GONE);

                                    // Enable the button
                                    resetPasswordButton.setEnabled(true);

                                    if (task.isSuccessful()) {
                                        Toast.makeText(ForgotPassword.this, "Password reset link sent to your email", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(ForgotPassword.this, "Failed to send password reset link", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Show a message to the user to enter their email address
                    Toast.makeText(ForgotPassword.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                }
            }

        });


        TextView BackToLogin = findViewById(R.id.text_view_back_to_login);
        BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the ProgressBar before sending the reset password email
                progressBar.setVisibility(View.VISIBLE);
                // Remove the startActivity line if you want to keep the user on the same screen
                startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                finish();
            }
        });}}