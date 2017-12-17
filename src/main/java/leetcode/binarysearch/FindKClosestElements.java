package leetcode.binarysearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/*
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. 
 * If there is a tie, the smaller elements are always preferred.
 * Example 1: Input: [1,2,3,4,5], k=4, x=3 Output: [1,2,3,4]
 * Example 2: Input: [1,2,3,4,5], k=4, x=-1 Output: [1,2,3,4]
 * Note:
 * - The value k is positive and will always be smaller than the length of the sorted array.
 * - Length of the given array is positive and will not exceed 10^4
 * - Absolute value of elements in the array and x will not exceed 10^4
 * 
 * Company: Google
 * Difficulty: medium
 */
public class FindKClosestElements {

    // Time complexity: O(nlogn)
    public List<Integer> findClosestElements_withSorting(List<Integer> arr, int k, int x) {
        // sort the array first by the difference
        Collections.sort(arr, (a, b) -> Math.abs(a - x) - Math.abs(b - x));

        // extract the first k element
        List<Integer> result = arr.subList(0, k);

        Collections.sort(result);

        return result;
    }

    // Time xomplexity: O(logn+k)
    public List<Integer> findClosestElements_withBinarySearch(List<Integer> arr, int k, int x) {
        int n = arr.size();

        // handle special cases
        if (arr.get(0) >= x)
            return arr.subList(0, k);
        if (arr.get(n - 1) <= x)
            return arr.subList(n - k, n);

        // find the first number which is equal to or greater than x

        // Collections.binarySearch returns the index of the search key, if it
        // is contained in the list; otherwise, (-(insertion point) - 1).
        // The insertion point is defined as the point at which the key would be
        // inserted into the list: the index of the first element greater
        // than the key, or list.size() if all elements in the list are less
        // than the specified key.
        int index = Collections.binarySearch(arr, x);
        if (index < 0)
            index = -(index + 1);

        int left = index, right = index;
        while (k-- > 0) {
            if (left < 0 || (right < n && Math.abs(arr.get(left) - x) > Math.abs(arr.get(right) - x)))
                right++;
            else
                left--;
        }
        return arr.subList(left + 1, right);
    }

    private int findClosetElementIndex(List<Integer> arr, int x) {
        int lo = 0, hi = arr.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = arr.get(mid);
            if (val == x)
                return mid;
            else if (val > x)
                hi = mid - 1;
            else
                lo = mid + 1;
        }

        if (hi < 0)
            return lo;
        if (lo > arr.size() - 1)
            return hi;
        return Math.abs(arr.get(lo) - x) <= Math.abs(arr.get(hi) - x) ? lo : hi;
    }

    public static void main(String[] args) {
        FindKClosestElements fce = new FindKClosestElements();
        // List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
        // int k = 4, x = 6;
        // List<Integer> arr = Arrays.asList(0,0,1,2,3,3,4,7,7,8);
        // int k = 3, x = 5;
        // List<Integer> arr = Arrays.asList(0,1,2,2,2,3,6,8,8,9);
        // int k = 5, x = 9;
        List<Integer> arr = Arrays.asList(0, 2, 2, 3, 4, 6, 7, 8, 9, 9);
        int k = 4, x = 5;
        System.out.println(fce.findClosestElements_withBinarySearch(arr, k, x));

    }
}
