package com.thegingerbeardd.ttbot.party;

import com.thegingerbeardd.ttbot.character.PlayerCharacter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PartyTest {

    private class MockCharacter extends PlayerCharacter {
        private MockCharacter(String name) {
            this.name = name;
        }
    }

    @Test
    public void partyBuilderSetMembersWorks() {
        String testName1 = "Joe";
        String testName2 = "Bob";
        String testName3 = "Bill";
        String nameNotInParty = "Phil";
        List<PlayerCharacter> partyMembers = new ArrayList<>();
        partyMembers.add(new MockCharacter(testName1));
        partyMembers.add(new MockCharacter(testName2));
        partyMembers.add(new MockCharacter(testName3));
        Party party = new Party.PartyBuilder()
                .setMembers(partyMembers)
                .build();
        assertThat("Party contains " + testName1, party.getMemberWithName(testName1), is(new MockCharacter(testName1)));
        assertThat("Party contains " + testName2, party.getMemberWithName(testName2), is(new MockCharacter(testName2)));
        assertThat("Party contains " + testName3, party.getMemberWithName(testName3), is(new MockCharacter(testName3)));
        assertThat("Party does not contain " + nameNotInParty, party.getMemberWithName("Phil"), is(nullValue()));
    }

    @Test
    public void partyGetMemberWithNameWorksAsIntended() {
        String testName1 = "Joe";
        String testName2 = "Bob";
        String testName3 = "Bill";
        String nameNotInParty = "Phil";
        List<PlayerCharacter> partyMembers = new ArrayList<>();
        partyMembers.add(new MockCharacter(testName1));
        partyMembers.add(new MockCharacter(testName2));
        partyMembers.add(new MockCharacter(testName3));
        Party party = new Party.PartyBuilder()
                .setMembers(partyMembers)
                .build();
        assertThat(String.format("Party contains %s", testName1), party.getMemberWithName(testName1), is(new MockCharacter(testName1)));
        assertThat(String.format("Party contains %s", testName2), party.getMemberWithName(testName2), is(new MockCharacter(testName2)));
        assertThat(String.format("Party contains %s", testName3), party.getMemberWithName(testName3), is(new MockCharacter(testName3)));
        assertThat(String.format("%s is not %s", testName1, testName2), party.getMemberWithName(testName1), is(not(new MockCharacter(testName2))));
        assertThat(String.format("%s is not null", testName1), party.getMemberWithName(testName1), is(not(nullValue())));
        assertThat(String.format("%s is not any random thing", testName1), party.getMemberWithName(testName1), is(not(new ArrayList<>())));
    }

}
