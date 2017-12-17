package leetcode.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
 * a) it                      --> it    (no abbreviation)
 *      1
 * b) d|o|g                   --> d1g
 *               1    1  1
 *      1---5----0----5--8
 * c) i|nternationalizatio|n  --> i18n
 *               1
 *      1---5----0
 * d) l|ocalizatio|n          --> l10n
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique 
 * if no other word from the dictionary has the same abbreviation.
 * If this word (also this word’s abbreviation) is not in the dictionary OR this word and only it’s abbreviation in the dictionary. 
 * We call a word’s abbreviation unique.
 * Example: Given dictionary = [ "deer", "door", "cake", "card" ]
 * isUnique("dear") -> false
 * isUnique("cart") -> true
 * isUnique("cane") -> false
 * isUnique("make") -> true
 * https://leetcode.com/articles/unique-word-abbreviation/
 * 
 * Company: Google
 * Difficulty: medium
 */
public class UniqueWordAbbreviation {
    // private final Map<String, Set<String>> dict;

    private final Map<String, Boolean> abbreDict;
    private final Set<String> dict;

    public UniqueWordAbbreviation(String[] dictionary) {
        /*
         * dict = new HashMap<>(); for (String s : dictionary) { String abbre =
         * getAbbreviation(s); Set<String> wordSet = dict.getOrDefault(abbre,
         * new HashSet<>()); wordSet.add(s); dict.put(abbre, wordSet); }
         * System.out.println(dict);
         */

        dict = new HashSet<>(Arrays.asList(dictionary));
        abbreDict = new HashMap<>();
        for (String s : dict) { // add dictionary to set first to dedup and then
                                // iterate through set
            String abbre = getAbbreviation(s);
            abbreDict.put(abbre, !abbreDict.containsKey(abbre));
        }
        System.out.println(dict);
        System.out.println(abbreDict);
    }

    public boolean isUnique(String word) {
        String abbre = getAbbreviation(word);
        return !abbreDict.containsKey(abbre) || (abbreDict.get(abbre) && dict.contains(word)); // faster
                                                                                               // than
                                                                                               // using
                                                                                               // Map<String,
                                                                                               // Set<String>>
                                                                                               // if
                                                                                               // isUniqie
                                                                                               // called
                                                                                               // a
                                                                                               // lot

        // Set<String> wordSet = dict.get(getAbbreviation(word));
        // return wordSet == null || (wordSet.contains(word) && wordSet.size()
        // == 1);
    }

    private String getAbbreviation(String s) {
        if (s == null || s.length() <= 2)
            return s;

        int n = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0)).append(n - 2).append(s.charAt(n - 1));
        return sb.toString();
    }

    public static void main(String[] args) {
        // String[] dictionary = {"deer", "door", "cake", "card"};
        String[] dictionary = { "a", "a" };
        UniqueWordAbbreviation uwa = new UniqueWordAbbreviation(dictionary);
        // System.out.println("dear:" + uwa.isUnique("dear"));
        // System.out.println("cart:" + uwa.isUnique("cart"));
        // System.out.println("cane:" + uwa.isUnique("cane"));
        // System.out.println("make:" + uwa.isUnique("make"));
        System.out.println("a:" + uwa.isUnique("a"));
    }
}
