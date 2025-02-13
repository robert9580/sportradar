package org.example;

import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isBlank;

class ScoreboardValidator {

    void validateStartMatch(String homeTeam, String awayTeam, Set<Match> ongoingMatches, Match newMatch) {
        validateTeamsNameIsNotBlank(homeTeam, awayTeam);
        validateTeamsNameIsNotTheSame(homeTeam, awayTeam);
        validateTeamsIsNotPlayInOngoingMatches(homeTeam, awayTeam, ongoingMatches);
        validateMatchIsNotTheSameStartTimeInOngoingMatches(newMatch, ongoingMatches);
    }

    void validateUpdateScore(String homeTeam, int homeScore, String awayTeam, int awayScore) {
        validateTeamsNameIsNotBlank(homeTeam, awayTeam);
        validateTeamsNameIsNotTheSame(homeTeam, awayTeam);
        validateScoresIsNotNegative(homeScore, awayScore);
    }

    void validateFinishMatch(String homeTeam, String awayTeam) {
        validateTeamsNameIsNotBlank(homeTeam, awayTeam);
        validateTeamsNameIsNotTheSame(homeTeam, awayTeam);
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

    private void validateTeamsIsNotPlayInOngoingMatches(String homeTeam, String awayTeam, Set<Match> ongoingMatches) {
        if (ongoingMatches.stream().anyMatch(match -> match.getHomeTeam().equals(homeTeam) || match.getHomeTeam().equals(awayTeam) || match.getAwayTeam().equals(homeTeam) || match.getAwayTeam().equals(awayTeam))) {
            throw new IllegalArgumentException("One of the teams plays in ongoing matches");
        }
    }

    /**
     * https://stackoverflow.com/questions/31334698/understanding-treeset-when-compareto-returns-0
     */
    private void validateMatchIsNotTheSameStartTimeInOngoingMatches(Match newMatch, Set<Match> ongoingMatches) {
        if (ongoingMatches.stream().anyMatch(match -> match.getStartTime().equals(newMatch.getStartTime()))) {
            throw new IllegalStateException("One of the matches has exactly the same time start");
        }
    }

    private void validateScoresIsNotNegative(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Match can't have a negative score");
        }
    }
}
