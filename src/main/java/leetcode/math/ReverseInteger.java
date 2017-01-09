package leetcode.math;

public class ReverseInteger {
	public int reverse(int x) {
		boolean isNegative = false;
		if (x < 0) {
			x *= -1;
			isNegative = true;
		}
		
        int ret = 0;
        while (x > 0) {
        	if (Integer.MAX_VALUE / 10 < ret) {
        		return 0;
        	}
        	ret = ret * 10 + x % 10;
        	x = x / 10;
        }
        
        if (isNegative) {
        	ret *= -1;
        }
		
        return ret;
    }
	
	public int reverse_withLong(int x) {
		long ret = 0;
		while (x != 0) {
			ret = ret * 10 + x % 10;
			x = x / 10;
		}
		
		if (ret < Integer.MIN_VALUE || ret > Integer.MAX_VALUE) {
			return 0;
		}
		
		return (int) ret;
	}
	
	public static void main(String[] args) {
		ReverseInteger r = new ReverseInteger();
		
		int i = r.reverse(1);
		System.out.println(i);
	}
	
}
