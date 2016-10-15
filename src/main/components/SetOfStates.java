package main.components;

import java.util.Set;

public class SetOfStates extends DataSet<State> {
    public SetOfStates(State... data) {
        super(data);
    }

    public SetOfStates(Set<State> dataSet) {
        super(dataSet);
    }

    public boolean containsAll(SetOfStates anotherSet) {
        return this.dataSet.containsAll(anotherSet.dataSet);
    }
}
