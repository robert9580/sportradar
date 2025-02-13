package org.example;


import java.time.Clock;

public class Main {
    public static void main(String[] args) {

        Scoreboard scoreBoard = new Scoreboard(Clock.systemDefaultZone());

        scoreBoard.startMatch("Mexico", "Canada");
        scoreBoard.startMatch("Spain", "Brazil");
        System.out.println("Summary of matches in progress:");
        System.out.println(scoreBoard.getSummary());

        scoreBoard.updateScore("Mexico", 0, "Canada", 1);
        System.out.println("Summary of matches in progress:");
        System.out.println(scoreBoard.getSummary());

        scoreBoard.finishMatch("Mexico", "Canada");
        System.out.println("Summary of matches in progress:");
        System.out.println(scoreBoard.getSummary());
    }
}