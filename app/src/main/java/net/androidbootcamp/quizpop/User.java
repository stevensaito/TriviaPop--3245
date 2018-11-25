package net.androidbootcamp.quizpop;

public final class User {

    private String userName;
    private int userScore;


    public User() {}

    public User(String name, int score) {
        userName = name;
        userScore = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName; }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}
