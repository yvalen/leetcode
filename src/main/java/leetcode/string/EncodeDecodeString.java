package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 271
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent 
 * over the network and is decoded back to the original list of strings.
 * Machine 1 (sender) has the function:
 * 	string encode(vector<string> strs) {
 * 		// ... your code
 * 		return encoded_string;
 * 	}
 * Machine 2 (receiver) has the function:
 * 	vector<string> decode(string s) {
 * 		//... your code
 * 		return strs;
 * 	}
 * So Machine 1 does:
 * 	string encoded_string = encode(strs);
 * and Machine 2 does:
 * 	vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * Implement the encode and decode methods.
 * Note:    
 * - The string may contain any possible characters out of 256 valid ascii characters. Your algorithm 
 * should be generalized enough to work on any possible characters.
 * - Do not use class member/global/static variables to store states. Your encode and decode algorithms 
 * should be stateless.
 * - Do not rely on any library method such as eval or serialize methods. You should implement your own 
 * encode/decode algorithm.
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 38(CountAndSay), 271(EncodeDecodeString)
 */
public class EncodeDecodeString {

    // Use escape
    // Double any hashes inside the strings, then use stand alone hashes
    // (surrounded by spaces) to mark
    // string endings. For example:
    // {"abc", "def"} => "abc # def # "
    // {'abc', '#def'} => "abc # ##def # "
    // {'abc##', 'def'} => "abc#### # def # "
    // For decoding, just do the reverse: First split at stand alone hashes,
    // then undo the doubling in each string.
    public String encode_withEscape(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            // need to append the result of replace call, cannot append str
            // because str is immutable
            sb.append(str.replace("#", "##")).append(" # ");
        }
        return sb.toString();
    }

    public List<String> decode_withEscape(String s) {
        List<String> result = new ArrayList<>();
        // the array returned by split contains each substring of this string
        // that is terminated by another substring
        // that matches the given expression or is terminated by the end of the
        // string
        // If limit is non-positive then the pattern will be applied as many
        // times as possible and the array can have
        // any length. If limit is zero then the pattern will be applied as many
        // times as possible, the array can have
        // any length, and trailing empty strings will be discarded.
        String[] strs = s.split(" # ", -1); // use negative limit to handle
                                            // empty string as input
        for (int i = 0; i < strs.length - 1; i++) { // skip the last entry
                                                    // because it is empty
                                                    // string with negative
                                                    // limit in split
            result.add(strs[i].replace("##", "#"));
        }
        return result;
    }

    //
    // encode as <str len>/<str>
    //
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        if (strs == null)
            return sb.toString();

        for (String str : strs) {
            sb.append(str.length()).append("/").append(str);
        }

        return sb.toString();
    }

    public List<String> decode(String s) {
        if (s == null)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();
        int start = 0;
        while (start < s.length()) {
            // Any '/' in original strs do not conflict since i is moved forward
            // to pick up next size value.
            int slashPos = s.indexOf('/', start);
            int len = Integer.parseInt(s.substring(start, slashPos));
            result.add(s.substring(slashPos + 1, slashPos + 1 + len));
            start = slashPos + len + 1;
        }

        return result;
    }

    public static void main(String[] args) {
        EncodeDecodeString codec = new EncodeDecodeString();
        List<String> strs = Arrays.asList("", "");
        //List<String> strs = Collections.emptyList();
        // List<String> strs = Arrays.asList("3OSUiNAP4fkGt49##[w");
        // System.out.println(codec.decode(codec.encode(strs)));
        //System.out.println(codec.decode_withEscape(codec.encode_withEscape(strs)));
        System.out.println(codec.decode(codec.encode(strs)));
    }
}
