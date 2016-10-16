package main.components;

import java.util.Set;

public class States extends DataSet<State> {
    public States(Set<State> dataSet) {
        super(dataSet);
    }

    public boolean containsAll(States anotherSet) {
        return this.dataSet.containsAll(anotherSet.dataSet);
    }
}
