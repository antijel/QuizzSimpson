package com.clem.quizzsimpson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView versionTextView = findViewById(R.id.versionTextView);
        TextView quizzNameTextView = findViewById(R.id.quizzNameTextView);
        TextView nameTextView = findViewById(R.id.nameTextView);

        versionTextView.setText("Version : " + BuildConfig.VERSION_NAME);
    }
}
