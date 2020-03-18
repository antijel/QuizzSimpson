package com.clem.quizzsimpson;

import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FlashCard implements Parcelable {

    public final String question;
    public final String soundName;
    public final String imageName;
    public final ArrayList<String> answerList;
    public final int goodAnswerId;


    public FlashCard(String question, String soundName, String imageName, ArrayList<String> answerList, int goodAnswerId) {
        this.question = question;
        this.soundName = soundName;
        this.imageName = imageName;
        this.answerList = answerList;
        this.goodAnswerId = goodAnswerId;
    }

    protected FlashCard(Parcel in) {
        question = in.readString();
        soundName = in.readString();
        imageName = in.readString();
        answerList = in.createStringArrayList();
        goodAnswerId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(soundName);
        dest.writeString(imageName);
        dest.writeStringList(answerList);
        dest.writeInt(goodAnswerId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlashCard> CREATOR = new Creator<FlashCard>() {
        @Override
        public FlashCard createFromParcel(Parcel in) {
            return new FlashCard(in);
        }

        @Override
        public FlashCard[] newArray(int size) {
            return new FlashCard[size];
        }
    };

}
