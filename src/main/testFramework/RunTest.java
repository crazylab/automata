package main.testFramework;

import main.exceptions.InvalidMachineDefinition;
import main.io.InputFile;
import main.utils.InputMachineDescription;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ListIterator;

public class RunTest {
    public static void main(String[] args) throws IOException, ParseException, InvalidMachineDefinition {
        String filePath = args[0];
        String inputJson = new InputFile(filePath).read();
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(inputJson);
        ListIterator jsonIterator = jsonArray.listIterator();
        while (jsonIterator.hasNext()) {
            InputMachineDescription machineDesc = new InputMachineDescription(jsonIterator.next().toString());
            Test test = new Test(machineDesc);
            test.run();
        }
    }
}
