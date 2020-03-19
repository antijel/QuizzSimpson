package com.clem.quizzsimpson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent resultIntent = getIntent();

        TextView difficultyTextView = findViewById(R.id.QuestionTextView);
        TextView ratioTextView = findViewById(R.id.ratioTextView);
        TextView percentageTextView = findViewById(R.id.percentageTextView);

        switch(resultIntent.getIntExtra("difficultyId",0)){
            case 0:
                difficultyTextView.setText("Facile");
                break;
            case 1:
                difficultyTextView.setText("Intermédiaire");
                break;
            case 2:
                difficultyTextView.setText("Difficile");
                break;
        }

        ratioTextView.setText(resultIntent.getStringExtra("numberGoodAnswer"));
        percentageTextView.setText(resultIntent.getFloatExtra("ratio",0) + "%");
    }

    public void onBackPressed(){
        Intent intentMenu = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intentMenu);
    }
}
