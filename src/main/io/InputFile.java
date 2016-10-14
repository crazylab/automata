package main.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InputFile {
    private String filePath;

    public InputFile(String filePath) {
        this.filePath = filePath;
    }

    public String read() throws IOException {
        File file = new File(this.filePath);
        int fileSize = (int) file.length();
        char[] s = new char[fileSize];
        FileReader fr = new FileReader(file);
        fr.read(s);
        return new String(s);
    }
}
