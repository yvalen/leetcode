package leetcode.tree;

public class BinaryTreeLongestConsecutiveSequence {
    /*
     * LEETCODE 298 
     * Given a binary tree, find the length of the longest consecutive sequence path. 
     * The path refers to any sequence of nodes from some starting node to any node 
     * in the tree along the parent-child connections. The longest consecutive path 
     * need to be from parent to child (cannot be the reverse). 
     * For example, 
     *      1 
     *       \ 
     *        3 
     *       / \ 
     *      2   4
     *           \ 
     *            5 
     * Longest consecutive sequence path is 3-4-5, so return 3. 
     *      2 
     *       \ 
     *        3 
     *       / 
     *      2 
     *     / 
     *    1 
     * Longest consecutive sequence path is 2-3,not 3-2-1 (since this is not an
     * increasing sequence), so return 2.
     * 
     * Company: Google 
     * Difficulty: medium
     * Similar Questions: 549(Binary Tree Longest Consecutive Sequence II),
     * 128(LongestConsecutiveSequence)
     */
    public int longestConsecutive(TreeNode root) {
        // if (root == null) return 0;
        // return dfs_topdown(root, 0, root.val-1);
        dfs_bottomup(root);
        return maxDepth;
    }

    private int dfs_topdown(TreeNode current, int len, int parentVal) {
        if (current == null) {
            return len;
        }

        int currentLen = (parentVal + 1 == current.val) ? len + 1 : 1;
        return Math.max(currentLen, Math.max(dfs_topdown(current.left, currentLen, current.val),
                dfs_topdown(current.right, currentLen, current.val)));
    }

    private int maxDepth = 0;

    private int dfs_bottomup(TreeNode node) {
        if (node == null)
            return 0;
        int leftDepth = dfs_bottomup(node.left) + 1;
        int rightDepth = dfs_bottomup(node.right) + 1;

        if (node.left != null && node.left.val != node.val + 1) {
            leftDepth = 1;
        }

        if (node.right != null && node.right.val != node.val + 1) {
            rightDepth = 1;
        }

        int len = Math.max(leftDepth, rightDepth); // depth of the current node
        maxDepth = Math.max(maxDepth, Math.max(leftDepth, rightDepth));
        return len; // need to return the current depth instead of maxDepth
    }

    /*
     * LEETCODE 549 
     * Given a binary tree, you need to find the length of Longest Consecutive 
     * Path in Binary Tree. Especially, this path can be either increasing or 
     * decreasing. 
     * For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the 
     * path [1,2,4,3] is not valid. On the other hand, the path can be in the 
     * child-Parent-child order, where not necessarily be parent-child order. 
     * Example 1: 
     * Input: 
     *      1 
     *     / \ 
     *    2   3 
     * Output: 2 
     * Explanation: The longest consecutive path is [1, 2] or [2, 1]. 
     * Example 2: 
     * Input: 
     *      2 
     *     / \
     *    1   3 
     * Output: 3 
     * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1]. 
     * Note: All the values of tree nodes are in the range of [-1e7, 1e7].
     * 
     * Company: Google 
     * Difficulty: medium
     * Similar Questions: 298(Binary Tree Longest Consecutive Sequence)
     */
    public int longestConsecutiveII(TreeNode root) {
        dfsII(root);
        return maxLen;
    }

    private int maxLen = 0;

    private int[] dfsII(TreeNode node) {
        if (node == null)
            return new int[] { 0, 0 };
        int incr = 1, decr = 1;
        if (node.left != null) {
            int[] l = dfsII(node.left);
            if (node.left.val == node.val + 1) {
                incr = l[0] + 1;
            } else if (node.left.val == node.val - 1) {
                decr = l[1] + 1;
            }
        }
        if (node.right != null) {
            int[] r = dfsII(node.right);
            if (node.right.val == node.val + 1) {
                incr = Math.max(incr, r[0] + 1);
            } else if (node.right.val == node.val - 1) {
                decr = Math.max(decr, r[1] + 1);
            }
        }
        // for each subtree, the longest child-parent-child consecutive (with
        // root being the parent) is dec+inc-1
        // since both the ascending and descending path start from root.
        maxLen = Math.max(maxLen, incr + decr - 1);
        return new int[] { incr, decr };
    }
    
    public static void main(String[] args) {
        BinaryTreeLongestConsecutiveSequence  btlic = new BinaryTreeLongestConsecutiveSequence();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(btlic.longestConsecutiveII(root));
    }
}
