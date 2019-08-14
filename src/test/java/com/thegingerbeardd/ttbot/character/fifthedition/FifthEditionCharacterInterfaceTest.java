package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.dice.impl.D20;
import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FifthEditionCharacterInterfaceTest {

    private class MockedAbilityScores extends FifthEditionCharacterAbilityScores {
        @Override
        public int getAbilityModifier(AbilityScore score) {
            switch (score) {
                case STR: return -2;
                case DEX: return -1;
                case CON: return 0;
                case INT: return 1;
                case WIS: return 2;
                case CHA: return 3;
            }
            return -5;
        }
    }

    private class MockedFifthEditionCharacter extends FifthEditionCharacter {
        @Override
        protected FifthEditionCharacterAbilityScores getAbilityScores() {
            return new MockedAbilityScores();
        }
    }

    private class MockedDie extends D20 {
        @Override
        public int roll() {
            return 16;
        }
    }

    @Test
    public void interfaceCanMakeAbilityChecksCorrectly() {
        FifthEditionCharacterInterface itrface = new FifthEditionCharacterInterface(new MockedFifthEditionCharacter(), new MockedDie());
        assertThat( AbilityScore.STR + " ability check is " + (14), itrface.rollAbilityCheck(AbilityScore.STR).getTotal(), is(14));
        assertThat( AbilityScore.DEX + " ability check is " + (15), itrface.rollAbilityCheck(AbilityScore.DEX).getTotal(), is(15));
        assertThat( AbilityScore.CON + " ability check is " + (16), itrface.rollAbilityCheck(AbilityScore.CON).getTotal(), is(16));
        assertThat( AbilityScore.INT + " ability check is " + (17), itrface.rollAbilityCheck(AbilityScore.INT).getTotal(), is(17));
        assertThat( AbilityScore.WIS + " ability check is " + (18), itrface.rollAbilityCheck(AbilityScore.WIS).getTotal(), is(18));
        assertThat( AbilityScore.CHA + " ability check is " + (19), itrface.rollAbilityCheck(AbilityScore.CHA).getTotal(), is(19));
    }
}
