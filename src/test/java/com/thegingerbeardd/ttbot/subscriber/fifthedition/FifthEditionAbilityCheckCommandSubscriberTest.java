package com.thegingerbeardd.ttbot.subscriber.fifthedition;

import com.thegingerbeardd.ttbot.character.fifthedition.FifthEditionCharacter;
import com.thegingerbeardd.ttbot.character.fifthedition.FifthEditionCharacterInterface;
import com.thegingerbeardd.ttbot.dice.Roll;
import com.thegingerbeardd.ttbot.dice.impl.SingleDiceTypeRoll;
import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FifthEditionAbilityCheckCommandSubscriberTest {

    private static final int DEFAULT_DICE_ROLL = 4;
    private static final int DEFAULT_ROLL_MOD = 2;

    private class MockFifthEditionCharacter extends FifthEditionCharacter {
        private MockFifthEditionCharacter(String name) {
            this.name = name;
        }

        @Override
        public FifthEditionCharacterInterface getInterface() {
            return new MockFifthEditionCharacterInterface(null);
        }
    }

    private class MockFifthEditionCharacterInterface extends FifthEditionCharacterInterface {
        protected MockFifthEditionCharacterInterface(FifthEditionCharacter character) {
            super(character);
        }

        @Override
        public Roll rollAbilityCheck(AbilityScore score) {
            Roll roll = new SingleDiceTypeRoll(20);
            roll.addDiceValue(DEFAULT_DICE_ROLL);
            roll.addModifier(DEFAULT_ROLL_MOD);
            return roll;
        }
    }

    private FifthEditionCharacter testCharacterJoe;
    private FifthEditionCharacter testCharacterBob;
    private FifthEditionCharacter testCharacterLys;


    @Before
    public void createTestCharacters() {
        testCharacterJoe = new MockFifthEditionCharacter("Joe");
        testCharacterBob = new MockFifthEditionCharacter("Bob");
        testCharacterLys = new MockFifthEditionCharacter("Lys");
    }

    @Test
    public void rollABILITYCheckFor_CommandIdentifiedAsAbilityCheckCommandCorrectly() {
        FifthEditionAbilityCheckCommandSubscriber sub = new FifthEditionAbilityCheckCommandSubscriber();
        //Anything of the format "roll ......... ABILITY check for" will be identified as an AbilityCheckCommand
        assertEquals(true, sub.commandIsAbilityCheckCommand("roll a Dex Check for charactername"));
        assertEquals(true, sub.commandIsAbilityCheckCommand("roll me a dexterity check for charactername"));
        assertEquals(true, sub.commandIsAbilityCheckCommand("roll me a STR check for ...."));
        assertEquals(true, sub.commandIsAbilityCheckCommand("roll a random person a strength check for ...."));
        assertEquals(true, sub.commandIsAbilityCheckCommand("roll a Strength check for ...."));
        assertEquals(true, sub.commandIsAbilityCheckCommand("roll a Constitutino check for ...."));
        assertEquals(true, sub.commandIsAbilityCheckCommand("roll this is a word a con check for ...."));
        assertEquals(true, sub.commandIsAbilityCheckCommand("roll literally any word except for the word that starts with 'CH' and ends in 'ECK' a con check for ...."));
        assertEquals(true, sub.commandIsAbilityCheckCommand("For ..., roll an Intelligence check"));

        //Anything that has similar words but in the incorrect order are NOT abilityCheckCommands
        assertEquals(false, sub.commandIsAbilityCheckCommand("check abilityName roll for...."));
        assertEquals(false, sub.commandIsAbilityCheckCommand("roll check strength for...."));

        //Skill check commands are not abilityCheckCommands
        assertEquals(false, sub.commandIsAbilityCheckCommand("roll a Perception check for ..."));

        //Ability Save commands are not abilityCheckCommands
        assertEquals(false, sub.commandIsAbilityCheckCommand("roll a dexterity save for ..."));
        assertEquals(false, sub.commandIsAbilityCheckCommand("roll a CON save for...."));
    }

    @Test
    public void getSubjectWillAppropriatelyIdentifyNamesIfNamePrependedByFOR() {
        FifthEditionAbilityCheckCommandSubscriber sub = new FifthEditionAbilityCheckCommandSubscriber();
        assertEquals("THISISANAME", sub.getNameOfSubjectOfCommand("roll a dex check for THISISANAME"));
        assertEquals("THISISANAME", sub.getNameOfSubjectOfCommand("roll a dex check for thisisaname"));

        assertEquals("BOATWANA", sub.getNameOfSubjectOfCommand("roll a dex check for boatwana"));
        assertEquals("WELLBY", sub.getNameOfSubjectOfCommand("For Wellby Tosscobble, roll ....."));

        //Note, the following tests do not indicate valid character names, but another method must be responsible for validating a "name" against members of the stored party
        assertEquals("ROLL", sub.getNameOfSubjectOfCommand("bob for roll do things"));
        assertEquals("THISISNOTANAME", sub.getNameOfSubjectOfCommand("roll a dex check for thisisnotaname"));
        assertEquals("NOBODY", sub.getNameOfSubjectOfCommand("roll a dex check for NOBODY other words go here"));

    }

}
