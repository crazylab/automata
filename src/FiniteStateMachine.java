import components.Alphabet;
import components.State;
import components.TransitionTable;

import java.util.List;
import java.util.Set;

public class FiniteStateMachine {
    private final Set<State> setOfStates;
    private final Set<Alphabet> setOfAlphabets;
    private final TransitionTable transitionTable;
    private final State startingState;
    private final Set<State> finalStates;

    FiniteStateMachine(Set<State> setOfStates,
                       Set<Alphabet> setOfAlphabets,
                       TransitionTable transitionTable,
                       State startingState,
                       Set<State> finalStates) {
        this.setOfStates = setOfStates;
        this.setOfAlphabets = setOfAlphabets;
        this.transitionTable = transitionTable;
        this.startingState = startingState;
        this.finalStates = finalStates;

        for (State state : finalStates) {
            state.markAsFinal();
        }
    }

    boolean isAccepted(List<Alphabet> string) {
        State presentState = startingState;
        for (Alphabet alphabet : string) {
            presentState = transitionTable.nextState(presentState,alphabet);
        }
        return presentState.isFinal();
    }
}
