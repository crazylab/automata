package main.utils;

import main.TestCase;
import main.components.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MachineDescTest extends TestCase{

    private MachineDesc machineDesc;

    @Before
    public void setUp() throws Exception {
        String jsonDescription = "{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}";
        JSONObject machineDescription = (JSONObject) new JSONParser().parse(jsonDescription);
        this.machineDesc = new MachineDesc(machineDescription);
    }

    @Test
    public void ShouldBeAbleToGetTheMachineDescriptionName() throws Exception {
        Assert.assertEquals("odd number of zeroes", machineDesc.name());
    }

    @Test
    public void ShouldBeAbleToGetTheMachineType() throws Exception {
        Assert.assertEquals("dfa", machineDesc.type());
    }

    @Test
    public void ShouldBeAbleToGetTheStartState() throws Exception {
        Assert.assertEquals(q1, machineDesc.start());
    }

    @Test
    public void ShouldBeAbleToGetTheStates() throws Exception {
        States expected = statesContaining(q1, q2);

        Assert.assertEquals(expected, machineDesc.states());
    }

    @Test
    public void ShouldBeAbleToGetTheFinalStates() throws Exception {
        States expected = statesContaining(q2);

        Assert.assertEquals(expected, machineDesc.finalStates());
    }

    @Test
    public void ShouldBeAbleToGetTheAlphabets() throws Exception {
        Alphabets expected = alphabetsContaining(a, b);

        Assert.assertEquals(expected, machineDesc.alphabets());
    }

    @Test
    public void ShouldBeAbleToGetTransitionTable() throws Exception {
        TransitionTable transitionTable = machineDesc.transitionTable();

        assertEquals(q2, transitionTable.nextState(q1, a));
        assertEquals(q1, transitionTable.nextState(q1, b));
        assertEquals(q1, transitionTable.nextState(q2, a));
        assertEquals(q2, transitionTable.nextState(q2, b));
    }

    @Test
    public void ShouldBeAbleToGetPassCasesAsListOfInputString() throws Exception {
        List<String> actual = machineDesc.passCases();
        assertEquals(Arrays.asList("0", "000", "00000", "10", "101010", "010101"), actual);
    }

    @Test
    public void ShouldBeAbleToGetFailCasesAsListOfInputString() throws Exception {
        List<String> actual = machineDesc.failCases();
        assertEquals(Arrays.asList("00", "0000", "1001", "1010", "001100"), actual);
    }
}