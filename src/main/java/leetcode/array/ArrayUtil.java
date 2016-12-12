package leetcode.array;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class ArrayUtil {

	public static void printArray(int[] nums) {
		IntStream.of(nums).forEach(System.out::println);
	}
}
