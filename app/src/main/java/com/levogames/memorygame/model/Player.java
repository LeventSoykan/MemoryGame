package com.levogames.memorygame.model;

/**
 * Created by Karate Kid on 15.07.2017.
 */
public class Player {
    private String playerName;
    private int playerScore;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
