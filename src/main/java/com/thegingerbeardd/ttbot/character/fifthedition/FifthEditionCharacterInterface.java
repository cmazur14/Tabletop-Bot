package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.dice.Die;
import com.thegingerbeardd.ttbot.dice.Roll;
import com.thegingerbeardd.ttbot.dice.impl.SingleDiceTypeRoll;
import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;

public class FifthEditionCharacterInterface {

    private FifthEditionCharacter ownerCharacter;
    private Die die;

    protected FifthEditionCharacterInterface(FifthEditionCharacter character, Die gameBaseDie) {
        ownerCharacter = character;
        die = gameBaseDie;
    }

    protected FifthEditionCharacterInterface(FifthEditionCharacter character) {
        ownerCharacter = character;
    }

    protected void setGameDie(Die gameDie) {
        die = gameDie;
    }

    public Roll rollAbilityCheck(AbilityScore score) {
        Roll roll = new SingleDiceTypeRoll(die.getMaxRoll());
        roll.addDiceValue(die.roll());
        roll.addModifier(ownerCharacter.getAbilityScores().getAbilityModifier(score));
        return roll;
    }
}
