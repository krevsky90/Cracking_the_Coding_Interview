package java_learning.input_output_files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java_learning.input_output_files.Constants.*;

public class ScannerExample {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File(PATH + TEXT_FILE))) {
            // Read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // Read the next line
                System.out.println(line); // Print the line to the console
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
