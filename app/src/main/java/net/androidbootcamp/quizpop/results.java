package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class results extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_SCORE = "finalScore";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewHighScore;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        int score = intent.getIntExtra("Score", 0);

        TextView textViewScore = findViewById(R.id.txtViewUserScore);
        final EditText fieldEnterName = findViewById(R.id.fieldEnterName);

        textViewScore.setText("Final Score: " + score);

        Button buttonPlayAgain = findViewById(R.id.btnPlayAgain);
        Button buttonHome = findViewById(R.id.btnHome);
        final Button buttonSubmit = findViewById(R.id.btnResultsSubmit);

        if(score >= 37) {
            buttonSubmit.setVisibility(View.VISIBLE);
            fieldEnterName.setVisibility(View.VISIBLE);
        }
        else {
            buttonSubmit.setVisibility(View.INVISIBLE);
            fieldEnterName.setVisibility(View.INVISIBLE);
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fieldEnterName.getText().toString();
            }
        });

        saveQuiz(score);

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(results.this,quiz.class));
            }
        });
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(results.this,MainPage.class));
            }
        });
    }

    private void saveQuiz(int userScore){



    }
}
