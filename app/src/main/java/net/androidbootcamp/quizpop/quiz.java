package net.androidbootcamp.quizpop;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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

public class quiz extends AppCompatActivity {

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

    private ColorStateList txtColourDefaultRb;

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
                }
            }
        });
    }

    private void shownNextQuestion() {

        rb1.setTextColor(txtColourDefaultRb);
        rb2.setTextColor(txtColourDefaultRb);
        rb3.setTextColor(txtColourDefaultRb);
        rb4.setTextColor(txtColourDefaultRb);

        rbGroup.clearCheck();

        if(questionCounter < questionCountTotal){

            currentQuestion = questionList.get(questionCounter);
            txtViewQuestion.setText(currentQuestion.getQuestion());

            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            txtViewQuestionCount.setText("Question " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnNext.setText("Confirm");

        }else{
            
            finishQuiz(); 
        } 
    }

    private void checkAnswer(){

        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected)+1;

        if(answerNr == currentQuestion.getAnswerNum()){
            score++;
            txtViewScore.setText("Score " + score);

        }

        showSolution();
    }

    private void showSolution() {

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNum()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                txtViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb3.setTextColor(Color.GREEN);
                txtViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                txtViewQuestion.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                txtViewQuestion.setText("Answer 4 is correct");
                break;

        }
        if (questionCounter < questionCountTotal){

            btnNext.setText("Next");
        }else{
            btnNext.setText("Finish");
        }


    }

    private void finishQuiz() {

        finish();
    }
}
