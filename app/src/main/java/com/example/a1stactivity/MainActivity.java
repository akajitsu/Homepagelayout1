package com.example.a1stactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnRegister, btnLogin, btnHelp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnRegister = findViewById(R.id.superButtonRegister);
        btnLogin = findViewById(R.id.superButtonLogin);
        btnHelp = findViewById(R.id.superButtonHelp);

        // Check if user is currently logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Update button UI state to show Logged In / Logout status
            btnLogin.setText("Log Out (" + currentUser.getEmail() + ")");
            btnLogin.setOnClickListener(v -> {
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                recreate(); // Refresh screen state
            });
        } else {
            btnLogin.setText("Already Registered? Log In");
            btnLogin.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            });
        }

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        btnHelp.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Student Voting Portal v1.0 - Firebase Integrated", Toast.LENGTH_SHORT).show();
        });
    }
}