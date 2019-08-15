package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.dice.Die;
import com.thegingerbeardd.ttbot.dice.impl.D20;

public class FifthEditionCharacter extends com.thegingerbeardd.ttbot.character.PlayerCharacter {

    private FifthEditionCharacterAbilityScores abilityScores;
    private FifthEditionCharacterInterface itrface;

    protected FifthEditionCharacter() {}

    private FifthEditionCharacter(FifthEditionCharacterBuilder builder) {
        itrface = new FifthEditionCharacterInterface(this);
        itrface.setGameDie(builder.gameBaseDie);
        abilityScores = builder.scores;
        name = builder.name;
    }

    protected FifthEditionCharacterAbilityScores getAbilityScores() {
        return abilityScores;
    }

    public FifthEditionCharacterInterface getInterface() {
        return itrface;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FifthEditionCharacter))
            return false;
        return this.name.equals(((FifthEditionCharacter) o).name) &&
                this.abilityScores.equals(((FifthEditionCharacter) o).abilityScores);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static class FifthEditionCharacterBuilder {

        private FifthEditionCharacterAbilityScores scores = new FifthEditionCharacterAbilityScores();
        private String name = "CharacterName";
        private Die gameBaseDie = new D20();

        public FifthEditionCharacterBuilder setAbilityScores(FifthEditionCharacterAbilityScores scores) {
            this.scores = scores;
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
