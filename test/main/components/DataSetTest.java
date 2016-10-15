package main.components;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataSetTest {

    private State q2;
    private State q1;
    private State q3;
    private State q4;

    private Alphabet b;
    private Alphabet a;

    @Before
    public void setUp() throws Exception {
        q1 = new State("q1");
        q2 = new State("q2");
        q3 = new State("q3");
        q4 = new State("q4");

        a = new Alphabet("a");
        b = new Alphabet("b");
    }

    @Test
    public void ShouldBeAbleToCompareIfAnotherAlphabetSetIsEqualToTheAlphabetSetOrNot() throws Exception {
        AlphabetSet alphabetSet = new AlphabetSet(a, b);
        AlphabetSet anotherAlphabetSet = new AlphabetSet(a, b);

        assertTrue(alphabetSet.equals(anotherAlphabetSet));
    }

    @Test
    public void ShouldBeAbleToCompareIfASetIsEqualToTheAlphabetSetOrNot() throws Exception {
        AlphabetSet alphabetSet = new AlphabetSet(a, b);
        HashSet<Alphabet> another = new HashSet<>();
        another.add(a);

        assertFalse(alphabetSet.equals(another));

        another.add(b);
        assertTrue(alphabetSet.equals(another));

    }

    @Test
    public void ShouldBeAbleToCompareIfASetIsEqualToTheSetOfStateOrNot() throws Exception {
        SetOfStates setOfStates = new SetOfStates(q1, q2);
        SetOfStates anotherSetOfStates = new SetOfStates(q1, q2);

        assertTrue(setOfStates.equals(anotherSetOfStates));
    }

    @Test
    public void ShouldReturnTrueWhenItContainsAllTheStatesInTheOtherSetOfStates() throws Exception {
        SetOfStates setOfStates = new SetOfStates(q1, q2, q3);

        HashSet<State> anotherSet = new HashSet<>();
        anotherSet.add(q1);
        anotherSet.add(q2);

        assertTrue(setOfStates.equals(anotherSet));
    }

    @Test
    public void ShouldReturnFalseWhenItDoesNotContainsAllTheStatesInTheOtherSetOfStates() throws Exception {
        SetOfStates setOfStates = new SetOfStates(q1, q2, q3);
        HashSet<State> anotherSet = new HashSet<>();
        anotherSet.add(q1);
        anotherSet.add(q2);
        anotherSet.add(q4);

        assertFalse(setOfStates.equals(anotherSet));
    }

    @Test
    public void ShouldReturnTrueWhenItContainsTheGivenState() throws Exception {
        SetOfStates setOfStates = new SetOfStates(q2, q3);

        assertTrue(setOfStates.contains(q2));
        assertFalse(setOfStates.contains(q1));
    }
}