package main.components;

public class Alphabet {
    private final String alphabetName;

    public Alphabet(String alphabetName) {
        this.alphabetName = alphabetName;
    }

    @Override
    public String toString() {
        return alphabetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alphabet alphabet = (Alphabet) o;
        return alphabetName != null ? alphabetName.equals(alphabet.alphabetName) : alphabet.alphabetName == null;
    }

    @Override
    public int hashCode() {
        return alphabetName != null ? alphabetName.hashCode() : 0;
    }
}
