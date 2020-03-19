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
import android.widget.Toast;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {

    public Quiz quiz;
    public int indexFlashCard;
    public int numberGoodAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Intent quizzIntent = getIntent();

        //Retrieve quiz information's and index of the FlashCard
        if(quizzIntent.hasExtra("flashCardId")){
            indexFlashCard = quizzIntent.getIntExtra("flashCardId",0);
        }
        quiz = quizzIntent.getParcelableExtra("quiz");
        numberGoodAnswer = quizzIntent.getIntExtra("numberGoodAnswer",0);

        final FlashCard flashCard = quiz.flashCardList.get(indexFlashCard);

        // MAJ UI
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(flashCard.question);
        TextView questionNumberTextView = findViewById(R.id.questionNumberTextView);
        questionNumberTextView.setText(indexFlashCard + 1 + "/" + quiz.flashCardList.size());
        RadioButton answer1 = findViewById(R.id.answerRadioButton1);
        answer1.setText(flashCard.answerList.get(0));
        RadioButton answer2 = findViewById(R.id.answerRadioButton2);
        answer2.setText(flashCard.answerList.get(1));
        RadioButton answer3 = findViewById(R.id.answerRadioButton3);
        answer3.setText(flashCard.answerList.get(2));
        Button validateButton = findViewById(R.id.validateButton);
        validateButton.setText("Valider");
        validateButton.setTag("validate");

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button validateButton = findViewById(R.id.validateButton);

                //IF User want to check his response
                if(validateButton.getTag() == "validate"){
                    checkAnswer(flashCard);

                //IF User want to go to the next question
                }else if(validateButton.getTag() == "next"){
                    Intent intentQuizzNext = getIntent();
                    indexFlashCard = indexFlashCard + 1 ;
                    intentQuizzNext.putExtra("flashCardId", indexFlashCard);
                    intentQuizzNext.putExtra("numberGoodAnswer", numberGoodAnswer);
                    startActivity(intentQuizzNext);

                //IF Quizz is finished
                }else if(validateButton.getTag() == "result"){
                    //Go to result
                    //Log.i("Button", "onClick: " + validateButton.getTag());

                    Intent intentResult = new Intent(SurveyActivity.this, ResultActivity.class);
                    intentResult.putExtra("difficultyId",quiz.difficultyId );
                    intentResult.putExtra("numberGoodAnswer", numberGoodAnswer + "/" + quiz.flashCardList.size());
                    intentResult.putExtra("ratio",((float)numberGoodAnswer/(float)quiz.flashCardList.size())*100);

                    Log.i("Answer", "onClick: percentage : "+ numberGoodAnswer + " / " + quiz.flashCardList.size() + " = " +numberGoodAnswer/quiz.flashCardList.size()+ "* 100 = " + (numberGoodAnswer/quiz.flashCardList.size())*100);

                    startActivity(intentResult);
                }
            }
        });
    }

    //Function to check if the answer given is true
    private void checkAnswer(FlashCard flashCard) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        //UI
        RadioButton radioButtonChecked = findViewById(radioGroup.getCheckedRadioButtonId());
        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView answerTextView = findViewById(R.id.answerTextView);
        Button validateButton = findViewById(R.id.validateButton);

        //Check if one radioButton is selected
        if(radioButtonChecked == null){
            Toast.makeText(
                    SurveyActivity.this,
                    "Veuillez sélectionner une réponse !!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //Good Answer
        if(radioButtonChecked.getText() == flashCard.answerList.get(flashCard.goodAnswerId)){
            resultTextView.setText("Bonne réponse !!");
            numberGoodAnswer++;
        //Bad Answer
        }else{
            resultTextView.setText("Mauvaise réponse ..");
        }

        //MAJ UI
        answerTextView.setText("La bonne réponse est : " + flashCard.answerList.get(flashCard.goodAnswerId));

        if(indexFlashCard + 1 == quiz.flashCardList.size()){
            validateButton.setText("Resultat");
            validateButton.setTag("result");
        }else{
            validateButton.setText("Question suivante");
            validateButton.setTag("next");
        }
    }
}
