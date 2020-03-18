package com.clem.quizzsimpson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

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

                //Difficulty choice TO DO//

                launchQuizz(0);

                break;

            case R.id.questionListButton:
                Log.i("Button", "onClick: Question List");
                break;

            case R.id.aboutButton:
                Log.i("Button", "onClick: About");
                Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                break;
        }
    }

    private void launchQuizz(int difficultyId) {
        Intent intentQuizz = new Intent(MainActivity.this, SurveyActivity.class);

        //PARSE JSON TO DO//

        ArrayList<String> answer1 = new ArrayList<String>();
        answer1.add("Moe");
        answer1.add("Homer");
        answer1.add("Barney");;
        FlashCard question1 = new FlashCard("Quel est le prénom du père Simpson ?","none","homer.jpg",answer1,1);

        ArrayList<String> answer2 = new ArrayList<String>();
        answer1.add("Edna");
        answer1.add("Maude");
        answer1.add("Marge");;
        FlashCard question2 = new FlashCard("Quel est le prénom de la mère Simpson ?","none","marge.jpg",answer2,2);

        ArrayList<FlashCard> flashCardsList = new ArrayList<>();
        flashCardsList.add(question1);
        flashCardsList.add(question2);

        Quiz quiz = new Quiz(flashCardsList,0);

        intentQuizz.putExtra("flashCardId", 0);
        intentQuizz.putExtra("quiz", quiz);

        startActivity(intentQuizz);
    }
}
