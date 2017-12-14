package leetcode.math;

/*
 * LEETCODE 273
 * Convert a non-negative integer to its English words representation. Given input is guaranteed to be less than 2^31 - 1 (2,147,483,647).
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Hints:
 * - Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 * - Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
 * - There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
 * 
 * Company: Microsoft, Facebook
 * Difficulty: Hard
 * Similar Questions: 12(Integer to Roman)
 */
public class IntegerToEnglishWords {
	private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", 
			"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

	public String numberToWords(int num) {
        if (num == 0) return "Zero";
		
        String result = "";
        int i = 0;
        while (num > 0) {
        	// starting from the right, processing three digits at a time
        	if (num % 1000 != 0) { // need to handle middle is zero
        		result = helper(num % 1000) + THOUSANDS[i] + " " +  result; 
        	}
        	num /= 1000;
        	i++;
        }
        
		return result.trim(); // need to remove the empty space at the end
    }
	
	// num is less than 1000, this helper handles space properly especially an additional white space at the end
	private String helper(int num) {
		if (num == 0) return ""; // reurn empty string instead of calling LESS_THAN_20[num] for 0 so that white space is handled properly
		if (num < 20) return LESS_THAN_20[num] + " "; 
		if (num < 100) return TENS[num/10] + " " + helper(num % 10);  // use helper on num%10 instead of LESS_THAN_20[num%10] so that white space is handled properly
		return LESS_THAN_20[num/100] + " Hundred " + helper(num % 100); 
		
	}
	
	public static void main(String[] args) {
		IntegerToEnglishWords itew = new IntegerToEnglishWords();
		//int num = 1234567;
		//int num = 1000010;
		int num = 50868;
		System.out.println(itew.numberToWords(num));
	}
}
