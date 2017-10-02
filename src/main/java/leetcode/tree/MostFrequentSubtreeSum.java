package leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum 
 * of a node is defined as the sum of all the node values formed by the subtree rooted at that node 
 * (including the node itself). So what is the most frequent subtree sum value? If there is a tie, 
 * return all the values with the highest frequency in any order.
 * Examples 1: 
 * Input:
 * 	   5
 *    /  \
 *   2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * Examples 2
 * Input:
 *    5
 *  /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once. 
 */
public class MostFrequentSubtreeSum {
	private int maxCount;
	
	public int[] findFrequentTreeSum(TreeNode root) {
		if (root == null) return new int[0];
		Map<Integer, Integer> sumCounts = new HashMap<>();
		findFrequentTreeSum_helper(root, sumCounts);
		List<Integer> result = new LinkedList<>();
		for (Map.Entry<Integer, Integer> entry : sumCounts.entrySet()) {
			if (entry.getValue() == maxCount) result.add(entry.getKey());
		}
		return result.stream().mapToInt(Integer::intValue).toArray();
    }
	
	private Integer findFrequentTreeSum_helper(TreeNode root, Map<Integer, Integer> sumCounts) {
		Integer sum = root.val; 
		sum += root.left != null ? findFrequentTreeSum_helper(root.left, sumCounts) : 0;
		sum += root.right != null ? findFrequentTreeSum_helper(root.right, sumCounts) : 0;
		int count = sumCounts.getOrDefault(sum, 0) + 1;
		sumCounts.put(sum, count);
		maxCount = Integer.max(count, maxCount);
		return sum;
	}
	
	public static void main(String[] args) {
		MostFrequentSubtreeSum s = new MostFrequentSubtreeSum();
		/*
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(-3);
		*/
		
		TreeNode root = SerializeDeserialize.deserialize_bfs("3,1,5,0,2,4,6,null,null,null,3");
		System.out.println(SerializeDeserialize.serialize_bfs(root));
		
		int[] result = s.findFrequentTreeSum(root);
		IntStream.of(result).forEach(System.out::println);
	}
}
