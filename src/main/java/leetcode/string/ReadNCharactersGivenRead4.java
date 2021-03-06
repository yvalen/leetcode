package leetcode.string;

class Reader4 {
    int read4(char[] buf) {
        return 0;
    }
}


public class ReadNCharactersGivenRead4 extends Reader4 {
    /**
     * LEETCODE 157
     * The API: int read4(char *buf) reads 4 characters at a time from a file. The
     * return value is the actual number of characters read. For example, it returns
     * 3 if there is only 3 characters left in the file. By using the read4 API,
     * implement the function int read(char *buf, int n) that reads n characters
     * from the file.
     * 
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     * 
     * Company: Facebook
     * Difficulty: easy
     * Similar Questions: 158(Read N Characters Given Read4 II - Call multiple times)
     */
    public int read(char[] buf, int n) {
        if (n < 0 || buf == null || buf.length < n) {
            throw new IllegalArgumentException("invalid input");
        }

        int count = 0;
        char[] buf4 = new char[4]; // more efficient defining outside the loop
        while (count < n) {
            int count4 = read4(buf4);
            
            // need to check for 0 here, otherwise will be in infinite loop for "", 1
            if (count4 == 0) break;
            
            // need to check if count reaches n in every iteration
            for (int i = 0; i < count4 && count < n; i++) { 
                // cannot increment count by charCount outside the loop since it may be less
                buf[count++] = buf4[i]; 
            }
        }
        return count;
    }

    
    
    
    /**
     * LEETCODE 158
     * The API: int read4(char *buf) reads 4 characters at a time from a file.
     * The return value is the actual number of characters read. For example, 
     * it returns 3 if there is only 3 characters left in the file. By using the 
     * read4 API, implement the function int read(char *buf, int n) that reads n 
     * characters from the file.
     * Note: The read function may be called multiple times. 
     * 
     * @param buf Destination buffer
     * @param n Maximum number of characters to read
     * @return The number of characters read
     * 
     * Company: Google, Facebook, Bloomberg
     * Difficulty: hard
     * Similar Questions: 157(Read N Characters Given Read4)
     */
    // Think that you have 4 chars "a, b, c, d" in the file, and you want to
    // call your function twice like this:
    // read(buf, 1); // should return 'a'
    // read(buf, 3); // should return 'b, c, d'
    // All the 4 chars will be consumed in the first call. So the tricky part of
    // this question is how can you preserve
    // the remaining 'b, c, d' to the second call
    public int readII(char[] buf, int n) {
        if (n < 0 || buf == null || buf.length < n) {
            throw new IllegalArgumentException("invalid input");
        }

        int count = 0;
        while (count < n) {
            if (currentPos == 0) {
                charCount = read4(buf4);
            }
            
            // need to break here to avoid infinite loop 
            // when file is empty and n > 0
            if (charCount == 0) break; 

            while (currentPos < charCount && count < n) {
                buf[count++] = buf4[currentPos++];
            }
            
            // reset currentPos when buf4 is all consumed
            if (currentPos == charCount) {
                currentPos = 0;            
            }
        }
        return count;
    }
    
    private char[] buf4 = new char[4];
    private int currentPos;
    private int charCount;


}
