package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        void givenOneMatchScoreboard_whenStartNewNextMatch_thenSummaryExactlyTheseTwoMatchesWithZeroScore() {
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

        @ParameterizedTest
        @CsvSource(delimiter = '|', textBlock = """
                             | Brazil
                 Brazil      |
                 ''          | Brazil
                 Brazil      | ' '
                 '    '      | ''
                """)
        void givenEmptyScoreboard_whenStartNewMatchWithTeamNameIsBlank_thenExceptionThrow(String homeTeam, String awayTeam) {
            //given
            Scoreboard scoreboard = new Scoreboard();

            //when
            Throwable thrown = catchThrowable(() -> scoreboard.startMatch(homeTeam, awayTeam));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Name of teams can't be blank");
        }

        @Test
        void givenEmptyScoreboard_whenStartNewMatchWithBoothTeamsTheSameName_thenExceptionThrow() {
            //given
            Scoreboard scoreboard = new Scoreboard();

            //when
            Throwable thrown = catchThrowable(() -> scoreboard.startMatch(MEXICO, MEXICO));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Name of the teams can't be the same");
        }

        @ParameterizedTest
        @CsvSource(delimiter = '|', textBlock = """
                 Mexico      | Brazil
                 Brazil      | Mexico
                 Canada      | Brazil
                 Brazil      | Canada
                """)
        void givenOneMatchScoreboard_whenStartNewMatchWithTeamNameExists_thenExceptionThrow(String homeTeam, String awayTeam) {
            //given
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.startMatch(MEXICO, CANADA);

            //when
            Throwable thrown = catchThrowable(() -> scoreboard.startMatch(homeTeam, awayTeam));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("One of the teams plays in ongoing matches");
        }
    }

    @Nested
    class UpdateScore {

        @Test
        void givenTwoMatchScoreboard_whenUpdateScore_thenSummaryExactlyTheseMatchesWithUpdateScoreOne() {
            //given
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.startMatch(MEXICO, CANADA);
            scoreboard.startMatch(SPAIN, BRAZIL);

            //when
            scoreboard.updateScore(MEXICO, 1, CANADA, 2);

            //then
            List<String> summary = scoreboard.getSummary();
            assertThat(summary).containsExactly(MEXICO + " 1 - " + CANADA + " 2",
                    SPAIN + " 0 - " + BRAZIL + " 0");
        }

        @ParameterizedTest
        @CsvSource(delimiter = '|', textBlock = """
                             | Brazil
                 Brazil      |
                 ''          | Brazil
                 Brazil      | ' '
                 '    '      | ''
                """)
        void givenEmptyScoreboard_whenUpdateScoreWithTeamNameIsBlank_thenExceptionThrow(String homeTeam, String awayTeam) {
            //given
            Scoreboard scoreboard = new Scoreboard();

            //when
            Throwable thrown = catchThrowable(() -> scoreboard.updateScore(homeTeam, 0, awayTeam, 0));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Name of teams can't be blank");
        }

        @Test
        void givenEmptyScoreboard_whenUpdateScoreWithBoothTeamsTheSameName_thenExceptionThrow() {
            //given
            Scoreboard scoreboard = new Scoreboard();

            //when
            Throwable thrown = catchThrowable(() -> scoreboard.updateScore(MEXICO, 0, MEXICO, 0));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Name of the teams can't be the same");
        }

        @Test
        void givenOneMatchScoreboard_whenUpdateScoreNotExistMatch_thenExceptionThrow() {
            //given
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.startMatch(MEXICO, CANADA);

            //when
            Throwable thrown = catchThrowable(() ->scoreboard.updateScore(SPAIN, 1, BRAZIL, 2));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Match not found for the given teams");
        }

        @Test
        void givenOneMatchScoreboard_whenUpdateScoreMistakeHomeAndAwayTeamViceVersa_thenExceptionThrow() {
            //given
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.startMatch(MEXICO, CANADA);

            //when
            Throwable thrown = catchThrowable(() ->scoreboard.updateScore(CANADA, 1, MEXICO, 2));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Match not found for the given teams");
        }

        @ParameterizedTest
        @CsvSource(delimiter = '|', textBlock = """
                 -1      | 1
                 1       | -1
                 -1      | -1
                """)
        void givenOneMatchScoreboard_whenUpdateNegativeScore_thenExceptionThrow(int homeScore, int awayScore) {
            //given
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.startMatch(MEXICO, CANADA);

            //when
            Throwable thrown = catchThrowable(() ->scoreboard.updateScore(MEXICO, homeScore, CANADA, awayScore));

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Match can't have a negative score");
        }

    }

}
