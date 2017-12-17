package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Intersection {
    /**
     * Given two arrays, write a function to compute their intersection.
     * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2]. Note: -
     * Each element in the result must be unique. - The result can be in any
     * order.
     */
    public int[] intersection1_stream(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).distinct().filter(set::contains).toArray();

    }

    /**
     * Given two arrays, write a function to compute their intersection.
     * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2]. Note:
     * - Each element in the result should appear as many times as it shows in
     * both arrays. - The result can be in any order.
     */
    public int[] intersection2_sort(int[] nums1, int[] nums2) {
        // sort inputs O(nlogn)
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0, k = 0, len1 = nums1.length, len2 = nums2.length;
        int[] result = new int[Math.max(len1, len2)];
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j])
                i++;
            else if (nums1[i] > nums2[j])
                j++;
            else {
                result[k++] = nums1[i];
                i++;
                j++;
            }
        }

        return Arrays.copyOf(result, k);
    }

    public int[] intersection2_map(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] small = (len1 < len2) ? nums1 : nums2;
        int[] big = (len1 < len2) ? nums2 : nums1;

        // populate HashMap with the smaller array
        Map<Integer, Integer> occurenceMap = new HashMap<>();
        for (int num : small) {
            Integer occurence = occurenceMap.get(num);
            if (occurence == null) {
                occurence = 0;
            }
            occurenceMap.put(num, ++occurence);
        }

        // loop through bigger array
        List<Integer> result = new ArrayList<>(Math.max(len1, len2));
        for (int num : big) {
            Integer occurence = occurenceMap.get(num);
            if (occurence != null && occurence > 0) {
                result.add(num);
                // decrement the occurence by one
                occurenceMap.put(num, --occurence);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}
