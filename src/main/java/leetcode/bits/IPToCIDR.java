package leetcode.bits;

import java.awt.font.NumericShaper.Range;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

import sun.net.www.content.audio.x_aiff;

/*
 * LEETCODE 751
 * Given a start IP address ip and a number of ips we need to cover n, return a 
 * representation of the range as a list (of smallest possible length) of CIDR 
 * blocks. A CIDR block is a string consisting of an IP, followed by a slash, and 
 * then the prefix length. For example: "123.45.67.89/20". That prefix length "20" 
 * represents the number of common prefix bits in the specified range.
 * Example 1:
 * Input: ip = "255.0.0.7", n = 10
 * Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 * Explanation: The initial ip address, when converted to binary, looks like this 
 * (spaces added for clarity): 255.0.0.7 -> 11111111 00000000 00000000 00000111
 * The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits 
 * to the given address, i.e. just this one address. The address "255.0.0.8/29" 
 * specifies all addresses with a common prefix of 29 bits to the given address:
 * 255.0.0.8 -> 11111111 00000000 00000000 00001000
 * Addresses with common prefix of 29 bits are:
 * 11111111 00000000 00000000 00001000
 * 11111111 00000000 00000000 00001001
 * 11111111 00000000 00000000 00001010
 * 11111111 00000000 00000000 00001011
 * 11111111 00000000 00000000 00001100
 * 11111111 00000000 00000000 00001101
 * 11111111 00000000 00000000 00001110
 * 11111111 00000000 00000000 00001111
 * The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits 
 * to the given address, i.e. just 11111111 00000000 00000000 00010000.
 * In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7.
 * There were other representations, such as:
 * ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
 * but our answer was the shortest possible.
 * Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
 * because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100 that 
 * are outside the specified range.
 * Note:
 * - ip will be a valid IPv4 address.
 * - Every implied address ip + x (for x < n) will be a valid IPv4 address.
 * - n will be an integer in the range [1, 1000].
 * 
 * Company: Airbnb
 * Difficulty: easy
 * Similar Questions: 93(RestoreIPAddress), 468
 * 
 * http://www.cnblogs.com/grandyang/p/8440087.html
 */
public class IPToCIDR {
    // Maximize the number of trailing zeros, which will give us the largest block of IP 
    // addresses possible and optimal answer. We can increase the number of trailing zeros 
    // by creating a CIDR block that uses all the current trailing zeros available. 
    public static List<String> ipToCIDR(String ip, int n) {
        long ipLong = 0;
        String[] strs = ip.split("\\.");
        for (String str : strs) {
            ipLong = ipLong * 256 + Integer.parseInt(str);
        }
        
        List<String> result = new ArrayList<>();
        while (n > 0) {
            // find the rightmost set bit
            long step = ipLong & -ipLong;
            while (step > n) step /= 2;
            result.add(longToIP(ipLong, (int)step));
            ipLong += step;
            n -= step;
        }
        
        return result;
    }
    
    private static String longToIP(long val, int step) {
        int[] ipBlocks = new int[4];
        for (int i = 0; i < 4; i++) {
            ipBlocks[i] = (int) val & 255;
            val >>= 8;
        }
        int len = 33;
        while (step > 0) {
            len--;
            step /= 2;
        }
        
        return ipBlocks[3] + "." + ipBlocks[2] + "." + ipBlocks[1] + "." + ipBlocks[0] + "/" + len;
    }
    
    public static List<String> ipToCIDR_withMask(String ip, int range) {
        long start = ipToLong(ip);
        long end = start + range - 1;
        List<String> result = new ArrayList<>();
        while (start <= end) {
            // get the location of the last set bit
            long lastSetBit = start & -start;
            // Math.log(double val) returns the natural logarithm (base e) of a double value 
            // Math.log(val) / Math.log(2) calculates the log base 2 for val
            int mask = 32 - (int) (Math.log(lastSetBit) / Math.log(2));
            int rangeMask = 32 - (int) (Math.log(end-start+1) / Math.log(2));
            
            // take the max of mask and rangemask
            // if rangeMask > mask, it means the number of IPs from start to end 
            // is smaller than the mask range
            // if mask > rangeMask, it means number of IPs is larger than mask range
            // we can take all IPs in the maskRange
            mask = Math.max(mask, rangeMask);
    
            result.add(longToIP(start) + "/" + mask);
            
            // there are 2^(32-mask) IPs included 
            start += Math.pow(2, 32-mask);
        }
        return result;
    }
    
    private static long ipToLong(String ipStr) {
        long[] ipBlocks = new long[4];
        String[] strs = ipStr.split("\\.");
        for (int i = 0; i < 4; i++) {
            ipBlocks[i] = Long.parseLong(strs[i]);
        }
        return (ipBlocks[0] << 24) + (ipBlocks[1] << 16) + (ipBlocks[2] << 8) + ipBlocks[3];
     }
    
    private static String longToIP(long ipLong) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipLong >> 24).append(".");
        sb.append((ipLong & 0x00FFFFFF)  >> 16).append(".");
        sb.append((ipLong & 0x0000FFFF)  >> 8).append(".");
        sb.append(ipLong & 0x000000FF);
        return sb.toString();
    }

    public static void main(String[] args) {
        String ip = "255.0.0.7";
        int n = 10;
        System.out.println(ipToCIDR_withMask(ip, n));
    }
}
