package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TitlePage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_page);

        Button btnShareApp;
        Intent shareIntent;

        btnShareApp = findViewById(R.id.btnShare);
        btnShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareCmt = "This is a great app. Try it out!";
                myIntent.putExtra(Intent.EXTRA_TEXT,shareCmt);
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }
        });

        Button buttonProceed = findViewById(R.id.btnPlay);
        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainMenu();

            }
        });
    }
    private void startMainMenu(){

        Intent intent = new Intent(TitlePage.this, MainPage.class);
        startActivity(intent);
    }
}
