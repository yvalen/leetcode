package leetcode.binarysearch;

/*
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., 
 * there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, 
 * return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 * For example, given the following image:
 * [
 * 	"0010",
 * 	"0110",
 * 	"0100"
 * ]
 * and x = 0, y = 2, return 6.
 * 
 *  Company: Google
 *  Difficulty: hard
 */
public class SmallestRectangleEnclosingBlackPixels {

	// DFS
	// Search starting from the given pixel. Since all the black pixels are connected, 
	// DFS or BFS will visit all of them starting from the given black pixel
	// Time complexity: O(mn) worst case, Space complexity: O(mn) worst case
	private int top, bottom, left, right;
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
	public int minArea_dfs(char[][] image, int x, int y) {
		if (image == null || image.length == 0) return 0;
        top = bottom = x;
        left = right = y;
        dfs(image, x, y);
		return (bottom - top + 1) * (right - left + 1);
    }
	
	private void dfs(char[][] image, int x, int y) {
		if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] == '0') { // return when cell is white since all black cells are connected
			return;
		}
		
		image[x][y] = '0'; // mark the cell as visited
		top = Math.min(top, x);
		bottom = Math.max(bottom, x);
		left = Math.min(left, y);
		right = Math.max(right, y);
		System.out.println("top=" + top + " bottom=" + bottom + " left=" + left + " right=" + right);
		for (int[] dir : DIRECTIONS) {
			dfs(image, x + dir[0], y + dir[1]);
		}
	}
	
	
	// Binary search
	// Time complexity: O(mlogn + nlogm), Space complexity: O(1)
	public int minArea_binarySearch(char[][] image, int x, int y) {
		if (image == null || image.length == 0) return 0;
		int m = image.length, n = image[0].length;
		int left = searchColumns(image, 0, y, 0, m, true); 			// search col[0...y], find first column contain 1,
		int right = searchColumns(image, y+1, n, 0, m, false);      // search col[y+1, col], find first column contain all 0  
		int top = searchRows(image, 0, x, left, right, true);       // search row [0...x], find first row contain 1,
		int bottom = searchRows(image, x+1, m, left, right, false); // search row[x+1, row], find first row contains all 0
		return (bottom - top) * (right - left);
    }
	
	private int searchColumns(char[][] image, int lo, int hi, int top, int bottom, boolean whiteToBlack) {
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			int k = top;
			while (k < bottom && image[k][mid] == '0') k++;
			if ((k < bottom) == whiteToBlack) { // k < bottom means column mid has a black pixel
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	private int searchRows(char[][] image, int lo, int hi, int left, int right, boolean whiteToBlack) {
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			int k = left;
			while (k < right && image[mid][k] == '0') k++;
			if ((k < right) == whiteToBlack) { // k < bottom means row mid has a black pixel
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	public static void main(String[] args) {
		SmallestRectangleEnclosingBlackPixels sbp = new SmallestRectangleEnclosingBlackPixels();
		char[][] image = {
				{'0', '0', '1', '0'},
				{'0', '1', '1', '0'},
				{'0', '1', '0', '0'}
		};
		int x = 0, y = 2;
		System.out.println(sbp.minArea_dfs(image, x, y));
	}
}
