package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]. 
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();
        int start = nums[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1] + 1) {
                addResult(start, nums[i - 1], sb, result);
                start = nums[i];
            }
        }

        addResult(start, nums[nums.length - 1], sb, result); // need to process
                                                             // the last one
                                                             // outside loop
        return result;
    }

    private void addResult(int start, int end, StringBuilder sb, List<String> result) {
        sb.append(start);
        if (end > start)
            sb.append("->").append(end);
        result.add(sb.toString());
        sb.setLength(0);
    }

    public static void main(String[] args) {
        SummaryRanges sr = new SummaryRanges();
        int[] nums = { 0, 1, 2, 4, 5, 7 };
        System.out.println(sr.summaryRanges(nums));
    }
}
