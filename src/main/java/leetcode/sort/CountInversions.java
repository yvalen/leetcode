package leetcode.sort;

/*
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted. 
 * If array is already sorted then inversion count is 0. If array is sorted in reverse order 
 * that inversion count is the maximum n(n-1)/2. Formally speaking, two elements a[i] and a[j] 
 * form an inversion if a[i] > a[j] and i < j 
 * https://www.coursera.org/learn/algorithms-divide-conquer/lecture/IUiUk/o-n-log-n-algorithm-for-counting-inversions-ii
 */
public class CountInversions {
    public int countInversions(int[] nums) {
        int[] aux = new int[nums.length];
        return sortAndCount(nums, aux, 0, nums.length-1);
    }
    
    private int sortAndCount(int[] nums, int[] aux, int lo, int hi) {
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        int leftInversions = sortAndCount(nums, aux, lo, mid);
        int rightInversions = sortAndCount(nums, aux, mid+1, hi);
        int splitInversions = mergeAndCount(nums, aux, lo, mid, hi);
        return leftInversions + rightInversions + splitInversions;
    }
    
    private int mergeAndCount(int[] nums, int[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;
        int count = 0;
        while ( i <= mid && j <= hi) {
            if (nums[i] <= nums[j]) {
                aux[k] = nums[i++];
            }
            else {
                aux[k] = nums[j++];
                count += mid - i + 1;
            }
            k++;
        }
        while (i <= mid) aux[k++] = nums[i++];
        while (j <= hi) aux[k++] = nums[j++];
       
        for (k = lo; k <= hi; k++) nums[k] = aux[k];
        
        return count;
    }

    public static void main(String[] args) {
        CountInversions ci = new CountInversions();
        //int[] nums = {1,3, 5, 2, 4, 6};
        //int[] nums = {8, 4, 2, 1};
        //int[] nums = {1, 2, 3, 4};
        //int[] nums = {2, 1, 3, 1, 2};
        int[] nums = {1, 1, 1, 2, 2};
        System.out.println(ci.countInversions(nums));
    }
} 
