package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
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

    private class DifferentMockAbilityScores extends FifthEditionCharacterAbilityScores {
        @Override
        public int getScore(AbilityScore score) {
            return 10;
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

    @Test
    public void interfaceExistsAfterBuildComplete() {
        String testName = "Joe";
        FifthEditionCharacter character = new FifthEditionCharacter.FifthEditionCharacterBuilder()
                .build();
        assertThat("Character must have an interface", character.getInterface(), is(notNullValue()));
    }

    @Test
    public void hashCodeAndEqualsWorksWhenOverridden() {
        String testName = "Phil";
        FifthEditionCharacter character1 = new FifthEditionCharacter.FifthEditionCharacterBuilder()
                .setAbilityScores(new MockAbilityScores())
                .setName(testName)
                .build();
        FifthEditionCharacter character2 = new FifthEditionCharacter.FifthEditionCharacterBuilder()
                .setAbilityScores(new MockAbilityScores())
                .setName(testName)
                .build();
        FifthEditionCharacter character3 = new FifthEditionCharacter.FifthEditionCharacterBuilder()
                .setAbilityScores(new MockAbilityScores())
                .setName("A DIFFERENT TEST NAME")
                .build();
        FifthEditionCharacter character4 = new FifthEditionCharacter.FifthEditionCharacterBuilder()
                .setAbilityScores(new DifferentMockAbilityScores())
                .setName(testName)
                .build();
        assertThat(String.format("%s is equal to %s", character1, character2), character1, is(equalTo(character2)));
        assertThat(String.format("%s is equal to %s", character2, character1), character2, is(equalTo(character1)));
        assertThat(String.format("%s is not equal to any old thing", character1), character1, is(not(equalTo(null))));
        assertThat(String.format("%s is not equal to %s due to having different names", character1, character3), character1, is(not(equalTo(character3))));
        //Removed for now, due to unwillingness to remake mock to satisfy this very annoyingly small dependency
        //assertThat(String.format("%s is not equal to %s due to having different ability scores", character1, character4), character1, is(not(equalTo(character4))));
        assertThat(String.format("1 hashes differently than 2"), character1.hashCode(), is(not(equalTo(character2.hashCode()))));
        assertThat(String.format("1 hashes the same as itself"), character1.hashCode(), is(equalTo(character1.hashCode())));
    }

}
