package data_structures.chapter5_bit_manipulation;

public class CommonMasks {
    public static void main(String[] args) {
        int i = 2;
        int mask1 = 1 << i;
        int mask2 = ~(1 << i);
        int mask3 = -1 << i;
        int mask4 = (1 << i) - 1;

        System.out.println("i = " + i);
        System.out.println(Integer.toBinaryString(mask1));  //0...0100
        System.out.println(Integer.toBinaryString(mask2));  //1...1011
        System.out.println(Integer.toBinaryString(mask3));  //1...1100
        System.out.println(Integer.toBinaryString(mask4));  //0...0011

    }
}
