package com.thegingerbeardd.ttbot.party;

import com.thegingerbeardd.ttbot.character.PlayerCharacter;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private List<PlayerCharacter> characters;
    private Party(PartyBuilder builder) {
        this.characters = builder.characters;
    }

    public PlayerCharacter getMemberWithName(String name) {
        for (PlayerCharacter c : characters) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

    public static class PartyBuilder {
        private List<PlayerCharacter> characters = new ArrayList<>();
        public PartyBuilder setMembers(List<PlayerCharacter> partyMembers) {
            characters = partyMembers;
            return this;
        }

        public Party build() {
            return new Party(this);
        }
    }
}
