package com.joker.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button mBtnTrue;
    Button mBtnFalse;
    ImageButton mBtnNext;
    ImageButton mBtnPrev;
    TextView mQuesTV;
    private static final String TAG = "QuizActivity";


    private static final String KEY_INDEX = "index";

    private Question[] quesBank = new Question[]{
            new Question(R.string.ques_india,true),
            new Question(R.string.ques_programming,false),
            new Question(R.string.ques_richer,true),
            new Question(R.string.ques_river,true),
            new Question(R.string.ques_web,false)
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        mQuesTV = findViewById(R.id.tvQues);
        updateQuestion();

        mBtnTrue = findViewById(R.id.btnTrue);
        mBtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mBtnFalse = findViewById(R.id.btnFalse);
        mBtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mBtnNext = findViewById(R.id.btnNext);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % quesBank.length;
                updateQuestion();
            }
        });

        mBtnPrev = findViewById(R.id.btn_prev);
        mBtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0)
                    currentIndex = (currentIndex - 1) % quesBank.length;
                else
                    currentIndex = 0;
                updateQuestion();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "OnStart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume() called");
    }

    private void updateQuestion(){
        int question = quesBank[currentIndex].getTextResId();
        mQuesTV.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = quesBank[currentIndex].isAnswerTrue();
        int messageResId;

        if (userPressedTrue == answerIsTrue)
            messageResId = R.string.correct_toast;
        else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
