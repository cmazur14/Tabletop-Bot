package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.dice.Die;
import com.thegingerbeardd.ttbot.dice.impl.D20;
import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MockedFifthEditionCharacter))
                return false;
            return ((MockedFifthEditionCharacter) o).getAbilityScores().equals(getAbilityScores());
        }
    }

    private class MockedDie extends D20 {
        @Override
        public int roll() {
            return 16;
        }
    }

    private class DifferentMockedDie implements Die {

        @Override
        public int roll() {
            return 2;
        }

        @Override
        public int getMaxRoll() {
            return 3;
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

    @Test
    public void overriddenEqualsAndHashCodeWorkCorrectly() {
        FifthEditionCharacterInterface itrface1 = new FifthEditionCharacterInterface(new MockedFifthEditionCharacter(), new MockedDie());
        FifthEditionCharacterInterface itrface2 = new FifthEditionCharacterInterface(new MockedFifthEditionCharacter(), new MockedDie());
        FifthEditionCharacterInterface itrface3 = new FifthEditionCharacterInterface(new MockedFifthEditionCharacter(), new DifferentMockedDie());
        assertThat("The same interface is equal to itself", itrface1, is(equalTo(itrface1)));
        assertThat("Two interfaces created with same arguments are equal", itrface1, is(equalTo(itrface2)));
        assertThat("Two interfaces created with different arguments are not equal", itrface1, is(not(equalTo(itrface3))));
        assertThat("An interface is not equal to any old thing", itrface3, is(not(equalTo(null))));
        assertThat("An interface hashes to itself", itrface1.hashCode(), is(equalTo(itrface1.hashCode())));
        assertThat("Two separate interfaces hash to different values", itrface1.hashCode(), is(not(equalTo(itrface2.hashCode()))));
    }

}
