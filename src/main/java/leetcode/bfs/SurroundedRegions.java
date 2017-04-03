package leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.array.ArrayUtil;

/*
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region. For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
public class SurroundedRegions {
	class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/*
	 * Find all 'O's on the edge, and do BFS from these 'O's. Keep adding 'O's into the queue in the BFS, 
	 * and mark it as '+'. Since these 'O's are found by doing BFS from the 'O's on the edge, it means 
	 * they are connected to the edge 'O's. so they are the 'O's that will remain as 'O' in the result.
	 */
	public void solve_bfs(char[][] board) {
       if (board == null || board.length == 0 || board[0].length == 0) return;
       
       int m = board.length, n = board[0].length;
       Queue<Point> queue = new LinkedList<>();
       
       // enqueue all O on the edge and replace them with +
       for (int i = 0; i < m; i++) {
    	   if (board[i][0] == 'O') {
    		   queue.offer(new Point(i, 0));
    		   board[i][0] = '+';
    	   }
    	   if (board[i][n-1] == 'O') {
    		   queue.offer(new Point(i, n-1));
    		   board[i][n-1] = '+';
    	   }
       }
       
       for (int j = 0; j < n; j++) {
    	   if (board[0][j] == 'O') {
    		   queue.offer(new Point(0, j));
    		   board[0][j] = '+';
    	   }
    	   if (board[m-1][j] == 'O') {
    		   queue.offer(new Point(m-1, j));
    		   board[m-1][j] = '+';
    	   }
       }
       
       System.out.println("before bfs\n");
       ArrayUtil.printTwoDCharArray(board);
       
       // bfs, change all cells that connects to O in edge to +
       while (!queue.isEmpty()) {
    	   Point p = queue.poll();
    	   if (p.x < m-1 && board[p.x+1][p.y] == 'O') {
    		   queue.offer(new Point(p.x+1, p.y));
    		   board[p.x+1][p.y] = '+';
    	   }
    	   else if  (p.x > 0 && board[p.x-1][p.y] == 'O') {
    		   queue.offer(new Point(p.x-1, p.y));
    		   board[p.x-1][p.y] = '+';
    	   }
    	   else if (p.y < n-1 && board[p.x][p.y+1] == 'O') {
    		   queue.offer(new Point(p.x, p.y+1));
    		   board[p.x][p.y+1] = '+';
    	   }
    	   else if ((p.y > 0 && board[p.x][p.y-1] == 'O')) {
    		   queue.offer(new Point(p.x, p.y-1));
    		   board[p.x][p.y-1] = '+';
    	   }
       }
       
       System.out.println("after bfs\n");
       ArrayUtil.printTwoDCharArray(board);
       
       // update board, O -> X, + -> O
       for (int i = 0; i < m; i++) {
    	   for (int j = 0; j < n; j++) {
    		   if (board[i][j] == 'O') {
    			   board[i][j] = 'X';
    		   }
    		   else if (board[i][j] == '+') {
    			   board[i][j] = 'O';
    		   }
    	   }
       }
    }
	
	
	public static void main(String[] args) {
		SurroundedRegions s = new SurroundedRegions();
		String[] strAry = {"XOXOXOOOXO","XOOXXXOOOX","OOOOOOOOXX","OOOOOOXOOX","OOXXOXXOOO","XOOXXXOXXO","XOXOOXXOXO","XXOXXOXOOX","OOOOXOXOXO","XXOXXXXOOO"};
		char[][] board = new char[strAry.length][strAry[0].length()];
		for (int i = 0; i < strAry.length; i++) {
			board[i] = strAry[i].toCharArray();
		}
		ArrayUtil.printTwoDCharArray(board);
		s.solve_bfs(board);
		System.out.println("after solve\n");
		ArrayUtil.printTwoDCharArray(board);
				
		
		
	}


}
