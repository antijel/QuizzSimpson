package com.clem.quizzsimpson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {

    private List<FlashCard> questions;
    private QuestionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        questions = new ArrayList<>();

        for(int i = 0 ; i < 15 ; i++){
            ArrayList<String> answerEasy1 = new ArrayList<String>();
            answerEasy1.add("Moe");
            answerEasy1.add("Homer");
            answerEasy1.add("Barney");

            ArrayList<String> answerEasy2 = new ArrayList<String>();
            answerEasy2.add("Edna");
            answerEasy2.add("Maude");
            answerEasy2.add("Marge");

            questions.add(new FlashCard("Quel est le prénom du père Simpson ?", "none", "homer.jpg", answerEasy1, 1));
            questions.add(new FlashCard("Quel est le prénom de la mère Simpson ?", "none", "marge.jpg", answerEasy2, 2));

            ArrayList<String> answerMedium1 = new ArrayList<String>();
            answerMedium1.add("Nelson");
            answerMedium1.add("Milhouse");
            answerMedium1.add("Bart");

            ArrayList<String> answerMedium2 = new ArrayList<String>();
            answerMedium2.add("Jessica");
            answerMedium2.add("Lisa");
            answerMedium2.add("Francine");

            questions.add(new FlashCard("Quel est le prénom du fils Simpson ?", "none", "bart.jpg", answerMedium1, 2));
            questions.add(new FlashCard("Quel est le prénom de la fille ainée Simpson ?", "none", "lisa.jpg", answerMedium2, 1));

            ArrayList<String> answerHard1 = new ArrayList<String>();
            answerHard1.add("Orange");
            answerHard1.add("Grise");
            answerHard1.add("Rose");

            ArrayList<String> answerHard2 = new ArrayList<String>();
            answerHard2.add("Abraham");
            answerHard2.add("Arnold");
            answerHard2.add("Ned");

            questions.add(new FlashCard("Quel est la couleur de la voiture des Simpson ?", "none", "voiture.jpg", answerHard1, 2));
            questions.add(new FlashCard("Quel est le prénom du grand-père Simpson ?", "none", "granpa.jpg", answerHard2, 0));

        }

        adapter = new QuestionAdapter(questions);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
