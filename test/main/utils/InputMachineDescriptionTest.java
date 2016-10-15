package main.utils;

import main.components.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InputMachineDescriptionTest {

    private InputMachineDescription inputMachineDescription;
    private Alphabet zero;
    private Alphabet one;
    private State q1;
    private State q2;

    @Before
    public void setUp() throws Exception {
        String jsonDescription = "{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}";
        inputMachineDescription = new InputMachineDescription(jsonDescription);

        zero = new Alphabet("0");
        one = new Alphabet("1");

        q1 = new State("q1");
        q2 = new State("q2");
    }

    @Test
    public void ShouldBeAbleToGetTheMachineDescriptionName() throws Exception {
        assertEquals("odd number of zeroes", inputMachineDescription.name());
    }

    @Test
    public void ShouldBeAbleToGetTheMachineType() throws Exception {
        assertEquals("dfa", inputMachineDescription.getType());
    }

    @Test
    public void ShouldBeAbleToGetTheStartState() throws Exception {
        assertEquals(q1, inputMachineDescription.startState());
    }

    @Test
    public void ShouldBeAbleToGetTheStates() throws Exception {
        SetOfStates expected = new SetOfStates(q1, q2);

        assertEquals(expected, inputMachineDescription.states());
    }

    @Test
    public void ShouldBeAbleToGetTheFinalStates() throws Exception {
        SetOfStates expected = new SetOfStates(q2);

        assertEquals(expected, inputMachineDescription.finalStates());
    }

    @Test
    public void ShouldBeAbleToGetTheAlphabets() throws Exception {
        AlphabetSet expected = new AlphabetSet(one, zero);

        assertEquals(expected, inputMachineDescription.alphabets());
    }

    @Test
    public void ShouldBeAbleToGetTransitionTable() throws Exception {
        TransitionTable transitionTable = inputMachineDescription.transitionTable();

        assertEquals(q2, transitionTable.nextState(q1, zero));
        assertEquals(q1, transitionTable.nextState(q1, one));
        assertEquals(q1, transitionTable.nextState(q2, zero));
        assertEquals(q2, transitionTable.nextState(q2, one));
    }

    @Test
    public void ShouldBeAbleToGetPassCasesAsListOfInputString() throws Exception {
        List<String> actual = inputMachineDescription.passCases();
        assertEquals(Arrays.asList("0","000","00000","10","101010","010101"), actual);
    }

    @Test
    public void ShouldBeAbleToGetFailCasesAsListOfInputString() throws Exception {
        List<String> actual = inputMachineDescription.failCases();
        assertEquals(Arrays.asList("00","0000","1001","1010","001100"), actual);
    }

}