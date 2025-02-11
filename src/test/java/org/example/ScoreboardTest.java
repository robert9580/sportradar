package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ScoreboardTest {

    private static final String MEXICO = "Mexico";
    private static final String CANADA = "Canada";
    private static final String SPAIN = "Spain";
    private static final String BRAZIL = "Brazil";


    @Nested
    class StartMatch {

        @Test
        void givenEmptyScoreboard_whenStartNewMatch_thenSummaryExactlyThisNewMatchWithZeroScore() {
            //given
            Scoreboard scoreboard = new Scoreboard();

            //when
            scoreboard.startMatch(MEXICO, CANADA);

            //then
            List<String> summary = scoreboard.getSummary();
            assertThat(summary).containsExactly(MEXICO + " 0 - " + CANADA + " 0");
        }

        @Test
        void givenOneMatchScoreboard_whenStartNewNextMatch_thenSummaryExactlyThisTwoMatchesWithZeroScore() {
            //given
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.startMatch(MEXICO, CANADA);

            //when
            scoreboard.startMatch(SPAIN, BRAZIL);

            //then
            List<String> summary = scoreboard.getSummary();
            assertThat(summary).containsExactly(MEXICO + " 0 - " + CANADA + " 0",
                    SPAIN + " 0 - " + BRAZIL + " 0");
        }

        @Test
        void givenEmptyScoreboard_whenStartNewMatchWithBoothTeamsTheSameName_thenExceptionThrow() {
            //given
            Scoreboard scoreboard = new Scoreboard();

            //when
            Throwable thrown = catchThrowable(() -> scoreboard.startMatch(MEXICO, MEXICO));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("");
        }

        @Test
        void givenOneMatchScoreboard_whenStartNewMatchWithTeamNameExists_thenExceptionThrow() {
            //given
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.startMatch(MEXICO, CANADA);

            //when
            Throwable thrown = catchThrowable(() -> scoreboard.startMatch(MEXICO, BRAZIL));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("");
        }
    }

}
