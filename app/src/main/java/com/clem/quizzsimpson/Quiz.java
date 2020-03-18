package com.clem.quizzsimpson;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Quiz implements Parcelable {

    public final ArrayList<FlashCard> flashCardList;
    public final int difficultyId;

    public Quiz(ArrayList<FlashCard> flashCardList, int difficultyId) {
        this.flashCardList = flashCardList;
        this.difficultyId = difficultyId;
    }

    protected Quiz(Parcel in) {
        flashCardList = in.createTypedArrayList(FlashCard.CREATOR);
        difficultyId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(flashCardList);
        dest.writeInt(difficultyId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };
}
