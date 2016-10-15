package main.components;

import java.util.HashMap;
import java.util.Set;

public class TransitionTable {

    private final HashMap<State, HashMap<Alphabet, State>> transitions;

    public TransitionTable() {
        transitions = new HashMap<>();
    }

    public void addTransition(State presentState, Alphabet alphabet, State nextState) {
        if (transitions.containsKey(presentState)) {
            HashMap<Alphabet, State> transitionsFromPresentState = transitions.get(presentState);
            transitionsFromPresentState.put(alphabet, nextState);
        } else {
            HashMap<Alphabet, State> transitionFromPresentState = new HashMap<>();
            transitionFromPresentState.put(alphabet, nextState);
            transitions.put(presentState, transitionFromPresentState);
        }
    }

    public State nextState(State presentState, Alphabet alphabet) {
        return transitions.get(presentState).get(alphabet);
    }

    public boolean isValidFor(SetOfStates states, AlphabetSet alphabetSet) {
        if (states.equals(transitions.keySet())) {
            for (State state : transitions.keySet()) {
                Set<Alphabet> allAlphabets = transitions.get(state).keySet();
                if (!alphabetSet.equals(allAlphabets)) return false;
            }
            return true;
        }
        return false;

    }
}
