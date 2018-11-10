package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Button btnPlayQuiz = findViewById(R.id.btnPlayGame);
        Button btnInstruct = findViewById(R.id.btnTutorial);
        Button btnShare = findViewById(R.id.btnShareGame);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnInstruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainPage.this, tutorial.class));
            }
        });
        btnPlayQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainPage.this, quiz.class));
            }
        });
    }
}
