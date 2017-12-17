package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ClosestBinarySearchTreeValue {
    /*
     * Given a non-empty binary search tree and a target value, find the value
     * in the BST that is closest to the target. Note: - Given target value is a
     * floating point. - You are guaranteed to have only one unique value in the
     * BST that is closest to the target.
     * 
     * Company: Microsoft, Google, Snapchat
     */
    public int closestValue_iterative(TreeNode root, double target) {
        TreeNode current = root;
        int closet = root.val;
        while (current != null) {
            closet = Math.abs(closet - target) < Math.abs(current.val - target) ? closet : current.val;
            current = target < current.val ? current.left : current.right;
        }

        return closet;
    }

    public int closestValue_recursive(TreeNode root, double target) {
        // if target is less than root, right tree can be ruled out because
        // all nodes in right tree are greater than root, hence root will be
        // closer to target
        TreeNode child = target < root.val ? root.left : root.right;
        if (child == null)
            return root.val;

        int closet = closestValue_recursive(child, target);
        return Math.abs(root.val - target) < Math.abs(closet - target) ? root.val : closet;
    }

    /*
     * Given a non-empty binary search tree and a target value, find k values in
     * the BST that are closest to the target. Note: - Given target value is a
     * floating point. - You may assume k is always valid, that is: k ≤ total
     * nodes. - You are guaranteed to have only one unique set of k values in
     * the BST that are closest to the target. Follow up: Assume that the BST is
     * balanced, could you solve it in less than O(n) runtime (where n = total
     * nodes)?
     * 
     * Company: Google
     */
    // Time complexity O(n), space complexity O(k)
    public List<Integer> closestKValues_inorder(TreeNode root, double target, int k) {
        Queue<Integer> queue = new LinkedList<>(); // keep the k elements,
                                                   // regular queue is enough
                                                   // because inorder sequence
                                                   // is sorted

        // inorder traversal
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (queue.size() < k) {
                    queue.offer(node.val); // add first k elements to queue
                } else {
                    // compare the diff of the first element in queue and target
                    // with node.val
                    if (Math.abs(queue.peek() - target) > Math.abs(node.val - target)) {
                        queue.poll(); // remove the first
                        queue.offer(node.val); // add to tail
                    }
                }
                node = node.right;
            }
        }

        return new ArrayList<>(queue);
    }

    // Time Complexity: O(logN + k), worst case k=n ~ O(N)
    // - build two stack: O(logN)
    // - Each call to findNextPred/Succ has amortized cost O(1), since each node
    // is pushed/popped at most once.
    // We have O(k) = O(n) calls of these, so these operations take O(n) in
    // total.
    // According the follow up question, for a balanced BST, the size of "prec"
    // and "suc" is bounded by logN.
    // Each time, we call getPrec or getSuc, we may kind of shrink/enlarge the
    // stack, but they are still bounded by logN.
    // The amortized complexity will be O(1) for get, since we can consider it
    // like "lazy traverse".
    public List<Integer> closestKValues_twoStacks(TreeNode root, double target, int k) {

        Stack<TreeNode> predecessors = new Stack<>();
        Stack<TreeNode> successors = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            if (node.val < target) {
                predecessors.push(node);
                node = node.right;
            } else {
                successors.push(node);
                node = node.left;
            }
        }

        List<Integer> result = new ArrayList<>();
        while (k-- > 0) {
            if (!predecessors.isEmpty() && !successors.isEmpty()) {
                TreeNode p = predecessors.peek(), s = successors.peek();
                if (target - p.val < s.val - target) {
                    result.add(p.val);
                    getPredecessor(predecessors);
                } else {
                    result.add(s.val);
                    getSuccessor(successors);
                }
            } else if (!predecessors.isEmpty()) {
                result.add(getPredecessor(predecessors).val);
            } else if (!successors.isEmpty()) {
                result.add(getSuccessor(successors).val);
            }
        }

        return result;
    }

    private TreeNode getPredecessor(Stack<TreeNode> predecessors) {
        TreeNode node = predecessors.pop();
        if (node.left != null) {
            predecessors.push(node.left);
            while (predecessors.peek().right != null) {
                predecessors.push(predecessors.peek().right);
            }
        }
        return node;
    }

    private TreeNode getSuccessor(Stack<TreeNode> successors) {
        TreeNode node = successors.pop();
        if (node.right != null) {
            successors.push(node.right);
            while (successors.peek().left != null) {
                successors.push(successors.peek().left);
            }
        }
        return node;
    }

    public static void main(String[] args) {
        ClosestBinarySearchTreeValue bstcv = new ClosestBinarySearchTreeValue();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        double target = 10.0;
        System.out.println(bstcv.closestValue_recursive(root, target));

    }
}
