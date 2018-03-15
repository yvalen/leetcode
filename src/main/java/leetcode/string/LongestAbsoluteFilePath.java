package leetcode.string;

import java.util.Stack;

/*
 * LEETCODE 388
 * Suppose we abstract our file system by a string in the following manner:
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * dir
 *     subdir1
 *     subdir2
 *        file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory 
 * subdir2 containing a file file.ext. The string 
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" 
 * represents:
 * dir
 *  subdir1
 *    file1.ext
 *    subsubdir1
 *  subdir2
 *    subsubdir2
 *       file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file 
 * file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level 
 * sub-directory subsubdir2 containing a file file2.ext.
 * We are interested in finding the longest (number of characters) absolute path to a file within 
 * our file system. For example, in the second example above, the longest absolute path is 
 * "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
 * Given a string representing the file system in the above format, return the length of the longest 
 * absolute path to file in the abstracted file system. If there is no file in the system, return 0.
 * Note:
 * - The name of a file contains at least a . and an extension.
 * - The name of a directory or sub-directory will not contain a ..
 * Time complexity required: O(n) where n is the size of the input string.
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 * 
 * Companies: Google
 * Difficulty: Medium
 */
public class LongestAbsoluteFilePath {
	
	public static int lengthLongestPath(String input) {
		if (input == null || input.isEmpty()) return 0;
		
		String[] strs = input.split("\\n");
		// stores the length of previous level
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
		int maxLen = 0;
		for (String s : strs) {
			// need to plus one as index is 0 based
			// if no tab, lastIndexOf returns -1, 
			// it will be 0
			int numOfTabs = s.lastIndexOf('\t')+1;
			// level is 1 based as it will be used to compare with stack size
			int level = numOfTabs + 1;
			// go to the previous level
			while (level < stack.size()) stack.pop();
			// plus one here to account for the ending slash for directory
			int currentLen = stack.peek() + s.length() - numOfTabs + 1;
			stack.push(currentLen);
			// only update maxLen if s is file, need to subtract one 
			// to remove the last / appended when calculating currentLen
			if (s.contains(".")) maxLen = Math.max(maxLen, currentLen-1);
		}
        return maxLen;
    }

	public static void main(String[] args) {
	    String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" ;
	    System.out.println(lengthLongestPath(input));
	}
}
