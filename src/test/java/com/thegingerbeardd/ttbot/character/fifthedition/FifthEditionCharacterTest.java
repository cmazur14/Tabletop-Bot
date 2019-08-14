package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FifthEditionCharacterTest {

    private class MockAbilityScores extends FifthEditionCharacterAbilityScores {
        @Override
        public int getScore(AbilityScore score) {
            switch (score) {
                case STR: return 11;
                case DEX: return 12;
                case CON: return 13;
                case INT: return 14;
                case WIS: return 15;
                case CHA: return 16;
            }
            return 500;
        }

        @Override
        public int getAbilityModifier(AbilityScore score) {
            return 0;
        }
    }

    @Test
    public void builderSetAbilityScoresFunctions() {
        FifthEditionCharacter character = new FifthEditionCharacter.FifthEditionCharacterBuilder()
                .setAbilityScores(new MockAbilityScores())
                .build();
        assertThat("All scores initialized to value set in Mock", character.getAbilityScores().getScore(AbilityScore.STR), is(11));
        assertThat("All scores initialized to value set in Mock", character.getAbilityScores().getScore(AbilityScore.DEX), is(12));
        assertThat("All scores initialized to value set in Mock", character.getAbilityScores().getScore(AbilityScore.CON), is(13));
        assertThat("All scores initialized to value set in Mock", character.getAbilityScores().getScore(AbilityScore.INT), is(14));
        assertThat("All scores initialized to value set in Mock", character.getAbilityScores().getScore(AbilityScore.WIS), is(15));
        assertThat("All scores initialized to value set in Mock", character.getAbilityScores().getScore(AbilityScore.CHA), is(16));
    }

    @Test
    public void builderSetNameFunctions() {
        String testName = "Joe";
        FifthEditionCharacter character = new FifthEditionCharacter.FifthEditionCharacterBuilder()
                .setName(testName)
                .build();
        assertThat("Character name is " + testName, character.getName(), is(testName));
    }

}
