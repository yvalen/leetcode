package leetcode.matrix;

/*
 * LEETCODE 531
 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
 * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
 * A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
 * Example:
 * Input:
 * [['W', 'W', 'B'],
 * 	['W', 'B', 'W'],
 * 	['B', 'W', 'W']
 * ]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * Note: The range of width and height of the input 2D array is [1,500].
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 73(SetMatrixZeroes)
 */
public class LonelyPixel {
    // Time complexity: O(m^2n^2)
    public int findLonelyPixel_bruteforce(char[][] picture) {
        if (picture == null || picture.length == 0)
            return 0;

        int m = picture.length, n = picture[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B' && isLonelyPixel(picture, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isLonelyPixel(char[][] picture, int row, int col) {
        for (int i = 0; i < picture[0].length; i++) {
            if (i != col && picture[row][i] == 'B')
                return false;
        }
        for (int i = 0; i < picture.length; i++) {
            if (i != row && picture[i][col] == 'B')
                return false;
        }
        return true;
    }

    // Time complexity: O(mn) Space complexity: O(m+n)
    public int findLonelyPixel_withArray(char[][] picture) {
        if (picture == null || picture.length == 0)
            return 0;

        int m = picture.length, n = picture[0].length;
        int[] rowCount = new int[m], colCount = new int[n]; // stores the count
                                                            // of black pixels
                                                            // for each row and
                                                            // column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1)
                    count++;
            }
        }

        return count;
    }

    // use the first row and column to record the number of black pixels
    // W + 1 = X --> one item in the row/column
    // B + 1 = C --> one item in the row/column, and the first row is the black
    // pixel
    // W + 2 = Y --> two items in the row/column
    // W - 1 = V --> this prevents wrap-around past W if there are more than 255
    // black pixels in a row/column
    // Time complexity: O(mn) Space complexity: O(1)
    public int findLonelyPixel(char[][] picture) {
        if (picture == null || picture.length == 0)
            return 0;

        int m = picture.length, n = picture[0].length;
        int firstRowCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    if (i == 0)
                        firstRowCount++;
                    else if (picture[i][0] < 'Y' || picture[i][0] != 'V')
                        picture[i][0]++;
                    if (picture[0][j] < 'Y' || picture[0][j] != 'V')
                        picture[0][j]++; // picture[0][0] stores the state of
                                         // the first column
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] < 'W' && (picture[0][j] == 'C' || picture[0][j] == 'X')) { // need
                                                                                             // to
                                                                                             // check
                                                                                             // for
                                                                                             // <
                                                                                             // 'W'
                                                                                             // so
                                                                                             // that
                                                                                             // the
                                                                                             // first
                                                                                             // row
                                                                                             // can
                                                                                             // be
                                                                                             // handled
                                                                                             // properly
                    if (i == 0)
                        count += (firstRowCount == 1 ? 1 : 0);
                    else if (picture[i][0] == 'C' || picture[i][0] == 'X')
                        count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        LonelyPixel lp = new LonelyPixel();
        char[][] picture = { { 'W', 'W', 'B' }, { 'W', 'B', 'W' }, { 'B', 'W', 'W' } };

        System.out.println(lp.findLonelyPixel(picture));
    }
}
