package org.example;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

class Match implements Comparable<Match> {
    private final String homeTeam;
    private int homeScore;
    private final String awayTeam;
    private int awayScore;
    private final LocalDateTime startTime;

    public Match(String homeTeam, String awayTeam, Clock clock) {
        this.homeTeam = homeTeam;
        this.homeScore = 0;
        this.awayTeam = awayTeam;
        this.awayScore = 0;
        this.startTime = LocalDateTime.now(clock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(homeTeam, match.homeTeam) && Objects.equals(awayTeam, match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
    }

    @Override
    public int compareTo(Match other) {
        int scoreComparison = Integer.compare(other.getTotalScore(), this.getTotalScore());
        if (scoreComparison != 0) {
            return scoreComparison;
        }
        return other.startTime.compareTo(this.startTime);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

}
