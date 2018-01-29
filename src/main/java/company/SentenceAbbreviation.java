package company;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.LocatorEx.Snapshot;

/*
 * Pinterest
 * 
 */
public class SentenceAbbreviation {
    /*
     *  English words array ["Alice", "Bob", "Tom"] ->  "Alice Bob and Tom" 
     */
    public static String getAbbreviation(String[] words) {
        if (words == null || words.length == 0) return "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length-1; i++) {
            sb.append(words[i]).append(" ");
        }
        if (sb.length() > 0) {
            sb.append("and ");
        }
        sb.append(words[words.length-1]);
        return sb.toString();
    }
    
    /*
     * Follow up: Array and number  2 -> "Alice Bob and 1 more"  1 -> "Alice and 2 more"
     */
    public static String getAbbreviation(String[] words, int k) {
        if (words == null || words.length == 0 || k <= 0) return "";
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < k && i < words.length) {
            if (i == words.length-1) {
                sb.append("and ").append(words[i]);
            }
            else {
                sb.append(words[i]).append(" ");
            }
            i++;
        }
        if (k < words.length) {
            sb.append("and ").append(words.length-k).append(" more");
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args) {
        //String[] words = {"Alice", "Bob", "Tom", "Nick", "Lucy"};
        String[] words = {"Alice", "Bob", "Tom"};
        int k = 3;
        //String[] words = {"Alice"};
        //System.out.println(SentenceAbbreviation.getAbbreviation(words));
        System.out.println(SentenceAbbreviation.getAbbreviation(words, k));
    }
}
