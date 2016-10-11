package main;

import main.components.Alphabet;
import main.components.State;
import main.components.TransitionTable;

import java.util.List;

class FiniteStateMachine {
    private final TransitionTable transitionTable;
    private final State startingState;

    FiniteStateMachine(State startingState, TransitionTable transitionTable) {
        this.transitionTable = transitionTable;
        this.startingState = startingState;
    }

    boolean isAccepted(List<Alphabet> string) {
        State presentState = startingState;
        for (Alphabet alphabet : string) {
            presentState = transitionTable.nextState(presentState, alphabet);
        }
        return presentState.isFinal();
    }
}
