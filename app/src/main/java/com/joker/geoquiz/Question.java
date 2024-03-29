package com.joker.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean isAnswered;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Question(int textResId, boolean answerTrue, boolean answered){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        isAnswered = answered;
    }
}
