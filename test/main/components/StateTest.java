package main.components;

import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void ShouldBeAbleToMarkAStateAsFinalState() throws Exception {
        State q0 = new State("q0");
        assertFalse(q0.isFinal());

        q0.markAsFinal();
        assertTrue(q0.isFinal());
    }
}