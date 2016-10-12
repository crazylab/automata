package main.components;

import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void ShouldEqualWithAnotherStateWithSameName() throws Exception {
        State q0 = new State("q0");
        State anotherQ0 = new State("q0");

        assertEquals(anotherQ0, q0);
    }

    @Test
    public void ShouldNotEqualWithAnotherStateWithDifferentName() throws Exception {
        State q0 = new State("q0");
        State q1 = new State("q1");

        assertNotSame(q1, q0);
    }
}