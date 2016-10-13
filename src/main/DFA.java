package main;

import main.components.Alphabet;
import main.components.State;
import main.components.TransitionTable;

import java.util.List;
import java.util.Set;

class DFA {
    private final TransitionTable transitionTable;
    private final State startingState;
    private Set<State> finalStates;

    public static DFA build(Set<State> setOfStates,
                            Set<Alphabet> setOfAlphabet,
                            State startingState,
                            TransitionTable transitionTable,
                            Set<State> finalStates) throws InvalidMachineDefinition {

        if(!transitionTable.isValidFor(setOfStates, setOfAlphabet)) {
            throw new InvalidMachineDefinition("Transition table does not contains all the transitions required for the given tuple");
        } else if(!setOfStates.containsAll(finalStates)){
            throw new InvalidMachineDefinition("Set of states does not contains one or more final state");
        }
        return new DFA(startingState, transitionTable, finalStates);
    }

    private DFA(State startingState,
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
