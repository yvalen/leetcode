package leetcode.string;

/*
 * LEETCODE 383
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, 
 * write a function that will return true if the ransom note can be constructed from the magazines; 
 * otherwise return false. Each letter in the magazine string can only be used once in your ransom note.
 * Note: You may assume that both strings contain only lowercase letters.
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 * 
 * Company: Apple
 * Difficulty: easy
 * Similar Questions: 691
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.isEmpty())
            return true;
        if (magazine == null || magazine.isEmpty())
            return false;

        int[] note = new int[26];
        for (char c : ransomNote.toCharArray()) {
            note[c - 'a']++;
        }
        int[] dict = new int[26];
        for (char c : magazine.toCharArray()) {
            dict[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (note[i] > dict[i])
                return false;
        }

        return true;
    }

    public boolean canConstruct_oneArray(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.isEmpty())
            return true;
        if (magazine == null || magazine.isEmpty())
            return false;

        int[] chars = new int[26];
        for (char c : magazine.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--chars[c - 'a'] < 0)
                return false;
        }
        return true;
    }
}
