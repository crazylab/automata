package main.components;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DataSet<T> {
    Set<T> dataSet = new HashSet<>();

    DataSet(Set<T> dataSet) {
        this.dataSet = dataSet;
    }

    DataSet(T[] data) {
        Collections.addAll(dataSet, data);
    }

    public boolean equals(Set<T> anotherSet) {
        return dataSet.containsAll(anotherSet) && anotherSet.containsAll(dataSet);
    }

    public boolean contains(T data) {
        return dataSet.contains(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataSet<T> that = (DataSet<T>) o;

        return dataSet != null ? dataSet.equals(that.dataSet) : that.dataSet == null;
    }

    @Override
    public int hashCode() {
        return dataSet != null ? dataSet.hashCode() : 0;
    }
}
