package com.example.bhojan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        usernameEditText = findViewById(R.id.edit_text_username);
        passwordEditText = findViewById(R.id.edit_text_password);

        Button loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Perform login validation
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter both a username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Perform authentication
            if (authenticateUser(username, password)) {
                // Authentication successful
                startMainActivity();
                finish();
            } else {
                // Authentication failed
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean authenticateUser(String username, String password) {
        // TODO: Implement user authentication
        // For example, you could query a database or perform network requests
        // to verify the user's credentials

        // For this example, we will just check if the username and password
        // match a hardcoded value
        return username.equals("test_user") && password.equals("test_password");
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}