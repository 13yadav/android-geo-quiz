package com.joker.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.joker.geoquiz.answer_is_true";
    private boolean answer;
    Button btnShowAnswer;
    TextView answerTV;

    public static Intent newIntent(Context packageContext, boolean isAnswerTrue){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, isAnswerTrue);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answer = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        answerTV = findViewById(R.id.answer_tv);
        btnShowAnswer = findViewById(R.id.btn_show_answer);
        btnShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer) answerTV.setText(R.string.btn_true);
                else answerTV.setText(R.string.btn_false);
            }
        });
    }
}
