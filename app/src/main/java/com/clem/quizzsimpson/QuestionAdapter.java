package com.clem.quizzsimpson;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<FlashCard> questions;

    public QuestionAdapter(List<FlashCard> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent ,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlashCard flashCard = questions.get(position);
        Log.i("Liste", "onBindViewHolder: Réponses : "+ flashCard.answerList.get(0) + " - " + flashCard.answerList.get(1) + " - " + flashCard.answerList.get(2));
        //holder.answer1.setText(flashCard.answerList.get(0));
        //holder.answer2.setText(flashCard.answerList.get(1));
        //holder.answer3.setText(flashCard.answerList.get(2));
        holder.question.setText(flashCard.question);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView image;
        final TextView answer1;
        final TextView answer2;
        final TextView answer3;
        final TextView question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.mediaImageView);
            answer1 = itemView.findViewById(R.id.answerRadioButton1);
            answer2 = itemView.findViewById(R.id.answerRadioButton2);
            answer3 = itemView.findViewById(R.id.answerRadioButton3);
            question = itemView.findViewById(R.id.QuestionTextView);

        }
    }
}
