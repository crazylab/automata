package main.machine;

import main.exceptions.InvalidMachineDefinition;
import main.components.*;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFATest {
    @Test
    public void ShouldBeAbleToBuildAFiniteStateMachine() throws Exception {
        State q1 = new State("q1");                     //Initial State
        State q2 = new State("q2");                     //Final State
        State q3 = new State("q3");                     //Dead state
        SetOfStates setOfStates = new SetOfStates(q1, q2, q3);

        Alphabet one = new Alphabet("1");
        Alphabet zero = new Alphabet("0");
        AlphabetSet setOfAlphabetSet = new AlphabetSet(one, zero);

        TransitionTable transitionTable = new TransitionTable();
        //Recognizes power of two
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, one, q3);
        transitionTable.addTransition(q3, zero, q3);

        SetOfStates finalStates = new SetOfStates(q2);

        DFA dfa = DFA.build(setOfStates, setOfAlphabetSet, q1, transitionTable, finalStates);

        assertTrue(dfa.isAccepted("0100"));
        assertFalse(dfa.isAccepted("010010"));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void ShouldThrowExceptionWhenTransitionTableDoesNotContainsSomeStates() throws Exception {
        State q1 = new State("q1");                     //Initial State
        State q2 = new State("q2");                     //Final State
        State q3 = new State("q3");                     //Dead state
        SetOfStates setOfStates = new SetOfStates(q1, q2, q3);

        Alphabet one = new Alphabet("1");
        Alphabet zero = new Alphabet("0");

        TransitionTable transitionTable = new TransitionTable();
        //Recognizes power of two
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, one, q3);

        SetOfStates finalStates = new SetOfStates(q2);

        thrown.expect(InvalidMachineDefinition.class);
        thrown.expectMessage(CoreMatchers.is("Transition table does not contains all the transitions required for the given tuple"));

        DFA.build(setOfStates, new AlphabetSet(zero, one), q1, transitionTable, finalStates);
    }

    @Test
    public void ShouldThrowExceptionWhenSetOfStatesDoesNotContainsFinalState() throws Exception {
        State q1 = new State("q1");                     //Initial State
        State q2 = new State("q2");                     //Final State
        State q3 = new State("q3");                     //Dead state
        SetOfStates setOfStates = new SetOfStates(q1, q2, q3);

        Alphabet one = new Alphabet("1");
        Alphabet zero = new Alphabet("0");

        TransitionTable transitionTable = new TransitionTable();
        //Recognizes power of two
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, one, q3);
        transitionTable.addTransition(q3, zero, q3);

        SetOfStates finalStates = new SetOfStates(new State("q10"));

        thrown.expect(InvalidMachineDefinition.class);
        thrown.expectMessage(CoreMatchers.is("Set of states does not contains one or more final state"));

        DFA.build(setOfStates, new AlphabetSet(one, zero), q1, transitionTable, finalStates);
    }


}
