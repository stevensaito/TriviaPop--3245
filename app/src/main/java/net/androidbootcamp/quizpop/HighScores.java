package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class HighScores extends AppCompatActivity {

    ArrayList<String> array = new ArrayList<String>();
    ListView showList;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "test";
    public static final String SCORE = "score";

    private String text;
    private String textScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        //get intents
        Intent i = getIntent();
        final String userName = i.getStringExtra("username");
        final int userScore = i.getIntExtra("Score", 0);

        showList = findViewById(R.id.listView);
        //Convert int into string
        int num = userScore;
        String finalScore = Integer.toString(num);

        //Button to play again
        Button btnPlay = findViewById(R.id.btnPlayAgain);

        array.add("Name: " +userName);
        array.add("Score: " +finalScore);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HighScores.this, android.R.layout.simple_list_item_1,array);
        showList.setAdapter(adapter);


        //SharedPreferences to save user data
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT , userName);
        editor.putString(SCORE, finalScore);
        editor.commit();


        //loadData();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HighScores.this, quiz.class));
            }
        });

    }
    public void loadData(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT,"");
        textScore = sharedPreferences.getString(SCORE,"");

    }

}
