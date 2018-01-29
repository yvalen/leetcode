package leetcode.binarysearch;

/*
 * LEETCODE 744
 * Given a list of sorted characters letters containing only lowercase letters, 
 * and given a target letter target, find the smallest element in the list that 
 * is larger than the given target. Letters also wrap around. For example, if the 
 * target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 * Examples:
 * Input: letters = ["c", "f", "j"] target = "a"
 * Output: "c"
 * Input: letters = ["c", "f", "j"] target = "c"
 * Output: "f"
 * Input: letters = ["c", "f", "j"] target = "d"
 * Output: "f"
 * Input: letters = ["c", "f", "j"] target = "g"
 * Output: "j"
 * Input: letters = ["c", "f", "j"] target = "j"
 * Output: "c"
 * Input: letters = ["c", "f", "j"] target = "k"
 * Output: "c"
 * Note:
 * - letters has a length in range [2, 10000].
 * - letters consists of lowercase letters, and contains at least 2 unique letters.
 * - target is a lowercase letter.
 * 
 * Company: LinkedIn
 * Difficulty: easy
 */
public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int lo = 0, hi = letters.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // check for equal here as we want lo points to the first 
            // element that is greater than target 
            if (letters[mid] <= target) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        
        return lo == letters.length ? letters[0] : letters[lo];
    }
    
    public static void main(String[] args) {
        FindSmallestLetterGreaterThanTarget  slgt = new FindSmallestLetterGreaterThanTarget ();
        
        //char[] letters = {'c', 'f', 'j'};
        //char target = 'k';
        //char target = 'j';
        char[] letters = {'e', 'e', 'e', 'e', 'e', 'e', 'n', 'n', 'n', 'n'};
        char target = 'e';
        System.out.println(slgt.nextGreatestLetter(letters, target));
    }
}
