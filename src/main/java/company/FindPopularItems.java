package company;


/*
 * Pinterest
 * Write a function in Java that takes a sorted array and returns any popular element. 
 * A popular element x in array of size n is such that the number of occurrences of x > n/4.
 * In other words, the element must be repeated in the array more than a quarter of the time.
 * https://maxjharris.wordpress.com/2015/03/11/find-popular/
 */
public class FindPopularItems {
    // only actually need to check values at 3 positions: n/4, n/2, and 3n/4. 
    // If an element occurs more than a fourth of the time, it will appear in one of these locations.
    // use Binary Search to find the first position and the last position of those elements
    // Time: O(log n) Space: O(1)
    public static int findPopular(int[] nums) {
        int n = nums.length;
        int fourth = n / 4;
        
        if (countOccurences(nums, nums[fourth]) > fourth) return nums[fourth];
        
        if (countOccurences(nums, nums[n/2]) > fourth) return nums[n/2];
        
        if (countOccurences(nums, nums[3*n/4]) > fourth) return nums[3*n/4];
        
        throw new IllegalArgumentException("No such element");
    }
    
    private static int countOccurences(int[] nums, int target) {
        int firstPos = findPosition(nums, target, true);
        int lastPos = findPosition(nums, target, false);
        return lastPos - firstPos + 1;
    }

    
    private static int findPosition(int[] nums, int target, boolean firstPosition) {
        int lo = 0, hi = nums.length- 1, pos = -1; 
        while (lo <= hi) {
           int mid = lo+(hi-lo)/2;
           if (nums[mid] == target) {
               pos = mid; 
               // need to keep updating hi and lo here until we find the boundary 
               if (firstPosition) hi = mid -1;
               else lo = mid +1;
           }
           else if (nums[mid] < target) {
               lo = mid + 1;
           }
           else {
               hi = mid - 1;
           }
        }
        return pos;
    }
    
    public static void main(String[] ars) {
        int[] nums = {1, 2, 3, 4, 4, 4, 5};
        System.out.println(findPopular(nums));
    }
}
