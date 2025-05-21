package com.example.quizhub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 4000; // 4 seconds
    private Handler handler;
    private Runnable navigateToHomeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize the handler and runnable for delayed navigation
        handler = new Handler(Looper.getMainLooper());
        navigateToHomeRunnable = this::navigateToHome;

        // Set up the GIF animation
        ImageView gifImageView = findViewById(R.id.gifImageView);
        Glide.with(this)
                .asGif()
                .load(R.raw.quiz_splash)
                .into(gifImageView);

        // Set up the Get Started button
        Button getStartedButton = findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the delayed navigation callback
                handler.removeCallbacks(navigateToHomeRunnable);
                // Navigate to home screen
                navigateToHome();
            }
        });

        // Set up the delayed navigation if button is not clicked
        handler.postDelayed(navigateToHomeRunnable, SPLASH_DELAY);
    }

    private void navigateToHome() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // Close the splash activity so it can't be returned to
    }

    @Override
    protected void onDestroy() {
        // Remove any pending callbacks to prevent leaks
        if (handler != null && navigateToHomeRunnable != null) {
            handler.removeCallbacks(navigateToHomeRunnable);
        }
        super.onDestroy();
    }
}