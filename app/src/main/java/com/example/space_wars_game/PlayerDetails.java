package com.example.space_wars_game;


import io.realm.RealmObject;

public class PlayerDetails extends RealmObject {

    private String playerName;
    private int score;

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
