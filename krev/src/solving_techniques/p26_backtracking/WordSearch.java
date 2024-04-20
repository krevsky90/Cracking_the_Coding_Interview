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

        System.out.println(new WordSearch().exist(board, wordTrue));   //true
        System.out.println(new WordSearch().exist(board, wordFalse));   //false

        char[][] board2 = {{'A','B'}};
        System.out.println(new WordSearch().exist(board2, "AB"));   //true
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visit = new boolean[m][n];  //to prevent returning to the symbol of the board where we have already been

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (validation(board, i, j, word, 0, visit)) {
                    return true;
                }
            }
        }

        return false;
    }



    /**
     * SOLUTION #1
     * info:
     * https://leetcode.com/problems/word-search/solutions/4965080/100-beats-fully-explained-code-with-comments-2-approaches/
     *
     * BEATS = 70%
     */
    private boolean validation(char[][] board, int i, int j, String word, int idx, boolean[][] visit) {
        //base case:
        if (word.length() == idx) return true;

        //stop conditions:
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(idx) != board[i][j]) return false;

        if (visit[i][j]) return false;

        visit[i][j] = true;

        //TIPS: do NOT use conditions in the middle of the logic. like i > 0. because it complicates the solution and we can not reach base condition if it is idx == word.length() !
//        boolean way1 = i > 0 ? validation(board, i - 1, j, word, idx + 1, visit) : false;
        boolean way1 = validation(board, i - 1, j, word, idx + 1, visit);
        if (way1) return true;

//      idea! we do not need to check another ways if we have already found any way!

        boolean way2 = validation(board, i, j - 1, word, idx + 1, visit);
        if (way2) return true;

        boolean way3 = validation(board, i + 1, j, word, idx + 1, visit);
        if (way3) return true;

        boolean way4 = validation(board, i, j + 1, word, idx + 1, visit);
        if (way4) return true;

        visit[i][j] = false;

        return false;
    }

    /**
     * KREVSKY SOLUTION
     * time to solve ~ 40 mins
     *
     * time ~ O(m*n * 4^word.length)
     * space ~ O(n*m + word.length)
     *
     * many attempts:
     * - did not use visit array
     * - incorrectly set base condition
     * - incorrectly set the sequence of stop and base conditions
     *
     * BEATS = 90%, BUT inconvenience with base case and stop conditions
     */
    private boolean validation2(char[][] board, int i, int j, String word, int idx, boolean[][] visit) {
        //stop conditions:
        if (visit[i][j]) return false;
        if (idx < word.length() && word.charAt(idx) != board[i][j]) return false;

        //base case:
        //NOTE: we can't write word.length() == idx. since it will not work in case of {{'a'}}, for example
        //also it affect case when we return to the cell where we have already been. and check if idx = word.length. it returns true, but in fact it should be false
        if (word.length() - 1 == idx) return true;

        visit[i][j] = true;
        boolean way1 = i > 0 ? validation2(board, i - 1, j, word, idx + 1, visit) : false;
        if (way1) return true;

        boolean way2 = j > 0 ? validation2(board, i, j - 1, word, idx + 1, visit) : false;
        if (way2) return true;

        boolean way3 = i < board.length - 1 ? validation2(board, i + 1, j, word, idx + 1, visit) : false;
        if (way3) return true;

        boolean way4 = j < board[0].length - 1 ? validation2(board, i, j + 1, word, idx + 1, visit) : false;
        if (way4) return true;

        visit[i][j] = false;    //we can do it even if result = true. in this case no matter what value is in visit[][]

        return false;
    }


}
