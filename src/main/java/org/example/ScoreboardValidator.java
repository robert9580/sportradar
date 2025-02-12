package org.example;

import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isBlank;

class ScoreboardValidator {

    void validateStartMatch(Set<Match> matches, String homeTeam, String awayTeam) {
        validateTeamsNameIsNotBlank(homeTeam, awayTeam);
        validateTeamsNameIsNotTheSame(homeTeam, awayTeam);
        validateTeamsIsNotPlayInOngoingMatches(matches, homeTeam, awayTeam);
    }

    void validateUpdateScore(String homeTeam, int homeScore, String awayTeam, int awayScore) {
        validateTeamsNameIsNotBlank(homeTeam, awayTeam);
        validateTeamsNameIsNotTheSame(homeTeam, awayTeam);
        validateScoresIsNotNegative(homeScore, awayScore);
    }

    private void validateTeamsNameIsNotBlank(String homeTeam, String awayTeam) {
        if (isBlank(homeTeam) || isBlank(awayTeam)) {
            throw new IllegalArgumentException("Name of teams can't be blank");
        }
    }

    private void validateTeamsNameIsNotTheSame(String homeTeam, String awayTeam) {
        if (Objects.equals(homeTeam, awayTeam)) {
            throw new IllegalArgumentException("Name of the teams can't be the same");
        }
    }

    private void validateTeamsIsNotPlayInOngoingMatches(Set<Match> matches, String homeTeam, String awayTeam) {
        if (matches.stream().anyMatch(match -> match.getHomeTeam().equals(homeTeam) || match.getHomeTeam().equals(awayTeam) || match.getAwayTeam().equals(homeTeam) || match.getAwayTeam().equals(awayTeam))) {
            throw new IllegalArgumentException("One of the teams plays in ongoing matches");
        }
    }

    private void validateScoresIsNotNegative(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Match can't have a negative score");
        }
    }
}
