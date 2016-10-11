package components;

public class Alphabet {
    private final String alphabetName;

    public Alphabet(String alphabetName) {
        this.alphabetName = alphabetName;
    }

    @Override
    public String toString() {
        return alphabetName;
    }
}
