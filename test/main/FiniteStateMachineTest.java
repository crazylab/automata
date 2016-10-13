package main;

import main.components.Alphabet;
import main.components.State;
import main.components.TransitionTable;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FiniteStateMachineTest {
    @Test
    public void ShouldBeAbleToBuildAFiniteStateMachine() throws Exception {
        State q1 = new State("q1");                     //Initial State
        State q2 = new State("q2");                     //Final State
        State q3 = new State("q3");                     //Dead state
        HashSet<State> setOfState = new HashSet<>();
        setOfState.add(q1);
        setOfState.add(q2);
        setOfState.add(q3);

        Alphabet one = new Alphabet("1");
        Alphabet zero = new Alphabet("0");
        HashSet<Alphabet> setOfAlphabet = new HashSet<>();
        setOfAlphabet.add(one);
        setOfAlphabet.add(zero);

        TransitionTable transitionTable = new TransitionTable();
        //Recognizes power of two
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, one, q3);
        transitionTable.addTransition(q3, zero, q3);

        HashSet<State> finalStates = new HashSet<>();
        finalStates.add(q2);

        FiniteStateMachine DFA = FiniteStateMachine.build(setOfState, setOfAlphabet, q1, transitionTable, finalStates);

        assertTrue(DFA.isAccepted(Arrays.asList(zero, one, zero, zero)));
        assertFalse(DFA.isAccepted(Arrays.asList(zero, one, zero, zero, one, zero)));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void ShouldThrowExceptionWhenTransitionTableDoesNotContainsSomeStates() throws Exception {
        State q1 = new State("q1");                     //Initial State
        State q2 = new State("q2");                     //Final State
        State q3 = new State("q3");                     //Dead state
        HashSet<State> setOfState = new HashSet<>();
        setOfState.add(q1);
        setOfState.add(q2);
        setOfState.add(q3);

        Alphabet one = new Alphabet("1");
        Alphabet zero = new Alphabet("0");
        HashSet<Alphabet> setOfAlphabet = new HashSet<>();
        setOfAlphabet.add(one);
        setOfAlphabet.add(zero);

        TransitionTable transitionTable = new TransitionTable();
        //Recognizes power of two
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, one, q3);

        HashSet<State> finalStates = new HashSet<>();
        finalStates.add(q2);

        thrown.expect(InvalidMachineDefinition.class);
        thrown.expectMessage(CoreMatchers.is("Transition table does not contains all the transitions required for the given tuple"));

        FiniteStateMachine.build(setOfState, setOfAlphabet, q1, transitionTable, finalStates);

    }


}
