package data_structures.chapter5_bit_manipulation;

public class CommonBitTasks {
    /**
     * ВНИМАНИЕ!!!
     * биты отсчитываются с НУЛЯ!
     */
    public static void main(String[] args) {
        int num = 200;
        int i = 2;
        System.out.println("num = " + Integer.toBinaryString(num) + ", i = " + i);
        int result = updateBit(num, 3, false);
        System.out.println("result = " + result);
    }

    /**
     * получить i-й бит
     */
    public static int getBit(int num, int i) {
        int mask = 1 << i;   //should be like 000000 1 0000
        System.out.println("mask = " + Integer.toBinaryString(mask));
        int appliedMask = num & mask;
        System.out.println("appliedMask = " + Integer.toBinaryString(appliedMask));
        int result = appliedMask > 0 ? 1 : 0;
        return result;
    }

    /**
     * установить i-й бит равным 1
     */
    public static int setBit(int num, int i) {
        int mask = 1 << i;   //should be like 000000 1 0000
        int result = num | mask;
        System.out.println("resultBinary = " + Integer.toBinaryString(result));
        return result;
    }

    /**
     * очистить i-й бит
     */
    public static int clearBit(int num, int i) {
        int mask = ~(1 << i);   //should be like 11111111 0 111
        System.out.println("mask = " + Integer.toBinaryString(mask));
        int result = num & mask;
        return result;
    }

    /**
     * очистить все биты от старшего до i-го до 0-го (все включительно)
     */
    public static int clearBitsIthrough0(int num, int i) {
        int mask = (-1 << (i+1));    //should be like 11111111 0000
        System.out.println("mask = " + Integer.toBinaryString(mask));
        int result = num & mask;
        return result;
    }

    /**
     * очистить все биты от старшего до i-го (включительно)
     */
    public static int clearBitsMSBthroughI(int num, int i) {
        System.out.println(Integer.toBinaryString(1 << i));
        int mask = (1 << i)  - 1;   //should be like 00000000 1111
        System.out.println("mask = " + Integer.toBinaryString(mask));
        int result = num & mask;
        return result;
    }

    /**
     * установить i-й бит равным v
     */
    public static int updateBit(int num, int i, boolean bitIs1) {
        int value = bitIs1 ? 1 : 0;
        // 1) clear i-th bit:
        int mask = ~ (1 << i);  //should be like 11111111 0 111
        int result = (num & mask);
        System.out.println("num with cleared i-th bit = " + Integer.toBinaryString(result));
        // 2) prepare value to be set:
        int valueToSet = value << i;
        // 3) set value to i-th bit (set = use '|' operation)
        result = result | valueToSet;
        System.out.println("resultBinary = " + Integer.toBinaryString(result));
        return result;
    }
}