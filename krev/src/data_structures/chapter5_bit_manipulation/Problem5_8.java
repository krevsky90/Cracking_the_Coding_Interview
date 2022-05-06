package data_structures.chapter5_bit_manipulation;

/**
 * p.128
 * 5.8 Draw Line:
 * A monochrome screen is stored as a single array of bytes, allowing eight consecutive
 * pixels to be stored in one byte. The screen has width w, where w is divisible by 8 (that is, no byte will
 * be split across rows). The height of the screen, of course, can be derived from the length of the array
 * and the width. Implement a function that draws a horizontal line from (x1, y) to (x2, y) .
 * The method signature should look something like:
 * drawline(byte[] screen, int width, int x1, int x2, int y)
 * Hints: #366, #381, #384, #391
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem5_8 {
    public static void main(String[] args) {
        int width = 40;
        int x1 = 11;
        int x2 = 13;
        int y = 1;
        System.out.println("width = " + width + ", x1 = " + x1 + ", x2 = " + x2 + ", y = " + y);

        //source:
        //[00000000, 00000000, 00000000, 00000000, 00000000,
        // 00000000, 00011001, 10000000, 00000000, 00000000]
        //
        //target:
        // [00000000, 00000000, 00000000, 00000000, 00000000,
        //  00000000, 00011101, 10000000, 00000000, 00000000]

        byte[] screen = new byte[10];
        screen[0] = 0;
        screen[1] = 0;
        screen[2] = 0;
        screen[3] = 0;
        screen[4] = 0;

        screen[5] = 0;
        screen[6] = (byte) 0x19;
        screen[7] = (byte) 0xA0;
//        screen[6] = 0;
//        screen[7] = 0;
        screen[8] = 0;
        screen[9] = 0;
        System.out.println("--------screen -----------------------");
        printScreenBytes(screen);

        drawline(screen, width, x1, x2, y);

        System.out.println("--------screen after-----------------------");
        printScreenBytes(screen);
    }

    /**
     * KREVSKY SOLUTION: idea is like original
     */
    //assume that x, y starts from 0!
    public static void drawlineKrevsky(byte[] screen, int width, int x1, int x2, int y) {
        if (width % 8 != 0) return;

        int k1 = (width * y + x1) / 8;    //since numeration of arrays starts from 0
        int k2 = (width * y + x2) / 8;
        if (k1 < 0 || k1 >= screen.length || k2 < 0 || k2 >= screen.length) return;

        int d1 = 8 - x1 % 8;    // amount of rightmost bits of k1-th element that should be set = '1'
        int d2 = x2 % 8;        //amount of leftmost bits of k2-th element that should be set = '1'

        //NOTE: if d1 == 8 then mask1 also works properly and we fill all bits of k1-th byte as 11111111 = 255
        int mask1 = (1 << d1) - 1;    //like 00001111
        System.out.println("mask1 = " + formatByte(mask1));
        int mask2 = -1 << (8 - (d2 + 1));        //like 11110000, d2 + 1 because numeration starts from 0.
        System.out.println("mask2 = " + formatByte(mask2));

        //todo: не додумался про то, что k1 = k2 это отдельный кейс
        if (k1 == k2) {
            screen[k1] |= mask1 & mask2;
        } else {
            screen[k1] |= mask1;
            screen[k2] |= mask2;
        }

        // set all bytes from k1+1 till k2-1 equals -1
        for (int i = k1 + 1; i < k2; i++) {
            //todo: не додумался сам, как сеттить 11111111. в Java тип byte не бывает unsigned, поэтому приходится извращаться с приведением 0xFF
            screen[i] = (byte) 0xFF;    //i.e. 11111111
        }
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static void drawline(byte[] screen, int width, int x1, int x2, int y) {
        int start_offset = x1 % 8;
        int first_full_byte = x1 / 8;
        if (start_offset != 0) {
            first_full_byte++;
        }

        int end_offset = x2 % 8;
        int last_full_byte = x2 / 8;
        if (end_offset != 7) {
            last_full_byte--;
        }

        //set full bytes
        for (int b = first_full_byte; b <= last_full_byte; b++) {
            screen[(width / 8 * y + b)] = (byte) 0xFF;
        }

        //create masks for start and end of line
        byte start_mask = (byte) (0xFF >> start_offset);
        byte end_mask = (byte) ~(0xFF >> (end_offset + 1));

        //set start and end of line
        if (x1 / 8 == x2 / 8) {
            byte mask = (byte) (start_mask & end_mask);
            screen[(width * y + x1) / 8] |= mask;
        } else {
            if (start_offset != 0) {
                int byte_number = width / 8 * y + first_full_byte - 1;
                screen[byte_number] |= start_mask;
            }
            if (end_offset != 7) {
                int byte_number = width / 8 * y + last_full_byte - 1;
                screen[byte_number] |= end_mask;
            }
        }
    }

    //just for debug
    private static void printScreenBytes(byte[] screen) {
        for (int i = 0; i < screen.length; i++) {
            System.out.print(" " + formatByte(screen[i]));
        }
        System.out.println("");
    }

    private static String formatByte(int t) {
        String s = "" + Integer.toBinaryString(t);
        while (s.length() < 8) {
            s = "0" + s;
        }
        if (s.length() > 8) s = s.substring(24);
        return s;
    }
}
