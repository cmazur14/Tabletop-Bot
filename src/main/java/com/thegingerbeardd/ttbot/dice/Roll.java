package com.thegingerbeardd.ttbot.dice;

public interface Roll {
    public void addDiceValue(int val);
    public void addModifier(int val);
    public int[] getModifiers();
    public int[] getDiceValues();
    public int getTotal();
}
