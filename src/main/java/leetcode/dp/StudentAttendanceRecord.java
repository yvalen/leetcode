package leetcode.dp;

public class StudentAttendanceRecord {
    /*
     * LEETCODE 551
     * You are given a string representing an attendance record for a student. 
     * The record only contains the following three characters:
     * 'A' : Absent.
     * 'L' : Late.
     * 'P' : Present.
     * A student could be rewarded if his attendance record doesn't contain more 
     * than one 'A' (absent) or more than two continuous 'L' (late). You need to 
     * return whether the student could be rewarded according to his attendance record.
     * Example 1: Input: "PPALLP" Output: True
     * Example 2: Input: "PPALLL" Output: False
     * 
     * Company: Google
     * Difficulty: easy
     * Similar Questions: 552(Student Attendance Record II)
     */
    public boolean checkRecord(String s) {
        
        int numOfAs = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                if (++numOfAs > 1) return false;
            }
            else if (c == 'L') {
                if (i > 1 && s.charAt(i-1) == 'L' && s.charAt(i-2) == 'L') {
                    return false;
                }
            }
        }
        return true;
    }
    
    /*
     * LEETCODE 552
     * Given a positive integer n, return the number of all possible attendance records 
     * with length n, which will be regarded as rewardable. The answer may be very large, 
     * return it after mod 10^9 + 7. A student attendance record is a string that only 
     * contains the following three characters
     * 'A' : Absent.
     * 'L' : Late.
     * 'P' : Present.
     * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) 
     * or more than two continuous 'L' (late).
     * Example 1: Input: n = 2 * Output: 8 
     * Explanation: There are 8 records with length 2 will be regarded as rewardable:
     * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
     * Only "AA" won't be regarded as rewardable owing to more than one absent times. 
     * Note: The value of n won't exceed 100,000.
     * 
     * Company: Google
     * Difficulty: hard
     * Similar Questions: 551(Student Attendance Record I)
     */
    public int checkRecord(int n) {
        
        
        return 0;
    }
    
}