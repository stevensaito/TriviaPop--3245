package net.androidbootcamp.quizpop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.androidbootcamp.quizpop.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TriviaPop.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NM + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);

    }
    private void fillQuestionsTable(){


        //add questions here
        Question q1 = new Question ("What six-lane, 1.7-mile-long US suspension bridge marked its 75th anniversary in 2012",
                "The Golden Gate Bridge", "Brooklyn Bridge" , "George Washington Bridge" , "Seven Mile Bridge" , 1);
        addQuestion(q1);
        Question q2 = new Question("What is the colourful name of China’s second-longest river, whose waterfall froze spectacularly during an unusual cold nap in 2014?",
                "Yangtze River", "Yellow River", "Mekong River", "Huai River" , 2);
        addQuestion(q2);
        Question q3 = new Question ("Does the Taj Mahal appears pink at dawn, dazzling white during the day, golden at dusk, and silver by moonlight? True or False? " ,
                "True" , "False", null, null, 1);
        addQuestion(q3);
        Question q4 = new Question ("Which US territory was so infested with brown tree snakes that toxic mice were parachuted in to poison them in 2013? " ,
                "Florida", "New York" ,"Guam", "Puerto Rico", 3);
        addQuestion(q4);
        Question q5 = new Question("Which nation was the most visited in 2011 with 79.5 million tourists?" ,"Brazil","USA","Japan", "France",4);
        addQuestion(q5);
    }
    private void addQuestion(Question question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NM, question.getAnswerNum());

        db.insert(QuestionsTable.TABLE_NAME, null, cv);


    }

    public List<Question> getAllQuestions (){

        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if(c.moveToFirst()){

            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNum(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NM)));
                questionList.add(question);

            }while(c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
