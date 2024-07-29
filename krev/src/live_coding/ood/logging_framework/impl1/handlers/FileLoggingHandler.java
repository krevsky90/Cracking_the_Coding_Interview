package live_coding.ood.logging_framework.impl1.handlers;

import live_coding.ood.logging_framework.impl1.LogMessage;

import java.io.FileWriter;
import java.io.IOException;

public class FileLoggingHandler implements ILoggingHandler {
    private final String filePath;

    public FileLoggingHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(LogMessage logMessage) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(logMessage.toString() + "\n");
//            System.out.println("PRINT TO FILE: " + message);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
