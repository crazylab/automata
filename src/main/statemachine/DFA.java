package main.statemachine;

import main.exceptions.InvalidMachineDefinition;
import main.components.*;

public class DFA {
    private final TransitionTable transitionTable;
    private final State startingState;
    private States finalStates;

    public static DFA build(States states,
                            Alphabets setOfAlphabet,
                            State startingState,
                            TransitionTable transitionTable,
                            States finalStates) throws InvalidMachineDefinition {

        if (!transitionTable.isValid(states, setOfAlphabet)) {
            throw new InvalidMachineDefinition("Transition table does not contains all the transitions required for the given tuple");
        } else if (!states.containsAll(finalStates)) {
            throw new InvalidMachineDefinition("Set of states does not contains one or more final state");
        }
        return new DFA(startingState, transitionTable, finalStates);
    }

    private DFA(State startingState,
                TransitionTable transitionTable,
                States finalStates) {
        this.startingState = startingState;
        this.transitionTable = transitionTable;
        this.finalStates = finalStates;
    }

    public boolean isAccepted(String string) {
        State presentState = startingState;
        for (char alphabet : string.toCharArray()) {
            presentState = transitionTable.nextState(presentState, new Alphabet(Character.toString(alphabet)));
        }
        return finalStates.contains(presentState);
    }
}
