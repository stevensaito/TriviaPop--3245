package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HighScores extends AppCompatActivity {


    private List<User>userList;

    private EditText userText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        Intent i = getIntent();

        //get intents
        final String userName = i.getStringExtra("username");
        final int userScore = i.getIntExtra("Score",0);
        /*
        TextView user = findViewById(R.id.textViewDisplayScores);
        user.setText("Name : " + userName + "  Score: " + userScore);
        */
        userText = findViewById(R.id.editTextEnterName);
        //Button to submit name and scores
        Button btnSubmit = findViewById(R.id.btnSubmitScores);
        //Button to play again
        Button btnPlay =findViewById(R.id.btnPlayAgain);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HighScores.this,quiz.class));
            }
        });

        //User submits their name
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store user input string into a variable
                String value = userText.getText().toString();
                EditText uName = findViewById(R.id.editTextEnterName);
                uName.setText(value);

                //store user score into a variable to be pass to the next class
                int num = userScore;

                TextView user = findViewById(R.id.textViewDisplayScores);
                user.setText("Name : " + value + "  Score: " + num);


            }
        });



        displayAllUserScores();


    }

    private void displayAllUserScores() {
    }
}
