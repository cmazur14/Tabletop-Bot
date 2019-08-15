package com.thegingerbeardd.ttbot.character.fifthedition;

import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;

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
        for (Map.Entry<AbilityScore, Integer> score : scores.entrySet()) {
            builder.append(score.getKey());
            builder.append(": ");
            builder.append(score.getValue());
            if (score.getKey().ordinal() != scores.keySet().size() - 1)
                builder.append("\n");
        }
        return builder.toString();
    }

    public void setScore(AbilityScore score, int i) {
        scores.put(score, i);
    }

    public int getAbilityModifier(AbilityScore score) {
        if (scores.get(score) < 10)
            return ((scores.get(score) - 11) / 2);
        return ((scores.get(score) - 10) / 2);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FifthEditionCharacterAbilityScores))
            return false;
        for (Map.Entry<AbilityScore, Integer> score : scores.entrySet()) {
            //if any ability score does not match the corresponding ability score in obj, return false
            if (!score.getValue().equals(((FifthEditionCharacterAbilityScores) obj).scores.get(score.getKey())))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
