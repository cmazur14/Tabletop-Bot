package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import static com.thegingerbeardd.ttbot.rulesets.fifthedition.FifthEditionConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FifthEditionCharacterAbilityScoresTest {

    private static final Logger LOGGER = LogManager.getLogger(FifthEditionCharacterAbilityScoresTest.class);

    @Test
    public void emptyConstructorInitializesToDefaultAndToStringWorks() {
        FifthEditionCharacterAbilityScores charAbilityScores = new FifthEditionCharacterAbilityScores();
        assertThat("All scores should be " + DEFAULT_ABILITY_SCORE, charAbilityScores.toString(), is(AbilityScore.STR + ": " + DEFAULT_ABILITY_SCORE +
                                                                                                                  "\n" + AbilityScore.DEX + ": " + DEFAULT_ABILITY_SCORE +
                                                                                                                  "\n" + AbilityScore.CON + ": " + DEFAULT_ABILITY_SCORE +
                                                                                                                  "\n" + AbilityScore.INT + ": " + DEFAULT_ABILITY_SCORE +
                                                                                                                  "\n" + AbilityScore.WIS + ": " + DEFAULT_ABILITY_SCORE +
                                                                                                                  "\n" + AbilityScore.CHA + ": " + DEFAULT_ABILITY_SCORE));
    }

    @Test
    public void getterForAllAbilityScoresWork() {
        FifthEditionCharacterAbilityScores charAbilityScores = new FifthEditionCharacterAbilityScores();
        assertThat(AbilityScore.STR + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.STR), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.DEX + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.DEX), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.CON + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.CON), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.INT + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.INT), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.WIS + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.WIS), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.CHA + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.CHA), is(DEFAULT_ABILITY_SCORE));
    }

    @Test
    public void setAbilityScoreWorksAsASetterForTheSpecificScore() {
        FifthEditionCharacterAbilityScores charAbilityScores = new FifthEditionCharacterAbilityScores();
        charAbilityScores.setScore(AbilityScore.STR, 15);
        assertThat( AbilityScore.STR + " is 15 after being set that way", charAbilityScores.getScore(AbilityScore.STR), is(15));
        charAbilityScores.setScore(AbilityScore.DEX, 16);
        assertThat( AbilityScore.DEX + " is 16 after being set that way", charAbilityScores.getScore(AbilityScore.DEX), is(16));
        charAbilityScores.setScore(AbilityScore.CON, 17);
        assertThat( AbilityScore.CON + " is 17 after being set that way", charAbilityScores.getScore(AbilityScore.CON), is(17));
        charAbilityScores.setScore(AbilityScore.INT, 18);
        assertThat( AbilityScore.INT + " is 18 after being set that way", charAbilityScores.getScore(AbilityScore.INT), is(18));
        charAbilityScores.setScore(AbilityScore.WIS, 19);
        assertThat( AbilityScore.WIS + " is 19 after being set that way", charAbilityScores.getScore(AbilityScore.WIS), is(19));
        charAbilityScores.setScore(AbilityScore.CHA, 20);
        assertThat( AbilityScore.CHA + " is 20 after being set that way", charAbilityScores.getScore(AbilityScore.CHA), is(20));
    }

    @Test
    public void setAbilityScoreWorksAndOnlyAffectsTheIntendedScore() {
        FifthEditionCharacterAbilityScores charAbilityScores = new FifthEditionCharacterAbilityScores();
        charAbilityScores.setScore(AbilityScore.STR, 15);
        assertThat("Strength is 15 after being set that way", charAbilityScores.getScore(AbilityScore.STR), is(15));
        assertThat(AbilityScore.DEX + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.DEX), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.CON + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.CON), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.INT + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.INT), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.WIS + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.WIS), is(DEFAULT_ABILITY_SCORE));
        assertThat(AbilityScore.CHA + " is " + DEFAULT_ABILITY_SCORE, charAbilityScores.getScore(AbilityScore.CHA), is(DEFAULT_ABILITY_SCORE));
    }

    @Test
    public void getAbilityModifierCalculatesModifierCorrectlyBasedOnGivenScore() {
        FifthEditionCharacterAbilityScores charAbilityScores = new FifthEditionCharacterAbilityScores();
        int expectedMod = 0;
        for (int i = 0; i < 30; i++) {
            if (i == 0)
                expectedMod = -5;           //Initially (when score is 0), modifier should be -5
            else if (i % 2 == 0)
                expectedMod++;              //ability modifier bumps up at all even numbers
            charAbilityScores.setScore(AbilityScore.STR, i);
            LOGGER.debug(AbilityScore.STR + " modifier should be: " + expectedMod + " when " + AbilityScore.STR + " is " + i);
            assertThat(AbilityScore.STR + " modifier should be: " + expectedMod + " when " + AbilityScore.STR + " is " + i, charAbilityScores.getAbilityModifier(AbilityScore.STR), is(expectedMod));
        }
    }

}
