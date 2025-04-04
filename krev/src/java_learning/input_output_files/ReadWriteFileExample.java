package java_learning.input_output_files;

import java.io.*;

public class ReadWriteFileExample {
    public static final String FILE_IMAGE_PATH = "./krev/src/java_learning/input_output_streams/testdata/flower.webp";
    public static final String FILE_IMAGE_PATH_CLONE = "./krev/src/java_learning/input_output_streams/testdata/flower_clone.webp";

    public static void main(String[] args) {
//        readWriteByFileInputOutputStream();
        readWriteByBufferedInputOutputStream();
    }

    /**
     * FileOutputStream - to write 1 byte to File (not char, but byte! => can be used not only for text files, but for any types of files. For example, pictures)
     * FileInputStream - to read 1 byte from File
     *
     * example: clone picture
     */
    public static void readWriteByFileInputOutputStream() {
        try (FileOutputStream fos = new FileOutputStream(FILE_IMAGE_PATH_CLONE);
             FileInputStream fis = new FileInputStream(FILE_IMAGE_PATH)) {
            int tmp;
            while ((tmp = fis.read()) != -1) {
                fos.write(tmp);
            }
        } catch (IOException e) {
            //NOTE: FileWriter can throw exception is file name is the same as name of existing directory (i.e. there is directory with name 'test_write_file.txt'
            System.out.println("can't create file");
            System.out.println(e.getLocalizedMessage());
        }
    }

    //optimized by BufferedInputStream and BufferedOutputStream
    public static void readWriteByBufferedInputOutputStream() {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(FILE_IMAGE_PATH_CLONE));
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILE_IMAGE_PATH))) {
            int tmp;
            while ((tmp = bis.read()) != -1) {
                bos.write(tmp);
            }
        } catch (IOException e) {
            //NOTE: FileWriter can throw exception is file name is the same as name of existing directory (i.e. there is directory with name 'test_write_file.txt'
            System.out.println("can't create file");
            System.out.println(e.getLocalizedMessage());
        }
    }

}
