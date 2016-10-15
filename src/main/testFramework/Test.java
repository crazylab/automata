package main.testFramework;


import main.exceptions.InvalidMachineDefinition;
import main.machine.DFA;
import main.utils.InputMachineDescription;

import java.util.List;

public class Test {
    private InputMachineDescription machineDesc;
    private int success = 0;
    private int fail = 0;

    public Test(InputMachineDescription machineDesc) {
        this.machineDesc = machineDesc;
    }

    public String run() {
        System.out.println("TEST : " + machineDesc.name());
        System.out.println("Tuple Description : " + machineDesc.tupleDesc());
        DFA dfa = getMachine();

        assertTrue(dfa);
        System.out.println("\t\t----------------");
        assertFalse(dfa);
        System.out.println("-----------------------------------------------------------------------------------------");
        return success + " Test Passed\n"+fail+" Test Failed";
    }

    private void assertTrue(DFA dfa) {
        List<String> passCases = machineDesc.passCases();
        System.out.println("Pass Cases: ");
        for (String inputString : passCases) {
            System.out.print("\tRunning \"" + inputString + "\" : ");
            if (dfa.isAccepted(inputString)) {
                System.out.println("Passed");
                success++;
            } else {
                System.out.println("Failed");
                fail++;
            }
        }
    }

    private void assertFalse(DFA dfa) {
        List<String> passCases = machineDesc.failCases();
        System.out.println("Fail Cases: ");
        for (String inputString : passCases) {
            System.out.print("\tRunning " + inputString + " : ");
            if (dfa.isAccepted(inputString)) {
                System.out.println("Failed");
                fail++;
            } else {
                System.out.println("Success");
                success++;
            }
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
