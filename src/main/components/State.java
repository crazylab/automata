package main.components;

public class State {
    private final String name;
    private boolean isMarkedAsFinal;

    public State(String name) {
        this.name = name;
        this.isMarkedAsFinal = false;
    }

    @Override
    public String toString() {
        return name;
    }

    public State markAsFinal() {
        this.isMarkedAsFinal = true;
        return this;
    }

    public boolean isFinal() {
        return isMarkedAsFinal;
    }
}
