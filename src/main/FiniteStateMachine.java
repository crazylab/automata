package main;

import main.io.InputFile;
import main.utils.InputMachineDescription;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class FiniteStateMachine {
    public static void main(String[] args) throws IOException, ParseException, InvalidMachineDefinition {
        String filePath = args[0];
        String inputJson = new InputFile(filePath).read();
        InputMachineDescription machineDesc = new InputMachineDescription(inputJson);
        DFA dfa = DFA.build(machineDesc.states(),
                machineDesc.alphabet(),
                machineDesc.startState(),
                machineDesc.transitionTable(),
                machineDesc.finalStates()
        );



    }
}
