package leetcode;

/**
 * https://en.wikipedia.org/wiki/Look-and-say_sequence
 * "Count and Say problem" Write a code to do following:
 * n String to print
 * 0 1
 * 1 1 1
 * 2 2 1
 * 3 1 2 1 1
 * ...
 * 
 * Base case: n = 0 print "1"
 * for n = 1, look at previous string and write number of times a digit is seen and the digit itself. 
 * In this case, digit 1 is seen 1 time in a row... so print "1 1"
 * for n = 2, digit 1 is seen two times in a row, so print "2 1"
 * for n = 3, digit 2 is seen 1 time and then digit 1 is seen 1 so print "1 2 1 1"
 * for n = 4 you will print "1 1 1 2 2 1"
 * 
 * Consider the numbers as integers for simplicity. 
 * e.g. if previous string is "10 1" then the next will be "1 10 1 1" and the next one will be "1 1 1 10 2 1"
 */
public class CountAndSay {

	public String countAndSay(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Invalid n " + n);
		}
		if (n == 0) {
			return "1";
		}
		
		String result = "1";
		for (int i = 1; i < n; i++) {
			int count = 1;
			StringBuilder sb = new StringBuilder();
			for (int j = 1; j < result.length(); j++) {
				if (result.charAt(j) == result.charAt(j-1)) {
					count++;
				}
				else {
					sb.append(count).append(result.charAt(j-1));
					count = 1;
				}
			}
			sb.append(count).append(result.charAt(result.length() - 1));
			result = sb.toString();
		}
		
		return result;
    }
}
