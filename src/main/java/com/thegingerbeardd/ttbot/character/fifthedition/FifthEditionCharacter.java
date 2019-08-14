package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.dice.Die;
import com.thegingerbeardd.ttbot.dice.impl.D20;
import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;

public class FifthEditionCharacter {

    private FifthEditionCharacterAbilityScores abilityScores;
    private FifthEditionCharacterInterface itrface;
    private String name;
    protected FifthEditionCharacterAbilityScores getAbilityScores() {
        return abilityScores;
    }

    private FifthEditionCharacter(FifthEditionCharacterBuilder builder) {
        itrface = new FifthEditionCharacterInterface(this);
        itrface.setGameDie(builder.gameBaseDie);
        abilityScores = builder.scores;
        name = builder.name;
    }

    protected FifthEditionCharacter() {};

    public FifthEditionCharacterInterface getInterface() {
        return itrface;
    }

    public String getName() {
        return name;
    }

    public static class FifthEditionCharacterBuilder {

        private static FifthEditionCharacterAbilityScores scores = new FifthEditionCharacterAbilityScores();
        private static String name = "CharacterName";
        private static Die gameBaseDie = new D20();

        public FifthEditionCharacterBuilder setAbilityScores(FifthEditionCharacterAbilityScores scores) {
            this.scores = scores;
            return this;
        }

        public FifthEditionCharacterBuilder setGameDie(Die die) {
            this.gameBaseDie = die;
            return this;
        }

        public FifthEditionCharacter build() {
            return new FifthEditionCharacter(this);
        }

        public FifthEditionCharacterBuilder setName(String name) {
            this.name = name;
            return this;
        }
    }
}
