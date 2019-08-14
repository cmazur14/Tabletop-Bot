package com.thegingerbeardd.ttbot.character.fifthedition.internals;

import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;
import com.thegingerbeardd.ttbot.rulesets.fifthedition.FifthEditionConstants;

import java.util.EnumMap;
import java.util.Map;

import static com.thegingerbeardd.ttbot.rulesets.fifthedition.FifthEditionConstants.*;

public class FifthEditionCharacterAbilityScores {

    private Map<AbilityScore, Integer> scores;

    protected FifthEditionCharacterAbilityScores() {
        scores = new EnumMap<>(AbilityScore.class);
        for (AbilityScore score : AbilityScore.values()) {
            scores.put(score, DEFAULT_ABILITY_SCORE);
        }
    }

    public int getScore(AbilityScore score) {
        return scores.get(score);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (AbilityScore score : scores.keySet()) {
            builder.append(score);
            builder.append(": ");
            builder.append(scores.get(score));
            if (score.ordinal() != scores.keySet().size() - 1)
                builder.append("\n");
        }
        return builder.toString();
    }

    public void setScore(AbilityScore score, int i) {
        scores.put(score, i);
    }

    public Object getAbilityModifier(AbilityScore score) {
        if (scores.get(score) < 10)
            return ((scores.get(score) - 11) / 2);
        return ((scores.get(score) - 10) / 2);
    }
}
