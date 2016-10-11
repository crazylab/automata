package components;

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

    public void markAsFinal() {
        this.isMarkedAsFinal = true;
    }

    public boolean isFinal() {
        return isMarkedAsFinal;
    }
}
