package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.character.fifthedition.FifthEditionCharacter;
import com.thegingerbeardd.ttbot.dice.Die;
import com.thegingerbeardd.ttbot.dice.Roll;
import com.thegingerbeardd.ttbot.dice.impl.BasicRoll;
import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;

public class FifthEditionCharacterInterface {

    private FifthEditionCharacter ownerCharacter;
    private Die die;

    protected FifthEditionCharacterInterface(FifthEditionCharacter character, Die gameBaseDie) {
        ownerCharacter = character;
        die = gameBaseDie;
    }

    public Roll rollAbilityCheck(AbilityScore score) {
        Roll roll = new BasicRoll(die.getMaxRoll());
        roll.addDiceValue(die.roll());
        roll.addModifier(ownerCharacter.getAbilityScores().getAbilityModifier(score));
        return roll;
    }

}
