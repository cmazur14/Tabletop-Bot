package com.thegingerbeardd.ttbot.command.fifthedition;

import com.thegingerbeardd.ttbot.rulesets.fifthedition.AbilityScore;

public class FifthEditionCommandParser {
    public boolean commandIsAbilityCheckCommand(String s) {
        String lowercase = addSpaceOnEitherEnd(s.toLowerCase());
        int abilityScoreIndex = -1;
        if (!lowercase.contains(" check "))
            return false;
        else {
            for (AbilityScore score : AbilityScore.values()) {
                if (lowercase.contains(" " + score.name().toLowerCase())) {
                    abilityScoreIndex = lowercase.indexOf(" " + score.name().toLowerCase()) + 1;
                    break;
                }
            }
            if (abilityScoreIndex == -1)
                return false;
        }
        String substringAfterAbilityScore = lowercase.substring(abilityScoreIndex);
        String[] tokens = substringAfterAbilityScore.split(" ", -1);
        return tokens[1].equals("check");
    }

    public String getNameOfSubjectOfCommand(String s) {
        String val = ifPrependedByForThenThisIsTheName(s);
        return val == null ? null : val.toUpperCase();
    }

    private String ifPrependedByForThenThisIsTheName(String s) {
        String lowercase = addSpaceOnEitherEnd(s.toLowerCase());
        if (!lowercase.contains(" for "))
            return null;
        String[] tokens = lowercase.substring(lowercase.indexOf(" for ") + 1).split(" ");
        if (tokens.length > 1)
            return tokens[1];
        return null;
    }

    private String addSpaceOnEitherEnd(String s) {
        return " " + s + " ";
    }
}
