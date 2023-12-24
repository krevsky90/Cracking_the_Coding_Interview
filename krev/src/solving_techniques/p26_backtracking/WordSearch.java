package solving_techniques.p26_backtracking;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63d4f8d230ea8276b79362b9
 *
 * Given an m x n grid of characters board and a string word, return true if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Example 1:
 * Input: word="ABCCED", board:
 *     { 'A', 'B', 'C', 'E' },
 *     { 'S', 'F', 'C', 'S' },
 *     { 'A', 'D', 'E', 'E' }
 * Output: true Explanation: The word exists in the board
 */
public class WordSearch {
    public static void main(String[] args) {
        String wordTrue = "ABCCED";
        String wordFalse = "ABCCES";
        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };

        boolean res1 = check(board, wordTrue);   //true
        System.out.println(res1);

        boolean res2 = check(board, wordFalse);   //false
        System.out.println(res2);
    }

    /**
     * KREVSKY SOLUTION
     * time to solve ~ 15 mins
     * 1 attempt
     * time ~ O(4^word.length)
     */
    public static boolean check(char[][] arr, String s) {
        int rowNum = arr.length;
        int columnNum = arr[0].length;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                if (check(arr, rowNum, columnNum, i, j, s, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(char[][] arr, int rowNum, int columnNum, int i, int j, String s, int idx) {
        if (idx == s.length()) return true;

        if (arr[i][j] != s.charAt(idx)) return false;

        boolean way1 = false;
        if (i > 0) way1 = check(arr, rowNum, columnNum,i-1, j, s, idx+1);

        boolean way2 = false;
        if (j > 0) way2 = check(arr, rowNum, columnNum,i, j-1, s, idx+1);

        boolean way3 = false;
        if (i < rowNum - 1) way3 = check(arr, rowNum, columnNum,i+1, j, s, idx+1);

        boolean way4 = false;
        if (j < columnNum - 1) way4 = check(arr, rowNum, columnNum,i, j+1, s, idx+1);

        return way1 || way2 || way3 || way4;
    }
}
