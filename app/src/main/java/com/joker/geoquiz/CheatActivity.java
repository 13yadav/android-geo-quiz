package com.joker.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.joker.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.joker.geoquiz.answer_shown";

    private boolean answer;
    Button btnShowAnswer;
    TextView answerTV;
    TextView tvBuildNo;

    public static Intent newIntent(Context packageContext, boolean isAnswerTrue){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, isAnswerTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
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

                setAnswerShownResult(true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    int cx = btnShowAnswer.getWidth() / 2;
                    int cy = btnShowAnswer.getHeight() / 2;
                    float radius = btnShowAnswer.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(btnShowAnswer,cx,cy,radius,0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            btnShowAnswer.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                } else{
                    btnShowAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });

        tvBuildNo = findViewById(R.id.tv_build_no);
        int version = Build.VERSION.SDK_INT;
        String api = "API Level " + version;
        tvBuildNo.setText(api);

    }

    public void setAnswerShownResult(boolean isShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isShown);
        setResult(RESULT_OK, data);
    }

}
