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
