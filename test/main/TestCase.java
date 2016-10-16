package main;

import main.components.Alphabet;
import main.components.Alphabets;
import main.components.State;
import main.components.States;

import java.util.Arrays;
import java.util.HashSet;

public class TestCase {

    public State q1 = new State("q1");
    public State q2 = new State("q2");
    public State q3 = new State("q3");
    public State q4 = new State("q4");

    public Alphabet a = new Alphabet("0");
    public Alphabet b = new Alphabet("1");

    public States statesContaining(State... states) {
        HashSet<State> setOfStates = new HashSet<>();
        setOfStates.addAll(Arrays.asList(states));
        return new States(setOfStates);
    }

    public Alphabets alphabetsContaining(Alphabet... alphabets) {
        HashSet<Alphabet> setOfAlphabets = new HashSet<>();
        setOfAlphabets.addAll(Arrays.asList(alphabets));
        return new Alphabets(setOfAlphabets);
    }
}
