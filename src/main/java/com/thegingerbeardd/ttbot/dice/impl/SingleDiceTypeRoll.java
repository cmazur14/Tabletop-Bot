package com.thegingerbeardd.ttbot.dice.impl;

import com.thegingerbeardd.ttbot.dice.Roll;

import java.util.ArrayList;
import java.util.List;

import static com.thegingerbeardd.ttbot.dice.DiceConstants.*;

public class SingleDiceTypeRoll implements Roll {
    private List<Integer> diceRolls;
    private List<Integer> modifiers;
    private int maxVal;

    public SingleDiceTypeRoll(int dieMaxValue) {
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int index = 0;
        for (int i : diceRolls) {
            output.append(DICE_LEFT_BRACKET);
            if (i == maxVal) {
                output.append("'''");
                output.append("CSS\n");
                output.append(i);
                output.append("\n'''");
            } else if (i == 1) {
                output.append("'''");
                output.append("diff\n");
                output.append(i);
                output.append("\n'''");
            } else {
                output.append(i);
            }
            output.append(DICE_RIGHT_BRACKET);
            if (index < (diceRolls.size() - 1))
                output.append(" + ");
            index++;
        }
        index = 0;
        if (modifiers.size() > 0)
            output.append(" + ");
        for (int i : modifiers) {
            output.append(i);
            if (index < (modifiers.size() - 1))
                output.append(" + ");
        }
        return output.toString();
    }
}
