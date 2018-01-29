package leetcode.tree;

/*
 * LEETCODE 450
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
 * Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * 	1. Search for a node to remove.
 * 	2. If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 * Example: root = [5,3,6,2,4,null,7] key = 3
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 * Another valid answer is [5,2,6,null,4,null,7].
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *     
 * Company: Uber
 * Difficulty: medium
 */
public class BSTDeleteNode {

    // Complexity: O(h) every node along the path to be visited best case once
    // (delete leaf),
    // worst case twice (delete root).
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            
            // save a link to the node to be deleted
            TreeNode t = root;
            
            // set root to point to its successor
            root = findMin(root.right);
            
            // set the right link of root (which is supposed to point to 
            // the BST containing all the keys larger that root.val) to 
            // deleteMin(t.right)
            root.right = deleteMin(t.right);
            
            // set the left link of root to t.left (all the keys less than 
            // the deleted node and its successor need to set the left child 
            // after right, otherwise it will be in an infinite loop
            root.left = t.left;
        }
        return root;
    }
    
    private TreeNode findMin(TreeNode node) {
        if (node.left == null) return node;
        return findMin(node.left);
    }
    
    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        BSTDeleteNode b = new BSTDeleteNode();
        b.deleteNode(root, 3);

    }
}
