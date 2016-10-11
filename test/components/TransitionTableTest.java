package components;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransitionTableTest {

    private State q1;
    private Alphabet zero;
    private State q2;
    private Alphabet one;
    private State q3;
    private State q4;

    @Before
    public void setUp() throws Exception {
        q1 = new State("q1");
        q2 = new State("q2");
        q3 = new State("q3");
        q4 = new State("q4");

        zero = new Alphabet("0");
        one = new Alphabet("1");
    }

    @Test
    public void ShouldBeAbleToGetTheNextStateGivenTheCurrentStateWithAlphabet() throws Exception {
        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q1, zero, q2);
        transitionTable.addTransition(q1, one, q1);
        transitionTable.addTransition(q2, zero, q2);
        transitionTable.addTransition(q2, one, q3);
        transitionTable.addTransition(q3, zero, q3);
        transitionTable.addTransition(q3, one, q4);
        transitionTable.addTransition(q4, zero, q4);
        transitionTable.addTransition(q4, one, q4);

        Assert.assertEquals(q2, transitionTable.nextState(q1,zero));
        Assert.assertEquals(q1, transitionTable.nextState(q1,one));
        Assert.assertEquals(q2, transitionTable.nextState(q2,zero));
        Assert.assertEquals(q3, transitionTable.nextState(q2,one));
        Assert.assertEquals(q3, transitionTable.nextState(q3,zero));
        Assert.assertEquals(q4, transitionTable.nextState(q3,one));
        Assert.assertEquals(q4, transitionTable.nextState(q4,zero));
        Assert.assertEquals(q4, transitionTable.nextState(q4,one));
    }
}