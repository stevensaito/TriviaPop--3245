package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HighScores extends AppCompatActivity {

    private TextView userData;
    private TextView userName;
    private List<User>userList;
    private int listCounter = 0;
    private int listCountTotal;
    private User user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        Intent i = getIntent();

        //get intents
        final String userName = i.getStringExtra("username");
        final int userScore = i.getIntExtra("Score",0);

        TextView user = findViewById(R.id.textViewDisplayScores);
        user.setText("Name : " + userName + "  Score: " + userScore);


        User newUser = new User(userName, userScore);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        //dbHelper.addGamer(newUser);
        userList = dbHelper.getAllScores();
        
        listCountTotal = userList.size();


        
        displayAllUserScores();


    }

    private void displayAllUserScores() {
    }
}
