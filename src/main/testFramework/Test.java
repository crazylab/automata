package main.testFramework;


import main.exceptions.InvalidMachineDefinition;
import main.machine.DFA;
import main.utils.InputMachineDescription;

import java.util.List;

public class Test {
    private InputMachineDescription machineDesc;

    public Test(InputMachineDescription machineDesc) {
        this.machineDesc = machineDesc;
    }

    public void run() {
        System.out.println("TEST : " + machineDesc.name());
        System.out.println("Tuple Description : " + machineDesc.tupleDesc());
        DFA dfa = getMachine();

        assertTrue(dfa);
        System.out.println("\t\t----------------");
        assertFalse(dfa);
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void assertTrue(DFA dfa) {
        List<String> passCases = machineDesc.passCases();
        System.out.println("Pass Cases: ");
        for (String inputString : passCases) {
            System.out.print("\tRunning \"" + inputString + "\" : ");
            System.out.println(dfa.isAccepted(inputString) ? "Passed" : "Failed");
        }
    }

    private void assertFalse(DFA dfa) {
        List<String> passCases = machineDesc.failCases();
        System.out.println("Fail Cases: ");
        for (String inputString : passCases) {
            System.out.print("\tRunning " + inputString + " : ");
            System.out.println((dfa.isAccepted(inputString) ? "Failed" : "Passed"));
        }
    }

    private DFA getMachine() {
        try {
            return DFA.build(machineDesc.states(),
                    machineDesc.alphabets(),
                    machineDesc.startState(),
                    machineDesc.transitionTable(),
                    machineDesc.finalStates());
        } catch (InvalidMachineDefinition invalidMachineDefinition) {
            invalidMachineDefinition.printStackTrace();
        }
        return null;
    }
}
