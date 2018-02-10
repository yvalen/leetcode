package leetcode.tree;

/*
 * LEETCODE 156
 * Given a binary tree where all the right nodes are either leaf nodes with 
 * a sibling (a left node that shares the same parent node)  or empty, flip 
 * it upside down and turn it into a tree where the original right nodes 
 * turned into left leaf nodes. Return the new root.
 * For example: given a binary tree {1,2,3,4,5},
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1  
 *    
 * Company: LinkedIn
 * Difficulty: medium
 * Similar Questions: 206(Reverse Linked List)    
 */
public class BinaryTreeUpsideDown {

    /*
     * The transform of the base three-node case is like below: Root L / \ / \ L
     * R R Root You can image you grab the L to the top, then the Root becomes
     * it's right node, and the R becomes its left node. To solve it
     * recursively, keep finding the left most node, make it upside-down, then
     * make its parent to be its right most subtree recursively. When you
     * connect the root to the right subtree, you need to make sure you are not
     * copying the original root, otherwise it will become cyclic
     * 
     * https://discuss.leetcode.com/topic/40924/java-recursive-o-logn-space-and-
     * iterative-solutions-o-1-space-with-explanation-and-figure/2
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null)
            return root;

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        // need to use root instead of newRoot here,
        // as root are still pointing the its old children
        root.left.left = root.right;
        root.left.right = root;
        // need to nullify both root.left and root.right
        root.left = null;
        root.right = null;
        return newRoot;
    }

    public TreeNode upsideDownBinaryTree_iterative(TreeNode root) {
        TreeNode current = root;
        // prev - parent of current node, next - left child of current node,
        // temp - right child of current node's parent
        TreeNode prev = null, next = null, temp = null;
        while (current != null) {
            next = current.left;

            // swapping nodes now, need temp to keep the previous right child
            current.left = temp;
            temp = current.right;
            current.right = prev;

            prev = current;
            current = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        BinaryTreeUpsideDown btu = new BinaryTreeUpsideDown();
        // TreeNode root = SerializeDeserialize.deserialize_bfs("1,2,null,3");
        // System.out.println(SerializeDeserialize.serialize_bfs(btu.upsideDownBinaryTree_iterative(root)));

        TreeNode root = SerializeDeserialize.deserialize_bfs("1,2,3,4,5");
        System.out.println(SerializeDeserialize.serialize_bfs(btu.upsideDownBinaryTree_iterative(root)));
    }
}
