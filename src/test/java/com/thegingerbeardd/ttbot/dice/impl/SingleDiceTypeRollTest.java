package com.thegingerbeardd.ttbot.dice.impl;

import com.thegingerbeardd.ttbot.dice.Roll;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SingleDiceTypeRollTest {
    
    @Test
    public void singleRollProducesCorrectValue() {
        Roll roll = new SingleDiceTypeRoll(diceMaxValue(20));
        roll.addDiceValue(10);
        assertThat("Dice that always rolls a 10 should be detected as having rolled a 10", roll.getTotal(), is(10));
    }

    @Test
    public void multipleRollsProduceCorrectValue() {
        Roll roll = new SingleDiceTypeRoll(diceMaxValue(20));
        roll.addDiceValue(10);
        roll.addDiceValue(10);
        roll.addDiceValue(10);
        assertThat("Dice that always rolls a 10 should add up to 30 when rolled 3 times", roll.getTotal(), is(30));
    }

    @Test
    public void multipleModifiersWithNoDiceRollStillProducesAValue() {
        Roll roll = new SingleDiceTypeRoll(diceMaxValue(20));
        roll.addModifier(1);
        roll.addModifier(2);
        roll.addModifier(3);
        assertThat("Since there's no dice rolled but modifiers added, the SingleDiceTypeRoll can still do basic math", roll.getTotal(), is(6));
    }

    @Test
    public void multipleRollsWithMultipleModifiersWorksCorrectly() {
        Roll roll = new SingleDiceTypeRoll(diceMaxValue(20));
        roll.addModifier(1);
        roll.addModifier(2);
        roll.addModifier(3);
        roll.addDiceValue(10);
        roll.addDiceValue(10);
        roll.addDiceValue(10);
        assertThat("3 10's rolled + 1, 2, and 3 should be 36", roll.getTotal(), is(36));
    }

    @Test
    public void rollToStringWorksAsIntendedWhenThereAreNoSpecialValues() {
        Roll roll = new SingleDiceTypeRoll(diceMaxValue(20));
        roll.addDiceValue(10);
        roll.addDiceValue(10);
        roll.addModifier(2);
        roll.addModifier(5);
        assertThat("Output should be of the form: [dice] + [dice] + modifier + modifier", roll.toString(), is("[10] + [10] + 2 + 5"));
        roll = new SingleDiceTypeRoll(diceMaxValue(20));
        roll.addDiceValue(6);
        roll.addDiceValue(7);
        assertThat("Output should be of the form: [dice] + [dice]", roll.toString(), is("[6] + [7]"));
    }

    private int diceMaxValue(int val) {
        return val;
    }

    /*@Test
    public void rollToStringHandlesMaxValues() {
        Roll roll = new SingleDiceTypeRoll(20);
        roll.addDiceValue(20); //in line above, max dice value was set to be 20
        assertThat("Output should have extras for discord green color", roll.toString(), is("[```CSS\n20\n```]"));
    }

    @Test
    public void rollToStringHandlesMinValues() {
        Roll roll = new SingleDiceTypeRoll(20);
        roll.addDiceValue(1);
        assertThat("Output should have extras for discord red color", roll.toString(), is("[```diff\n1\n```]"));
    }

    @Test
    public void rollToStringCanHandleMaxMinRegularAndModifiersAllInOneRoll() {
        Roll roll = new SingleDiceTypeRoll(20);
        roll.addDiceValue(1);
        roll.addDiceValue(20);
        roll.addDiceValue(3);
        roll.addModifier(1);
        assertThat("Output should be several lines, including extras for discord color", roll.toString(), is(
                "[```diff\n1\n```] + [```CSS\ndiceMaxValue(20\n```] + [3] + 1"
        ));
    }*/

}
