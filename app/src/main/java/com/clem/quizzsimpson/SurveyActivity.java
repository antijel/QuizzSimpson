package com.clem.quizzsimpson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyActivity extends AppCompatActivity {

    public Quiz quiz;
    public int indexFlashCard;
    public int numberGoodAnswer;
    public FlashCard flashCardOnly;
    public FlashCard flashCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Context context = getApplicationContext();
        Intent quizzIntent = getIntent();

        //Retrieve quiz information's and index of the FlashCard
        indexFlashCard = quizzIntent.getIntExtra("flashCardId",0);
        numberGoodAnswer = quizzIntent.getIntExtra("numberGoodAnswer",0);

        if(quizzIntent.hasExtra("flashCard")){
            flashCardOnly = quizzIntent.getParcelableExtra("flashCard");
            flashCard = flashCardOnly;
            Log.i("Question", "onCreate: " + flashCardOnly.question);
        }else{
            quiz = quizzIntent.getParcelableExtra("quiz");
            flashCard = quiz.flashCardList.get(indexFlashCard);
        }

        int idImage = context.getResources().getIdentifier("drawable/"+flashCard.imageName, null, context.getPackageName());

        // MAJ UI
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(flashCard.question);
        TextView questionNumberTextView = findViewById(R.id.questionNumberTextView);
        if (quiz != null) {
            questionNumberTextView.setText(indexFlashCard + 1 + "/" + quiz.flashCardList.size());
        }
        ImageView imageView = findViewById(R.id.questionImageView);
        imageView.setImageResource(idImage);
        RadioButton answer1 = findViewById(R.id.answerRadioButton1);
        answer1.setText(flashCard.answerList.get(0));
        RadioButton answer2 = findViewById(R.id.answerRadioButton2);
        answer2.setText(flashCard.answerList.get(1));
        RadioButton answer3 = findViewById(R.id.answerRadioButton3);
        answer3.setText(flashCard.answerList.get(2));
        Button validateButton = findViewById(R.id.validateButton);
        validateButton.setText("Valider");
        validateButton.setTag("validate");

        //Validate Button Pressed
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button validateButton = findViewById(R.id.validateButton);

                //IF User want to check his response
                if(validateButton.getTag() == "validate"){
                    checkAnswer(flashCard);

                //IF Question is answered and User want to go to the next question
                }else if(validateButton.getTag() == "next"){
                    Intent intentQuizzNext = getIntent();
                    indexFlashCard = indexFlashCard + 1 ;
                    intentQuizzNext.putExtra("flashCardId", indexFlashCard);
                    intentQuizzNext.putExtra("numberGoodAnswer", numberGoodAnswer);
                    startActivity(intentQuizzNext);

                //IF Quizz is finished and User want to see results
                }else if(validateButton.getTag() == "result"){
                    Intent intentResult = new Intent(SurveyActivity.this, ResultActivity.class);
                    if(flashCardOnly != null){
                        intentResult.putExtra("numberGoodAnswer", numberGoodAnswer + "/1");
                        intentResult.putExtra("ratio",((float)numberGoodAnswer/1)*100);
                    }else{
                        intentResult.putExtra("difficultyId",quiz.difficultyId );
                        intentResult.putExtra("numberGoodAnswer", numberGoodAnswer + "/" + quiz.flashCardList.size());
                        intentResult.putExtra("ratio",((float)numberGoodAnswer/(float)quiz.flashCardList.size())*100);
                    }
                    startActivity(intentResult);
                }
            }
        });
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    //Function to check if the answer given is true and modify UI in consequence
    private void checkAnswer(FlashCard flashCard) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        //UI
        RadioButton radioButtonChecked = findViewById(radioGroup.getCheckedRadioButtonId());
        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView answerTextView = findViewById(R.id.answerTextView1);
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

        if(flashCardOnly != null){
            validateButton.setText("Resultat");
            validateButton.setTag("result");
            return;
        }
        if(indexFlashCard + 1 == quiz.flashCardList.size()){
            validateButton.setText("Resultat");
            validateButton.setTag("result");
        }else{
            validateButton.setText("Question suivante");
            validateButton.setTag("next");
        }
    }
}
