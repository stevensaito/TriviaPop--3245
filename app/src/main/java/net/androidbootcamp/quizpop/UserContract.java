package net.androidbootcamp.quizpop;

import android.provider.BaseColumns;

public final class UserContract {

    private UserContract(){}

    public static class UserTable implements BaseColumns{


        public static final String TABLE_NAME = "user_table";
        public static final String COLUMN_NAME = "user_name";
        public static final String COLUMN_SCORE = "user_score";


    }

}
