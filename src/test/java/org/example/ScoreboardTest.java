package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ScoreboardTest {


    @Nested
    class StartMatch {

        @Test
        void givenEmptyScoreboard_whenStartNewMatch_thenSummaryExactlyThisNewMatchWithZeroScore() {
            //given

            //when

            //then
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void givenOneMatchScoreboard_whenStartNewNextMatch_thenSummaryExactlyThisTwoMatchesWithZeroScore() {
            //given

            //when

            //then
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void givenEmptyScoreboard_whenStartNewMatchWithBoothTeamsTheSameName_thenExceptionThrow() {
            //given

            //when

            //then
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void givenOneMatchScoreboard_whenStartNewMatchWithTeamNameExists_thenExceptionThrow() {
            //given

            //when

            //then
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

}
