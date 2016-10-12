package main;

import main.components.Alphabet;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFABuilderTest {

    private Alphabet zero;
    private Alphabet one;

    @Before
    public void setUp() throws Exception {
        one = new Alphabet("1");
        zero = new Alphabet("0");
    }

    @Test
    public void ShouldBeAbleToBuildAFiniteStateMachineFromJSONString() throws Exception {
//        String fiveTupleAsJSON = "{'Q':['q1','q2','q3'],'SIGMA':['0','1'],'DELTA':['q1,0,q1','q1,1,q2','q2,0,q2','q2,1,q3','q3,0,q3','q3,1,q3'],'q0':'q1','qf':['q2']}";
//        FiniteStateMachine DFA = new DFABuilder(fiveTupleAsJSON).build();
//
//        assertTrue(DFA.isAccepted(Arrays.asList(zero, one, zero, zero)));
//        assertFalse(DFA.isAccepted(Arrays.asList(zero, one, zero, zero, one, zero)));
    }
}