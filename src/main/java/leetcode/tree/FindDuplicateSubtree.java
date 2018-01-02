package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LEETCODE 652
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, 
 * you only need to return the root node of any one of them.
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
    // Serialize each subtree, the serialization string is the unique representation of the tree.
    // Perform a depth-first search, where the recursive function returns the serialization of the tree. 
    // At each node, record the result in a map, and analyze the map after to determine duplicate subtrees.
    // Time Complexity: O(N^2), where N is the number of nodes in the tree. We visit each node once, 
    // but each creation of serial may take O(N) work.
    // Space Complexity: O(N^2), the size of the map
    public List<TreeNode> findDuplicateSubtrees_serialize(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<TreeNode> result = new ArrayList<>();
        dfs(root, new HashMap<>(), result);
        return result;
    }

    private String dfs(TreeNode root, Map<String, Integer> map, List<TreeNode> result) {
        if (root == null) {
            // with null node set to # the tree can uniquely defined using post order (or pre order), 
            // null has to be represented by a char
            return "#";
        }
          
        String key = root.val + " " + dfs(root.left, map, result) + " " + dfs(root.right, map, result);
        if (map.containsKey(key) && map.get(key) == 1) {
            result.add(root);
        }
        map.put(key, map.getOrDefault(key, 0) + 1);
        return key;
    }
}
