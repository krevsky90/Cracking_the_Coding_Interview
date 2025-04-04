package java_learning.input_output_files;

import java.io.*;

import static java_learning.input_output_files.WriteFileExample.FILE_PATH;

public class ReadFileExample {
    public static void main(String[] args) {
        readByFileReader();
        readByFileInputStream();
    }

    //to read by one char - connect to file - slow
    public static void readByFileReader() {
        try (FileReader fr = new FileReader(FILE_PATH)) {
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
//        byt[] buffer = new char[1024];
//        String data = new String(buffer, 0, -1);
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
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
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
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
