package com.thegingerbeardd.ttbot.character;

public class PlayerCharacter {
    protected String name;

    public String getName() {
        return name;
    }

    public PlayerCharacter() {}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlayerCharacter))
            return false;
        return this.name.equals(((PlayerCharacter) o).getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
