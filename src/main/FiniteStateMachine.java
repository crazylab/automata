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

    public static FiniteStateMachine build(Set<State> setOfStates,
                                           Set<Alphabet> setOfAlphabet,
                                           State startingState,
                                           TransitionTable transitionTable,
                                           Set<State> finalStates) throws InvalidMachineDefinition {

        if(!transitionTable.isValidFor(setOfStates, setOfAlphabet))
            throw new InvalidMachineDefinition("Transition table does not contains all the transitions required for the given tuple");
        return new FiniteStateMachine(startingState, transitionTable, finalStates);
    }

    private FiniteStateMachine(State startingState,
                               TransitionTable transitionTable,
                               Set<State> finalStates) {
        this.startingState = startingState;
        this.transitionTable = transitionTable;
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
