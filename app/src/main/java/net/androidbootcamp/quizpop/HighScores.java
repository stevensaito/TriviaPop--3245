package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;

import java.util.ArrayList;


public class HighScores extends AppCompatActivity {



    ArrayList<String> array = new ArrayList<String>();
    ListView showList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);


        //get intents
        Intent i = getIntent();
        final String userName = i.getStringExtra("username");
        final int userScore = i.getIntExtra("Score",0);

        showList = findViewById(R.id.listView);
        //Convert int into string
        int num = userScore;
        String finalScore = Integer.toString(num);

        String userData = "Name: " + userName + " Score: " + finalScore;
        //add to arraylist and display to list view
        /*
        array.add(userData);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HighScores.this, android.R.layout.simple_list_item_1,array);
        showList.setAdapter(adapter);


        //SharedPreferences to save user data
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("User" , userData);
        */


            array.add("Name: " +userName);
            array.add("Score: " +finalScore);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(HighScores.this, android.R.layout.simple_list_item_1,array);
            showList.setAdapter(adapter);


            //SharedPreferences to save user data
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("User" , userName);
            editor.putString("Score", finalScore);
            editor.commit();



        //Button to play again
        Button btnPlay =findViewById(R.id.btnPlayAgain);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HighScores.this,quiz.class));
            }
        });


    }

}
