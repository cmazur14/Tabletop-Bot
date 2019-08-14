package com.thegingerbeardd.ttbot.dice.impl;

import com.thegingerbeardd.ttbot.dice.Die;

import java.util.Random;

public class D20 implements Die {

    private Random rng;
    private static final int MAX_VALUE = 20;

    public D20() {
        rng = new Random();
    }

    @Override
    public int roll() {
        return rng.nextInt(MAX_VALUE + 1);
    }

    @Override
    public int getMaxRoll() {
        return MAX_VALUE;
    }

}
