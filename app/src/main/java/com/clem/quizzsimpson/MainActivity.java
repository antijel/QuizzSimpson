package com.clem.quizzsimpson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;


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
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choix de la difficulté : ");

                // add a list
                String[] choice = {"Easy", "Medium", "Hard"};
                builder.setItems(choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // horse
                                launchQuizz(0);
                                break;
                            case 1: // cow
                                launchQuizz(1);
                                break;
                            case 2: // camel
                                launchQuizz(2);
                                break;
                        }
                    }
                });

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case R.id.questionListButton:
                Intent intentList = new Intent(MainActivity.this,QuestionListActivity.class);
                startActivity(intentList);
                break;

            case R.id.aboutButton:
                Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                break;
        }
    }

    private void launchQuizz(int difficultyId) {
        Intent intentQuizz = new Intent(MainActivity.this, SurveyActivity.class);

        //PARSE JSON TO DO//
        switch(difficultyId) {
            case 0:
            //Easy
            ArrayList<String> answerEasy1 = new ArrayList<String>();
            answerEasy1.add("Moe");
            answerEasy1.add("Homer");
            answerEasy1.add("Barney");

            FlashCard questionEasy1 = new FlashCard("Quel est le prénom du père Simpson ?", "none", "homer", answerEasy1, 1);

            ArrayList<String> answerEasy2 = new ArrayList<String>();
            answerEasy2.add("Edna");
            answerEasy2.add("Maude");
            answerEasy2.add("Marge");

            FlashCard questionEasy2 = new FlashCard("Quel est le prénom de la mère Simpson ?", "none", "marge", answerEasy2, 2);

            ArrayList<FlashCard> flashCardsListEasy = new ArrayList<>();
            flashCardsListEasy.add(questionEasy1);
            flashCardsListEasy.add(questionEasy2);

            Quiz quizEasy = new Quiz(flashCardsListEasy, 0);

            intentQuizz.putExtra("flashCardId", 0);
            intentQuizz.putExtra("quiz", quizEasy);
            intentQuizz.putExtra("numberGoodAnswer", 0);

            Log.i("Choice", "launchQuizz: on Easy " + questionEasy2.question);
            break;
            case 1:
            //Medium
            ArrayList<String> answerMedium1 = new ArrayList<String>();
            answerMedium1.add("Nelson");
            answerMedium1.add("Milhouse");
            answerMedium1.add("Bart");

            FlashCard questionMedium1 = new FlashCard("Quel est le prénom du fils Simpson ?", "none", "bart", answerMedium1, 2);

            ArrayList<String> answerMedium2 = new ArrayList<String>();
            answerMedium2.add("Jessica");
            answerMedium2.add("Lisa");
            answerMedium2.add("Francine");

            FlashCard questionMedium2 = new FlashCard("Quel est le prénom de la fille ainée Simpson ?", "none", "lisa", answerMedium2, 1);

            ArrayList<FlashCard> flashCardsListMedium = new ArrayList<>();
            flashCardsListMedium.add(questionMedium1);
            flashCardsListMedium.add(questionMedium2);

            Quiz quizMedium = new Quiz(flashCardsListMedium, 1);

            intentQuizz.putExtra("flashCardId", 0);
            intentQuizz.putExtra("quiz", quizMedium);
            intentQuizz.putExtra("numberGoodAnswer", 0);

            Log.i("Choice", "launchQuizz: on Medium " + questionMedium2.question);
            break;
            case 2:
            //Hard
            ArrayList<String> answerHard1 = new ArrayList<String>();
            answerHard1.add("Orange");
            answerHard1.add("Grise");
            answerHard1.add("Rose");

            FlashCard questionHard1 = new FlashCard("Quel est la couleur de la voiture des Simpson ?", "none", "voiture", answerHard1, 2);

            ArrayList<String> answerHard2 = new ArrayList<String>();
            answerHard2.add("Abraham");
            answerHard2.add("Arnold");
            answerHard2.add("Ned");

            FlashCard questionHard2 = new FlashCard("Quel est le prénom du grand-père Simpson ?", "none", "grandpa", answerHard2, 0);

            ArrayList<FlashCard> flashCardsListHard = new ArrayList<>();
            flashCardsListHard.add(questionHard1);
            flashCardsListHard.add(questionHard2);

            Quiz quizHard = new Quiz(flashCardsListHard, 2);

            intentQuizz.putExtra("flashCardId", 0);
            intentQuizz.putExtra("quiz", quizHard);
            intentQuizz.putExtra("numberGoodAnswer", 0);

            Log.i("Choice", "launchQuizz: on Hard " + questionHard2.question);
            break;
        }

        startActivity(intentQuizz);

    }
}
