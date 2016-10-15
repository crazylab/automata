package main.components;

import java.util.Set;

public class AlphabetSet extends DataSet<Alphabet> {
    public AlphabetSet(Set<Alphabet> alphabets) {
        super(alphabets);
    }

    public AlphabetSet(Alphabet... alphabets) {
        super(alphabets);
    }
}
