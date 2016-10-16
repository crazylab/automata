package main.machine;

import main.TestCase;
import main.components.*;
import main.exceptions.InvalidMachineDefinition;
import main.statemachine.DFA;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFATest extends TestCase {

    private State q1;
    private State q2;
    private State q3;
    private Alphabet one;
    private Alphabet zero;

    @Before
    public void setUp() throws Exception {
        q1 = new State("q1");
        q2 = new State("q2");
        q3 = new State("q3");

        one = new Alphabet("1");
        zero = new Alphabet("0");
    }

    @Test
    public void ShouldBeAbleToBuildAFiniteStateMachine() throws Exception {
        States states = statesContaining(q1, q2, q3);
        Alphabets setOfAlphabets = alphabetsContaining(zero, one);

        TransitionTable transitionTable = new TransitionTable();
        //Recognizes power of two
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, one, q3);
        transitionTable.addTransition(q3, zero, q3);

        States finalStates = statesContaining(q2);

        DFA dfa = DFA.build(states, setOfAlphabets, q1, transitionTable, finalStates);

        assertTrue(dfa.isAccepted("0100"));
        assertFalse(dfa.isAccepted("010010"));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void ShouldThrowExceptionWhenTransitionTableDoesNotContainsAllAlphabetInAnyState() throws Exception {
        States states = statesContaining(q1, q2, q3);

        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, one, q3);

        States finalStates = statesContaining(q2);

        thrown.expect(InvalidMachineDefinition.class);
        thrown.expectMessage(CoreMatchers.is("Transition table does not contains all the transitions required for the given tuple"));

        DFA.build(states, alphabetsContaining(one, zero), q1, transitionTable, finalStates);
    }

    @Test
    public void ShouldThrowExceptionWhenTransitionTableDoesNotContainsTransitionsFromAllStates() throws Exception {
        States states = statesContaining(q1, q2, q3);

        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);

        States finalStates = statesContaining(q2);

        thrown.expect(InvalidMachineDefinition.class);
        thrown.expectMessage(CoreMatchers.is("Transition table does not contains all the transitions required for the given tuple"));

        DFA.build(states, alphabetsContaining(one, zero), q1, transitionTable, finalStates);
    }

    @Test
    public void ShouldThrowExceptionWhenSetOfStatesDoesNotContainsFinalState() throws Exception {
        States states = statesContaining(q1, q2, q3);

        TransitionTable transitionTable = new TransitionTable();
        //Recognizes power of two
        transitionTable.addTransition(q1, zero, q1);
        transitionTable.addTransition(q1, one, q2);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, one, q3);
        transitionTable.addTransition(q3, zero, q3);

        States finalStates = statesContaining(new State("q10"));

        thrown.expect(InvalidMachineDefinition.class);
        thrown.expectMessage(CoreMatchers.is("Set of states does not contains one or more final state"));

        DFA.build(states, alphabetsContaining(zero, one), q1, transitionTable, finalStates);
    }
}
