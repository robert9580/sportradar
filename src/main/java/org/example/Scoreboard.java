package org.example;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Scoreboard {

    private final Set<Match> matches = new LinkedHashSet<>();
    private final ScoreboardValidator validator = new ScoreboardValidator();

    /**
     * Start a new match
     */
    public void startMatch(String homeTeam, String awayTeam) {
        validator.validateStartMatch(matches, homeTeam, awayTeam);
        matches.add(new Match(homeTeam, awayTeam));
    }

    /**
     * Update score for a match
     */
    public void updateScore(String homeTeam, int homeScore, String awayTeam, int awayScore) {

    }

    /**
     * Get summary of ongoing matches
     */
    public List<String> getSummary() {
        return matches.stream()
                .map(this::printMatch)
                .toList();
    }

    private String printMatch(Match match) {
        return match.getHomeTeam() + " " + match.getHomeScore() + " - " + match.getAwayTeam() + " " + match.getAwayScore();
    }
}
