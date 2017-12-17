package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 401
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 * Example:
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 * - The order of output does not matter.
 * - The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * - The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 17(LetterCombinationOfPhoneNumber), 191(NumberOfOneBits)
 */
public class BinaryWatch {
    private static final int[] HOURS = { 1, 2, 4, 8 };
    private static final int[] MINUTES = { 1, 2, 4, 8, 16, 32 };

    public List<String> readBinaryWatch_backtrack(int num) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            List<Integer> hours = generateHours(i);
            List<Integer> minutes = generateMinutes(num - i);
            StringBuilder sb = new StringBuilder();
            for (int hour : hours) {
                if (hour >= 12)
                    continue; // hour is in the range of 0-11
                int len1 = sb.length();
                sb.append(hour).append(":");
                for (int minute : minutes) {
                    if (minute >= 60)
                        continue; // minute is in the range of 0-59
                    int len2 = sb.length();
                    if (minute < 10) {
                        sb.append(0);
                    }
                    sb.append(minute);
                    result.add(sb.toString());
                    sb.setLength(len2);
                }
                sb.setLength(len1);
            }
        }
        return result;
    }

    private List<Integer> generateHours(int count) {
        List<Integer> hours = new ArrayList<>();
        generateDigits(HOURS, count, 0, hours, 0);
        return hours;
    }

    private List<Integer> generateMinutes(int count) {
        List<Integer> minutes = new ArrayList<>();
        generateDigits(MINUTES, count, 0, minutes, 0);
        return minutes;
    }

    private void generateDigits(int[] nums, int count, int start, List<Integer> digits, int sum) {
        if (count == 0) {
            digits.add(sum);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            generateDigits(nums, count - 1, i + 1, digits, sum + nums[i]);
        }
    }

    //
    // Bit Manipulation
    // go through the possible times and collect those with the correct number
    // of one-bits.
    public List<String> readBinaryWatch_bit(int num) {
        List<String> result = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                // hours occupy 4 bits, minutes occupy 6 bits
                // shift hours by 6 bits to differentiate hours and minutes by
                // bit,
                // we could also use: h*64+m, h+m*16, (h<<6)+m
                if ((Integer.bitCount(m) + Integer.bitCount(h)) == num) {
                    result.add(String.format("%d:%02d", h, m)); // %02d means an
                                                                // integer, left
                                                                // padded with
                                                                // zeros up to 2
                                                                // digits.

                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        BinaryWatch bw = new BinaryWatch();

        int count = 2, num = 2;
        // System.out.println(bw.readBinaryWatch(num));
        System.out.println(bw.generateHours(count));
        System.out.println(bw.generateMinutes(count));
    }
}
