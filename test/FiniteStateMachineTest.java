import components.Alphabet;
import components.State;
import components.TransitionTable;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class FiniteStateMachineTest {
    @Test
    public void ShouldBeAbleToBuildAFiniteStateMachine() throws Exception {
        State q1 = new State("q1");     //Initial State
        State q2 = new State("q2");     //Final State
        State q3 = new State("q3");     //Dead state

        HashSet<State> setOfStates = new HashSet<>();
        setOfStates.add(q1);
        setOfStates.add(q2);
        setOfStates.add(q3);

        Alphabet one = new Alphabet("1");
        Alphabet zero = new Alphabet("0");

        HashSet<Alphabet> setOfAlphabets = new HashSet<>();
        setOfAlphabets.add(one);
        setOfAlphabets.add(zero);

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

        FiniteStateMachine DFA = new FiniteStateMachine(setOfStates, setOfAlphabets, transitionTable, q1, finalStates);

        assertTrue(DFA.isAccepted(Arrays.asList(zero, one, zero, zero)));
        assertFalse(DFA.isAccepted(Arrays.asList(zero, one, zero, zero, one, zero)));
    }
}
