package com.clem.quizzsimpson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button quizButton = findViewById(R.id.quizzButton);
        final Button questionListButton = findViewById(R.id.questionListButton);
        final Button aboutButton = findViewById(R.id.aboutButton);

        quizButton.setOnClickListener(this);
        questionListButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quizzButton:
                Log.i("Button", "onClick: Quizz");
                break;

            case R.id.questionListButton:
                Log.i("Button", "onClick: Question List");
                break;

            case R.id.aboutButton:
                Log.i("Button", "onClick: About");
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }
    }
}
