package com.thegingerbeardd.ttbot.dice.impl;

import com.thegingerbeardd.ttbot.dice.Roll;

import java.util.ArrayList;
import java.util.List;

public class BasicRoll implements Roll {
    private List<Integer> diceRolls;
    private List<Integer> modifiers;
    private int maxVal;

    public BasicRoll(int dieMaxValue) {
        maxVal = dieMaxValue;
        diceRolls = new ArrayList<>();
        modifiers = new ArrayList<>();
    }

    @Override
    public void addDiceValue(int val) {
        diceRolls.add(val);
    }

    @Override
    public void addModifier(int val) {
        modifiers.add(val);
    }

    @Override
    public int[] getModifiers() {
        int[] toReturn = new int[modifiers.size()];
        int index = 0;
        for (int val : modifiers)
            toReturn[index] = val;
        return toReturn;
    }

    @Override
    public int[] getDiceValues() {
        int[] toReturn = new int[diceRolls.size()];
        int index = 0;
        for (int val : diceRolls)
            toReturn[index] = val;
        return toReturn;
    }

    @Override
    public int getTotal() {
        int total = 0;
        for (int i : diceRolls)
            total += i;
        for (int i : modifiers)
            total += i;
        return total;
    }
}
