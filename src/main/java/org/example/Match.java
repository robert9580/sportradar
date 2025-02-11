package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

public class Match {
    private final String homeTeam;
    private short homeScore;
    private final String awayTeam;
    private short awayScore;
    private final LocalDateTime startTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.homeScore = 0;
        this.awayTeam = awayTeam;
        this.awayScore = 0;
        this.startTime = LocalDateTime.now();
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

    public String getHomeTeam() {
        return homeTeam;
    }

    public short getHomeScore() {
        return homeScore;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public short getAwayScore() {
        return awayScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

}
