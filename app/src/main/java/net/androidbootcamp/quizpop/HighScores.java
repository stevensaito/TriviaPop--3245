package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;



public class HighScores extends AppCompatActivity {

    ArrayList<String> array = new ArrayList<>();
    ListView showList;
    ArrayAdapter<String> adapter;

    TextView textViewName;
    TextView textViewScore;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "test";
    public static final String SCORE = "score";

    private String text;
    private String textScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        loadData();
        //get intents
        Intent i = getIntent();
        final String userName = i.getStringExtra("username");
        final int userScore = i.getIntExtra("Score", 0);
        int num = userScore;
        String finalScore = Integer.toString(num);
        Button btnPlay = findViewById(R.id.btnPlayAgain);


        //showList = findViewById(R.id.listView);
        textViewName = findViewById(R.id.textViewName);
        textViewScore = findViewById(R.id.textViewScore);


            //Convert int into string

            String name = "";
            String fScore = "";
            if (userName!="" && finalScore!=""){

                textViewName.setText("Name : " +userName);
                textViewScore.setText("Score: " +finalScore);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(TEXT, i.getStringExtra("username"));
                editor.putString(SCORE, String.valueOf(i.getIntExtra("Score",0)));
                editor.apply();


            }else{

                SharedPreferences sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                String np = sp.getString("TEXT","");
                String ps = sp.getString("SCORE",""); 
                textViewName.setText("Name: " +name);
                textViewScore.setText("Score: "+fScore);

            }
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HighScores.this, MainPage.class));
            }
        });

    }


    public void loadData(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT,"");
        textScore = sharedPreferences.getString(SCORE,"");

    }


}
