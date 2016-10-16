package main.utils;

import main.components.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MachineDesc {

    private JSONObject machineDesc;

    public MachineDesc(JSONObject machineDesc) throws ParseException {
        this.machineDesc = machineDesc;
    }

    public String name() {
        return (String) machineDesc.get("name");
    }

    public String type() {
        return (String) machineDesc.get("type");
    }

    public States states() {
        JSONObject tuple = (JSONObject) machineDesc.get("tuple");
        Collection<String> statesAsArray = (Collection<String>) tuple.get("states");

        HashSet<State> states = new HashSet<>();
        for (String stateName : statesAsArray) {
            states.add(new State(stateName));
        }
        return new States(states);
    }

    public States finalStates() {
        JSONObject tuple = (JSONObject) machineDesc.get("tuple");
        Collection<String> finalStatesAsArray = (Collection<String>) tuple.get("final-states");

        HashSet<State> finalStates = new HashSet<>();
        for (String stateName : finalStatesAsArray) {
            finalStates.add(new State(stateName));
        }
        return new States(finalStates);
    }

    public State start() {
        JSONObject tuple = (JSONObject) machineDesc.get("tuple");
        return new State((String) tuple.get("start-state"));
    }

    public Alphabets alphabets() {
        JSONObject tuple = (JSONObject) machineDesc.get("tuple");
        Collection<String> alphabetCollection = (Collection<String>) tuple.get("alphabets");
        HashSet<Alphabet> alphabets = new HashSet<>();
        for (String alphabetName : alphabetCollection) {
            alphabets.add(new Alphabet(alphabetName));
        }
        return new Alphabets(alphabets);
    }

    public TransitionTable transitionTable() {
        JSONObject tuple = (JSONObject) machineDesc.get("tuple");
        JSONObject delta = (JSONObject) tuple.get("delta");
        Set<String> keys = delta.keySet();
        TransitionTable transitionTable = new TransitionTable();
        for (String sourceState : keys) {
            JSONObject transitions = (JSONObject) delta.get(sourceState);
            Set<String> alphabets = transitions.keySet();
            for (String alphabet : alphabets) {
                transitionTable.addTransition(new State(sourceState), new Alphabet(alphabet), new State((String) transitions.get(alphabet)));
            }
        }
        return transitionTable;
    }

    public List<String> passCases() {
        return (List<String>) machineDesc.get("pass-cases");
    }

    public List<String> failCases() {
        return (List<String>) machineDesc.get("fail-cases");
    }

    public String tupleDesc() {
        return machineDesc.get("tuple").toString();
    }
}
