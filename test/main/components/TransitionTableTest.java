package main.compbnts;

import main.TestCase;
import main.components.States;
import main.components.TransitionTable;
import org.junit.Test;

import static junit.framework.Assert.*;

public class TransitionTableTest extends TestCase{
    @Test
    public void ShouldBeAbleToGetTheNextStateGivenTheCurrentStateWithAlphabet() throws Exception {
        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q1, a, q2);
        transitionTable.addTransition(q1, b, q1);
        transitionTable.addTransition(q2, a, q2);
        transitionTable.addTransition(q2, b, q3);
        transitionTable.addTransition(q3, a, q3);
        transitionTable.addTransition(q3, b, q4);
        transitionTable.addTransition(q4, a, q4);
        transitionTable.addTransition(q4, b, q4);

        assertEquals(q2, transitionTable.nextState(q1, a));
        assertEquals(q1, transitionTable.nextState(q1, b));
        assertEquals(q2, transitionTable.nextState(q2, a));
        assertEquals(q3, transitionTable.nextState(q2, b));
        assertEquals(q3, transitionTable.nextState(q3, a));
        assertEquals(q4, transitionTable.nextState(q3, b));
        assertEquals(q4, transitionTable.nextState(q4, a));
        assertEquals(q4, transitionTable.nextState(q4, b));
    }

    @Test
    public void ShouldReturnTrueWhenTableContainsAllTheGivenStates() throws Exception {
        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q1, a, q2);
        transitionTable.addTransition(q1, b, q1);
        transitionTable.addTransition(q2, a, q2);
        transitionTable.addTransition(q2, b, q3);
        transitionTable.addTransition(q3, a, q3);
        transitionTable.addTransition(q3, b, q4);
        transitionTable.addTransition(q4, a, q4);
        transitionTable.addTransition(q4, b, q4);

        States inputStates = statesContaining(q1,q2,q3,q4);

        assertTrue(transitionTable.isValid(inputStates, alphabetsContaining(a, b)));
    }

    @Test
    public void ShouldReturnFalseWhenTableDoesNotContainsAnyOfTheStateOrHasMissingTransitionForGivenStatesAndAlphabets() throws Exception {
        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q1, a, q2);
        transitionTable.addTransition(q1, b, q1);
        transitionTable.addTransition(q2, a, q2);
        transitionTable.addTransition(q2, b, q3);
        transitionTable.addTransition(q3, a, q3);
        transitionTable.addTransition(q3, b, q4);
        transitionTable.addTransition(q4, a, q4);

        States inputStates = statesContaining(q1, q2, q3);

        assertFalse(transitionTable.isValid(inputStates, alphabetsContaining(a)));
    }
}