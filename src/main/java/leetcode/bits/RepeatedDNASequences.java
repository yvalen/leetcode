package leetcode.bits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * LEETCODE 187
 * All DNA is composed of a series of nucleotides abbreviated as 
 * A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, 
 * it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) 
 * that occur more than once in a DNA molecule.
 * For example, 
 * given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", 
 * return ["AAAAACCCCC", "CCCCCAAAAA"].
 * 
 * Company: LinkedIn
 * Difficulty: medium
 */
public class RepeatedDNASequences {

    /*
     * Since there are only 4 characters we can encode 10 letters into a 4-byte
     * number. We can use 2 bits to represent each letter 
     * 0 = 00 (bits in binary number system) = 'A' 
     * 1 = 01 (bits in binary number system) = 'C' 
     * 2 = 10 (bits in binary number system) = 'G' 
     * 3 = 11 (bits in binary number system) = 'T' 
     * 10 letters would require 10*2= 20 bits which can be fit into a 32 bits integer. 
     * Example: 
     * A  A  C  C  T  C  C  G  G  T 
     * 00 00 01 01 11 01 01 10 10 11
     * = 00000101110101101011 (binary) = 23915 (decimal)
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10)
            return Collections.emptyList();

        // maintains the mapping of character to 2-bit value
        char[] map = new char[26];
        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        
        List<String> result = new ArrayList<>();
        Set<Integer> firstTimeFound = new HashSet<>();
        Set<Integer> secondTimeFound = new HashSet<>();
        for (int start = 0; start < s.length() - 9; start++) { // need to handle
                                                               // AAAAAAAAAAA
            int value = 0; // integer that represents 10 letters
            for (int end = start; end < start + 10; end++) {
                value <<= 2;
                value |= map[s.charAt(end) - 'A'];
            }
            if (!firstTimeFound.add(value) && secondTimeFound.add(value)) {
                // only add to result list when the value appears the second time
                // otherwise duplicate will be added to result
                result.add(s.substring(start, start + 10)); 
            }
        }
        return result;
    }
    
    public List<String> findRepeatedDnaSequences_substring(String s) {
        Set<String> firstTimeSeen = new HashSet<>();
        Set<String> secondTimeSeen = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length()-9; i++) {
            String seq = s.substring(i, i+10);
            if (!firstTimeSeen.add(seq) && secondTimeSeen.add(seq)) {
                result.add(seq);
            }
        } 
        return result;
    }

}
