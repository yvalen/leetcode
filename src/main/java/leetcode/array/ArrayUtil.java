package leetcode.array;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class ArrayUtil {

    public static void printArray(int[] nums) {
        IntStream.of(nums).forEach(System.out::println);
    }

    public static void printArray(int[] nums, String separator) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(Integer.toString(nums[i]) + separator);
        }
        System.out.println();
    }
    
    public static void printArray(double[] nums, String separator) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(Double.toString(nums[i]) + separator);
        }
        System.out.println();
    }

    public static void printArray(boolean[] nums, String separator) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(Boolean.toString(nums[i]) + separator);
        }
        System.out.println();
    }

    public static void printTwoDCharArray(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(Character.toString(board[i][j]) + ' ');
            }
            System.out.println();
        }
    }
    
    public static void printTwoDIntArray(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(Integer.toString(board[i][j]) + ' ');
            }
            System.out.println();
        }
    }
}
