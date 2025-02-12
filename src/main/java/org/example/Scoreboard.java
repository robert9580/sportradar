package org.example;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Scoreboard {

    private final Set<Match> ongoingMatches = new LinkedHashSet<>();
    private final ScoreboardValidator validator = new ScoreboardValidator();

    /**
     * Start a new match
     */
    public void startMatch(String homeTeam, String awayTeam) {
        validator.validateStartMatch(ongoingMatches, homeTeam, awayTeam);
        ongoingMatches.add(new Match(homeTeam, awayTeam));
    }

    /**
     * Update score for ongoing match
     */
    public void updateScore(String homeTeam, int homeScore, String awayTeam, int awayScore) {
        validator.validateUpdateScore(homeTeam, homeScore, awayTeam, awayScore);
        Match matchToUpdate = findMatchFromOngoingMatches(homeTeam, awayTeam);
        matchToUpdate.updateScore(homeScore, awayScore);
    }

    /**
     * Finish ongoing match
     */
    public void finishMatch(String homeTeam, String awayTeam) {
        validator.validateFinishMatch(homeTeam, awayTeam);
        Match matchToFinish = findMatchFromOngoingMatches(homeTeam, awayTeam);
        ongoingMatches.remove(matchToFinish);
    }

    /**
     * Get summary of ongoing matches
     */
    public List<String> getSummary() {
        return ongoingMatches.stream()
                .map(this::printMatch)
                .toList();
    }

    private String printMatch(Match match) {
        return match.getHomeTeam() + " " + match.getHomeScore() + " - " + match.getAwayTeam() + " " + match.getAwayScore();
    }

    private Match findMatchFromOngoingMatches(String homeTeam, String awayTeam) {
        return ongoingMatches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Match not found for the given teams"));
    }
}
