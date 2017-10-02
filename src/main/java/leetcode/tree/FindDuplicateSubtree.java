package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LEETCODE 652
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with same node values.
 * Example 1:
 *       1
 *      / \
 *     2   3
 *    /   / \
 *   4   2   4
 *      /
 *     4
 * The following are two duplicate subtrees:
 *     2
 *    /
 *   4
 * and
 *    4
 * Therefore, you need to return above trees' root in the form of a list.
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Question: 297(SerializeDeserialize), 449(SerializationBST ), 606(ConstructStringFromBinaryTree)
 */
public class FindDuplicateSubtree {
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<TreeNode> result = new ArrayList<>();
        postorder(root, new HashMap<>(), result);
        return result;
	}
	
	private String postorder(TreeNode root, Map<String, Integer> map, List<TreeNode> result) {
		if (root == null) return "#"; // with null node set to # the tree can uniquely defined using post order (or pre order)
		String key = root.val + " " + postorder(root.left, map, result) + " " + postorder(root.right, map, result);
		if (map.containsKey(key) && map.get(key) == 1) result.add(root);
		map.put(key, map.getOrDefault(key, 0)+1);
		return key;
	}
}
