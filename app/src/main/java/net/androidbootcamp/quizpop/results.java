package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class results extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_SCORE = "finalScore";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewHighScore;
    private int highScore;

    private List<Question> userList;
    private String name;


    private EditText userText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        //Get intent from last activity
        Intent intent = getIntent();
        //store score to int
        final int score = intent.getIntExtra("Score", 0);

        final int userScore = score;


        //display score
        TextView textViewScore = findViewById(R.id.txtViewUserScore);
        //final EditText fieldEnterName = findViewById(R.id.fieldEnterName);



        textViewScore.setText("Final Score: " + score);

        //get user name
        userText = findViewById(R.id.fieldEnterName);




        Button buttonPlayAgain = findViewById(R.id.btnPlayAgain);
        Button buttonHome = findViewById(R.id.btnHome);
        final Button buttonSubmit = findViewById(R.id.btnResultsSubmit);

        if(score >= 37) {
            buttonSubmit.setVisibility(View.VISIBLE);
            userText.setVisibility(View.VISIBLE);
            //fieldEnterName.setVisibility(View.VISIBLE);
        }
        else {
            buttonSubmit.setVisibility(View.INVISIBLE);
            userText.setVisibility(View.INVISIBLE);
            //fieldEnterName.setVisibility(View.INVISIBLE);
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //store user input string into a variable
                    String value = userText.getText().toString();
                    EditText uName = findViewById(R.id.fieldEnterName);
                    uName.setText(value);

                    //store user score into a variable to be pass to the next class
                    int num = score;
                    Intent i = new Intent(results.this, HighScores.class);
                    i.putExtra("username", value);
                    i.putExtra("Score", num);

                    startActivityForResult(i, 1);




            }
        });



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



}
