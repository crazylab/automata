package main.utils;

import main.components.Alphabet;
import main.components.State;
import main.components.TransitionTable;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InputMachineDescriptionTest {

    private InputMachineDescription inputMachineDescription;

    @Before
    public void setUp() throws Exception {
        String jsonDescription = "{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}";
        inputMachineDescription = new InputMachineDescription(jsonDescription);
    }

    @Test
    public void ShouldBeAbleToGetTheMachineDescriptionName() throws Exception {
        assertEquals("odd number of zeroes", inputMachineDescription.getName());
    }

    @Test
    public void ShouldBeAbleToGetTheMachineType() throws Exception {
        assertEquals("dfa", inputMachineDescription.getType());
    }

    @Test
    public void ShouldBeAbleToGetTheStartState() throws Exception {
        assertEquals(new State("q1"), inputMachineDescription.startState());
    }

    @Test
    public void ShouldBeAbleToGetTheStates() throws Exception {
        Set<State> expected = new HashSet<>();
        expected.add(new State("q1"));
        expected.add(new State("q2"));

        assertEquals(expected, inputMachineDescription.states());
    }

    @Test
    public void ShouldBeAbleToGetTheFinalStates() throws Exception {
        Set<State> expected = new HashSet<>();
        expected.add(new State("q2"));

        assertEquals(expected, inputMachineDescription.finalStates());
    }

    @Test
    public void ShouldBeAbleToGetTheAlphabets() throws Exception {
        Set<Alphabet> expected = new HashSet<>();
        expected.add(new Alphabet("1"));
        expected.add(new Alphabet("0"));

        assertEquals(expected, inputMachineDescription.alphabet());
    }

    @Test
    public void ShouldBeAbleToGetTransitionTable() throws Exception {
        State q1 = new State("q1");
        State q2 = new State("q2");

        Alphabet zero = new Alphabet("0");
        Alphabet one = new Alphabet("1");

        TransitionTable transitionTable = inputMachineDescription.transitionTable();

        assertEquals(q2, transitionTable.nextState(q1, zero));
        assertEquals(q1, transitionTable.nextState(q1, one));
        assertEquals(q1, transitionTable.nextState(q2, zero));
        assertEquals(q2, transitionTable.nextState(q2, one));
    }

    @Test
    public void ShouldBeAbleToGetPassCases() throws Exception {
        Alphabet zero = new Alphabet("0");
        Alphabet one = new Alphabet("1");

        List<Alphabet> passCases = inputMachineDescription.passCases();
    }

}