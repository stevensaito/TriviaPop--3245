package net.androidbootcamp.quizpop;

public final class User {

    private String userName;
    private String userScore;


    public User() {}

    public User(String name, String score) {
        userName = name;
        userScore = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public String toString(){

        return "Name: " + getUserName() + " Score: " + getUserScore();
    }
}
