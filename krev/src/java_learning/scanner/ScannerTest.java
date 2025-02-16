package java_learning.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ScannerTest {
    /**
     * nextByte()
     * nextShort()
     * nextInt()
     * nextLong()
     * nextFloat()
     * nextDouble()
     * nextBoolean()
     * BUT there is no method nextChar!
     *
     * next()
     * nextLine()
     *
     * hasNext()
     * hasNextLine()
     * hasNextInt() etc
     * @param args
     */
    public static void main(String[] args) {
        readFile();
    }

    public static void readFile() {
        Map<String, Integer> freqMap = new HashMap<>();
        String filePath = "./krev/src/java_learning.scanner/text.txt";
        try (Scanner sc = new Scanner(new FileReader(new File(filePath)))) {
            sc.useDelimiter("\\W+");    //split by non-letter-or-digit symbols
            while (sc.hasNext()) {
                String word = sc.next();
                System.out.println(word);
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }

        freqMap.entrySet().stream().sorted((a,b) -> b.getValue() - a.getValue()).forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
        } catch (FileNotFoundException ex) {
            System.out.println("File " + filePath  + " is not found");
        }

    }

    public static void testNextDouble() {
        Scanner scanner = new Scanner("weew 4.0 rr");
        System.out.println(scanner.nextDouble());   //throws java.util.InputMismatchException
        Scanner scanner2 = new Scanner("4.0 rr");
        System.out.println(scanner.nextDouble());   //returns 4.0

    }
    public static void testNextLine() {
        Scanner scanner = new Scanner("line1 line11\nline2 line22\nline3 line33");
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

    }

    public static void testNext() {
        Scanner scanner = new Scanner("line1 line11\nline2 line22\nline3 line33");
        System.out.println(scanner.next()); //returns only one word

        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    public static void testInputLines() {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        System.out.println(s1 + " " + s2);

    }

    public static void testInputSum() {
        Scanner scanner = new Scanner(System.in);
        int i1 = scanner.nextInt();
        int i2 = scanner.nextInt();
        System.out.println(i1 + i2);
    }
}
