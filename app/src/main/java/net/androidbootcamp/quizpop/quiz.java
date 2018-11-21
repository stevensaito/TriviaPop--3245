package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class quiz extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 15000;

    private TextView txtViewQuestion;
    private TextView txtViewScore;
    private TextView txtViewQuestionCount;
    private TextView txtViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button btnNext;
    private int counter = 0; ///////COMMENTOCMOEMACOREKFJEARLK;VESRKVBKJETBHKSETBHJK

    private ColorStateList txtColourDefaultRb;
    private ColorStateList txtColourDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilis;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtViewQuestion = findViewById(R.id.txtQuestionsView);
        txtViewScore = findViewById(R.id.txtScore);
        txtViewQuestionCount = findViewById(R.id.txtQuestions);
        txtViewCountDown = findViewById(R.id.txtTimer);
        rbGroup = findViewById(R.id.radioGroup_Choice);
        rb1 = findViewById(R.id.radioButton_1);
        rb2 = findViewById(R.id.radioButton_2);
        rb3 = findViewById(R.id.radioButton_3);
        rb4 = findViewById(R.id.radioButton_4);
        btnNext = findViewById(R.id.btnConfirm);

        txtColourDefaultRb = rb1.getTextColors();
        txtColourDefaultCd = txtViewCountDown.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        shownNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){

                    if(rb1.isChecked() || rb2.isChecked()|| rb3.isChecked()||rb4.isChecked()){

                        checkAnswer();

                    }else{

                        Toast.makeText(quiz.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    shownNextQuestion();
                    counter++;
                }
            }
        });
    }

    private void shownNextQuestion() {

        rb1.setTextColor(txtColourDefaultRb);
        rb2.setTextColor(txtColourDefaultRb);
        rb3.setTextColor(txtColourDefaultRb);
        rb4.setTextColor(txtColourDefaultRb);

        rb1.setVisibility(View.VISIBLE);
        rb2.setVisibility(View.VISIBLE);
        rb3.setVisibility(View.VISIBLE);
        rb4.setVisibility(View.VISIBLE);

        rbGroup.clearCheck();

        if(questionCounter < 5){

            currentQuestion = questionList.get(questionCounter);
            txtViewQuestion.setText(currentQuestion.getQuestion());

            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            txtViewQuestionCount.setText("Question " + questionCounter + "/" + 5);
            answered = false;
            btnNext.setText("Confirm");

            timeLeftInMilis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        }else{

            counter = 0;
            finishQuiz(score);
        } 
    }
    private void startCountDown(){

        countDownTimer = new CountDownTimer(timeLeftInMilis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMilis = 0;

                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }
    private void updateCountDownText(){

        int minutes = (int) (timeLeftInMilis/1000/60);
        int seconds = (int)(timeLeftInMilis/1000%60);

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        txtViewCountDown.setText(timeFormatted);

        if(timeLeftInMilis<10000){
            txtViewCountDown.setTextColor(Color.RED);
        }else{
            txtViewCountDown.setTextColor(txtColourDefaultCd);
        }
    }

    private void checkAnswer(){

        answered = true;
        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected)+1;

        if(answerNr == currentQuestion.getAnswerNum()){
            int timeLeftInMilisINT = (int) timeLeftInMilis/1000;
            score = score + timeLeftInMilisINT;
            txtViewScore.setText("Score " + score);
        }

        showSolution();
    }

    private void showSolution() {

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        rb1.setVisibility(View.INVISIBLE);
        rb2.setVisibility(View.INVISIBLE);
        rb3.setVisibility(View.INVISIBLE);
        rb4.setVisibility(View.INVISIBLE);

        switch (currentQuestion.getAnswerNum()){
            case 1:
                rb1.setVisibility(View.VISIBLE);
                rb1.setTextColor(Color.GREEN);
                txtViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setVisibility(View.VISIBLE);
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setVisibility(View.VISIBLE);
                rb3.setTextColor(Color.GREEN);
                txtViewQuestion.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setVisibility(View.VISIBLE);
                rb4.setTextColor(Color.GREEN);
                txtViewQuestion.setText("Answer 4 is correct");
                break;
        }

        if(rb1.isChecked()) {
            rb1.setVisibility(View.VISIBLE);
        }
        else if(rb2.isChecked()){
            rb2.setVisibility(View.VISIBLE);
        }
        else if(rb3.isChecked()){
            rb3.setVisibility(View.VISIBLE);
        }
        else if(rb4.isChecked()){
            rb4.setVisibility(View.VISIBLE);
        }

        if (questionCounter < 5){

            btnNext.setText("Next");
        }else{
            btnNext.setText("Finish");
        }


    }

    private void finishQuiz(int score) {

        Intent intent = new Intent(quiz.this, results.class);
        Bundle bundle = new Bundle ();

        bundle.putInt("Score", score);

        intent.putExtras(bundle);

        startActivity(intent);
    }

}
