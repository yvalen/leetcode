package leetcode.string;

/*
 * LEETCODE 8
 * Implement atoi to convert a string to an integer. Hint: Carefully consider all possible input cases. If you want a challenge, 
 * please do not see below and ask yourself what are the possible input cases.
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front. 
 * 
 * Requirements for atoi:
 * - The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, 
 * takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * - The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * - If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it 
 * contains only whitespace characters, no conversion is performed.
 * - If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or 
 * INT_MIN (-2147483648) is returned.
 * 
 * Company: Microsoft, Amazon, Bloomberg, Uber
 * Difficulty: medium
 * Similar Questions: 
 */
public class StringToInteger {
	private static final int MAX_DIV = Integer.MAX_VALUE / 10; 
    
    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) return 0;
        
        int i = 0, n = str.length();
        while (i < n && Character.isWhitespace(str.charAt(i))) i++;
        
        boolean isNegative = false;
        if (i < n && str.charAt(i) == '+') i++;
        else if (i < n && str.charAt(i) == '-') {
            i++;
            isNegative = true;
        }
        
        int num = 0;
        while (i < n) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) break;
            if (num > MAX_DIV || (num == MAX_DIV && c >= '8')) return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            num = num * 10 + Character.getNumericValue(c);
            i++;
        }
        
        return num * (isNegative ? -1 : 1);
    }
    
    public static void main(String[] args) {
    	StringToInteger sti = new StringToInteger();
    	//String str = "1";
    	//String str = "123abc";
    	String str = "      -11919730356x";
    	System.out.println(sti.myAtoi(str));
    }
}
