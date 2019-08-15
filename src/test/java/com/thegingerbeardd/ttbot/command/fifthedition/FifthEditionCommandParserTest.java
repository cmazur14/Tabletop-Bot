package com.thegingerbeardd.ttbot.command.fifthedition;

import com.thegingerbeardd.ttbot.character.PlayerCharacter;
import com.thegingerbeardd.ttbot.character.fifthedition.FifthEditionCharacter;
import com.thegingerbeardd.ttbot.character.fifthedition.FifthEditionCharacterInterface;
import com.thegingerbeardd.ttbot.dice.Roll;
import com.thegingerbeardd.ttbot.dice.impl.SingleDiceTypeRoll;
import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;
import com.thegingerbeardd.ttbot.subscriber.fifthedition.FifthEditionAbilityCheckCommandSubscriber;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FifthEditionCommandParserTest {

    @Test
    public void rollABILITYCheckFor_CommandIdentifiedAsAbilityCheckCommandCorrectly() {
        FifthEditionCommandParser sub = new FifthEditionCommandParser();
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
        FifthEditionCommandParser sub = new FifthEditionCommandParser();
        assertEquals("THISISANAME", sub.getNameOfSubjectOfCommand("roll a dex check for THISISANAME"));
        assertEquals("THISISANAME", sub.getNameOfSubjectOfCommand("roll a dex check for thisisaname"));

        assertEquals("BOATWANA", sub.getNameOfSubjectOfCommand("roll a dex check for boatwana"));
        assertEquals("WELLBY", sub.getNameOfSubjectOfCommand("For Wellby Tosscobble, roll ....."));

        //Note, the following tests do not indicate valid character names, but another method must be responsible for validating a "name" against members of the stored party
        assertEquals("ROLL", sub.getNameOfSubjectOfCommand("bob for roll do things"));
        assertEquals("THISISNOTANAME", sub.getNameOfSubjectOfCommand("roll a dex check for thisisnotaname"));
        assertEquals("NOBODY", sub.getNameOfSubjectOfCommand("roll a dex check for NOBODY other words go here"));

        //The following are expected to have nulls
        assertEquals(null, sub.getNameOfSubjectOfCommand(""));
        assertEquals(null, sub.getNameOfSubjectOfCommand(" roll a dex check for"));
        assertEquals(null, sub.getNameOfSubjectOfCommand("this command doesn't contain a four"));
    }

    @Test
    public void getCharacterWithNameWorksWhenPassedAnAppropriateParty() {
        //TODO Actually write this test
    }

}
