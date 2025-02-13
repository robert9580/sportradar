package org.example;

import java.time.Clock;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Scoreboard {

    private final Set<Match> ongoingMatches = new TreeSet<>();
    private final ScoreboardValidator validator = new ScoreboardValidator();
    private final Clock clock;

    public Scoreboard(Clock clock) {
        this.clock = clock;
    }

    /**
     * Start a new match
     */
    public void startMatch(String homeTeam, String awayTeam) {
        Match newMatch = new Match(homeTeam, awayTeam, clock);
        validator.validateStartMatch(homeTeam, awayTeam, ongoingMatches, newMatch);
        ongoingMatches.add(newMatch);
    }

    /**
     * Update score for ongoing match
     */
    public void updateScore(String homeTeam, int homeScore, String awayTeam, int awayScore) {
        validator.validateUpdateScore(homeTeam, homeScore, awayTeam, awayScore);
        Match matchToUpdate = findMatchFromOngoingMatches(homeTeam, awayTeam);
        ongoingMatches.remove(matchToUpdate);
        matchToUpdate.updateScore(homeScore, awayScore);
        ongoingMatches.add(matchToUpdate);
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
     * Get summary of ongoing matches ordered by firstly total score(desc) and secondary start time(desc)
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
