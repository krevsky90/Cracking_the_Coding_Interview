package java_learning.input_output_files;

import java.io.*;

public class WriteFileExample {
    public static final String FILE_PATH = "./krev/src/java_learning/input_output_streams/testdata/test_write_file.txt";

    public static void main(String[] args) {
        writeByFileWriter();
        writeByBufferedWriter();
    }

    //to write by one char - connect to file - slow
    public static void writeByFileWriter() {
        boolean append = true; //if false - Writer recreates file, if true - just appends new text
        try (FileWriter fr = new FileWriter(FILE_PATH, append)) {
            fr.write("FileWriter: test write text\n");
        } catch (IOException e) {
            //NOTE: FileWriter can throw exception is file name is the same as name of existing directory (i.e. there is directory with name 'test_write_file.txt'
            System.out.println("can't create file");
            System.out.println(e.getLocalizedMessage());
        }
    }

    //writes by array of chars => faster! => always use BufferedWriter and wrap Writer stream!
    public static void writeByBufferedWriter() {
        boolean append = false; //if false - Writer recreates file, if true - just appends new text
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, append))) {
            bw.write("BufferedWriter: test write text1\n");
            bw.write("BufferedWriter: test write text2\n");
            bw.write("BufferedWriter: test write text3\n");
        } catch (IOException e) {
            //NOTE: FileWriter can throw exception is file name is the same as name of existing directory (i.e. there is directory with name 'test_write_file.txt'
            System.out.println("can't create file");
            System.out.println(e.getLocalizedMessage());
        }
    }
}
