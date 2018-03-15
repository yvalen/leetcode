package leetcode.design;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 551
 * TinyURL is a URL shortening service where you enter a URL such as 
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL 
 * such as http://tinyurl.com/4e9iAk. 
 * Design the encode and decode methods for the TinyURL service. There is no 
 * restriction on how your encode/decode algorithm should work. 
 * You just need to ensure that a URL can be encoded to a tiny URL and the 
 * tiny URL can be decoded to the original URL.
 * https://discuss.leetcode.com/topic/95853/a-complete-solution-for-tinyurl-leetcode-system-design
 * 
 * Company: Amazon, Google, Uber, Facebook
 * Difficulty: medium
 */
public class EncodeDecodeTinyURL {
    char[] charSet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z' };
    private final Map<Long, String> idToUrlMap;
    private final Map<String, Long> tinyUrlToIdMap;
    private long sequenceId = 1;

    public EncodeDecodeTinyURL() {
        this.idToUrlMap = new HashMap<>();
        this.tinyUrlToIdMap = new HashMap<>();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        idToUrlMap.put(sequenceId, longUrl);
        String encodedUrl = base10ToBase62(sequenceId);
        // tinyUrlToIdMap.put(encodedUrl, sequenceId);
        sequenceId++;
        return encodedUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String s = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        long id = base62ToBase10(s);
        return idToUrlMap.get(id);
    }

    private String base10ToBase62(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(charSet[(int) (id % 62)]);
            id /= 62;
        }
        // pad the result string into 6 chars sequence
        while (sb.length() < 6)
            sb.append('0');

        return sb.reverse().toString();
    }

    private long base62ToBase10(String s) {
        long id = 0;
        for (char c : s.toCharArray()) {
            id = id * 62 + charToInt(c);
        }
        return id;
    }

    private int charToInt(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z')
            return c - 'a' + 10;
        if (c >= 'A' && c <= 'Z')
            return c - 'A' + 36;
        return -1;
    }
}
