package com.example.logg;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logg.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class HomeActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Get user name from intent
        String username = getIntent().getStringExtra("username");
        tvWelcome.setText("Welcome,you are signed in as " + username + "!");

        // Set up sign-out config
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Sign out on button click
        btnLogout.setOnClickListener(v -> {
            mGoogleSignInClient.signOut().addOnCompleteListener(task -> {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            });
        });
    }
}
