package java_learning.input_output_files;

import java.io.*;

import static java_learning.input_output_files.Constants.PATH;
import static java_learning.input_output_files.Constants.TEXT_FILE;

public class ReadFileExample {
    public static void main(String[] args) {
        readByFileReader();
        readByFileInputStream();
    }

    //to read by one char - connect to file - slow
    public static void readByFileReader() {
        try (FileReader fr = new FileReader(PATH + TEXT_FILE)) {
            int temp = -100;
            while ((temp = fr.read()) != -1) {
                System.out.print((char) temp);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readByFileInputStream() {
        try (FileInputStream fis = new FileInputStream(PATH + TEXT_FILE)) {
            int temp = -100;
            while ((temp = fis.read()) != -1) {
                System.out.print((char) temp);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //reads by array of chars => faster! => always use BufferedReader and wrap Reader stream!
    public static void readByBufferedReader() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH + TEXT_FILE))) {
            String tempLine = null;
            while ((tempLine = br.readLine()) != null) {
                System.out.println(tempLine);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
