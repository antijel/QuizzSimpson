package com.clem.quizzsimpson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {

    public Quiz quiz;
    public int indexFlashCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Intent quizzIntent = getIntent();

        //Retrieve quiz information's and index of the FlashCard
        indexFlashCard = quizzIntent.getIntExtra("flashCardId",0);
        quiz = quizzIntent.getParcelableExtra("quiz");

        final FlashCard flashCard = quiz.flashCardList.get(indexFlashCard);

        // MAJ UI
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(flashCard.question);
        TextView questionNumberTextView = findViewById(R.id.questionNumberTextView);
        questionNumberTextView.setText(indexFlashCard + "/" + quiz.flashCardList.size());
        RadioButton answer1 = findViewById(R.id.answerRadioButton1);
        answer1.setText(flashCard.answerList.get(0));
        RadioButton answer2 = findViewById(R.id.answerRadioButton2);
        answer2.setText(flashCard.answerList.get(1));
        RadioButton answer3 = findViewById(R.id.answerRadioButton3);
        answer3.setText(flashCard.answerList.get(2));
        Button validateButton = findViewById(R.id.validateButton);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button validateButton = findViewById(R.id.validateButton);

                //IF User want to check his response
                if(validateButton.getText() == "Valider"){
                    checkAnswer(flashCard);

                //IF User want to go to the next question
                }else if(validateButton.getText() == "Question suivante"){
                    Intent intentQuizz = new Intent(SurveyActivity.this, SurveyActivity.class);
                    intentQuizz.putExtra("flashCardId", indexFlashCard + 1);
                    intentQuizz.putExtra("quiz", quiz);
                    startActivity(intentQuizz);

                //IF Quizz is finished
                }else if(validateButton.getText() == "Voir les resultat"){
                    //Go back to menu
                }
            }
        });

    }

    //Function to check if the answer given is true
    private void checkAnswer(FlashCard flashCard) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        Log.i("Button", "checkAnswer: "+ radioGroup.getCheckedRadioButtonId());

        //UI
        RadioButton radioButtonChecked = findViewById(radioGroup.getCheckedRadioButtonId());
        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView answerTextView = findViewById(R.id.answerTextView);
        Button validateButton = findViewById(R.id.validateButton);

        Log.i("Answer", "checkAnswer: " + radioButtonChecked.getText() + " = " + flashCard.answerList.get(flashCard.goodAnswerId));
        if(radioButtonChecked.getText() == flashCard.answerList.get(flashCard.goodAnswerId)){
            resultTextView.setText("Bonne réponse !!");
            //Log.i("Answer", "checkAnswer: Good");

        }else{
            resultTextView.setText("Mauvaise réponse ..");
            //Log.i("Answer", "checkAnswer: Bad");
        }

        //MAJ UI
        answerTextView.setText("La bonne réponse est : " + flashCard.answerList.get(flashCard.goodAnswerId));
        validateButton.setText("Question suivante");
    }


}
