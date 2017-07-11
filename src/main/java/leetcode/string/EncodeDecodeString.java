package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network 
 * and is decoded back to the original list of strings.
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
 * - The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized 
 * enough to work on any possible characters.
 * - Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * - Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 * 
 * Company: Google
 */
public class EncodeDecodeString {
	
	// Use escape
	// Double any hashes inside the strings, then use stand alone hashes (surrounded by spaces) to mark string endings. For example:
	// {"abc", "def"}    =>  "abc # def # "
	// {'abc', '#def'}   =>  "abc # ##def # "
	// {'abc##', 'def'}  =>  "abc#### # def # "
	// For decoding, just do the reverse: First split at stand alone hashes, then undo the doubling in each string.
    public String encode_withEscape(List<String> strs) {
    	StringBuilder sb = new StringBuilder();
        if (strs == null) return sb.toString();
        
        for (String str : strs) {
        	sb.append(str.replace("#", "##")).append(" # "); // need to append the result of replace call, cannot append str because str is immutable
        }
        
    	return sb.toString();
    }

    public List<String> decode_withEscape(String s) {
        if (s == null) return Collections.emptyList();
        
        List<String> result = new ArrayList<>();
        String[] strs = s.split(" # ", -1);  // use negative limit to handle empty string as input
        for (int i = 0 ; i < strs.length - 1; i++) { // skip the last entry because it is empty string with negative limit in split
        	result.add(strs[i].replace("##", "#"));
        }
        return result;
    }
    
    //
    // encode as <str len>/<str>
    //
    public String encode(List<String> strs) {
    	StringBuilder sb = new StringBuilder();
        if (strs == null) return sb.toString();
        
        for (String str : strs) {
        	sb.append(str.length()).append("/").append(str);
        }
        
    	return sb.toString();
    }

    public List<String> decode(String s) {
        if (s == null) return Collections.emptyList();
        
        List<String> result = new ArrayList<>();
        int start = 0;
        while (start < s.length()) {
        	int slashPos = s.indexOf('/', start);
        	int len = Integer.valueOf(s.substring(start, slashPos));
        	result.add(s.substring(slashPos+1, slashPos+1+len));
        	start = slashPos + len + 1;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	EncodeDecodeString codec = new EncodeDecodeString();
    	//List<String> strs = Arrays.asList("");
    	List<String> strs = Arrays.asList("3OSUiNAP4fkGt49##[w");
    	System.out.println(codec.decode(codec.encode(strs)));
    }
}
