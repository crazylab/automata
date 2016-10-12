package main;

import main.components.Alphabet;
import main.components.State;
import main.components.TransitionTable;

import java.util.List;
import java.util.Set;

class FiniteStateMachine {
    private final TransitionTable transitionTable;
    private final State startingState;
    private Set<State> finalStates;

    FiniteStateMachine(State startingState, TransitionTable transitionTable, Set<State> finalStates) {
        this.transitionTable = transitionTable;
        this.startingState = startingState;
        this.finalStates = finalStates;
    }

    boolean isAccepted(List<Alphabet> string) {
        State presentState = startingState;
        for (Alphabet alphabet : string) {
            presentState = transitionTable.nextState(presentState, alphabet);
        }
        return finalStates.contains(presentState);
    }
}
