package main.components;

import main.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataSetTest extends TestCase{

    @Test
    public void ShouldBeAbleToCompareIfAnotherAlphabetSetIsEqualToTheAlphabetSetOrNot() throws Exception {
        Alphabets alphabets = alphabetsContaining(a, b);
        Alphabets anotherAlphabets = alphabetsContaining(a, b);

        assertTrue(alphabets.equals(anotherAlphabets));
    }

    @Test
    public void ShouldBeAbleToCompareIfASetIsEqualToTheAlphabetSetOrNot() throws Exception {
        Alphabets alphabets = alphabetsContaining(a, b);
        HashSet<Alphabet> another = new HashSet<>();
        another.add(a);

        assertFalse(alphabets.equals(another));

        another.add(b);
        assertTrue(alphabets.equals(another));

    }

    @Test
    public void ShouldBeAbleToCompareIfASetIsEqualToTheSetOfStateOrNot() throws Exception {
        States states = statesContaining(q1, q2);
        States anotherStates = statesContaining(q1, q2);

        assertTrue(states.equals(anotherStates));
    }

    @Test
    public void ShouldReturnTrueWhenItContainsAllTheStatesInTheOtherSetOfStates() throws Exception {
        States states = statesContaining(q1, q2, q3);

        HashSet<State> anotherSet = new HashSet<>();
        anotherSet.add(q1);
        anotherSet.add(q2);
        anotherSet.add(q3);

        assertTrue(states.equals(anotherSet));
    }

    @Test
    public void ShouldReturnFalseWhenItDoesNotContainsAllTheStatesInTheOtherSetOfStates() throws Exception {
        States states = statesContaining(q1, q2, q3);
        HashSet<State> anotherSet = new HashSet<>();
        anotherSet.add(q1);
        anotherSet.add(q2);
        anotherSet.add(q4);

        assertFalse(states.equals(anotherSet));
    }

    @Test
    public void ShouldReturnTrueWhenItContainsTheGivenState() throws Exception {
        States states = statesContaining(q2, q3);

        assertTrue(states.contains(q2));
        assertFalse(states.contains(q1));
    }
}