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
}
