package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * LEETCODE 536
 * You need to construct a binary tree from a string consisting of parenthesis and integers. The whole input represents a binary tree. 
 * It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis 
 * contains a child binary tree with the same structure. You always start to construct the left child node of the parent first if it exists.
 * Example:
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 *        4
 *      /   \
 *     2     6
 *    / \   / 
 *   3   1 5   
 * Note:
 * - There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 * - An empty tree is represented by "" instead of "()".
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Question: 606(ConstructStringFromBinaryTree)
 */
public class ConstructBinaryTreeFromString {
    private int index = 0;

    public TreeNode str2tree(String s) {
        if (s == null || index == s.length())
            return null;

        StringBuilder num = new StringBuilder(); // use StringBuilder to
                                                 // calculate the node value,
                                                 // this takes care of the
                                                 // negative numbers
        while (index < s.length() && s.charAt(index) != '(' && s.charAt(index) != ')') {
            num.append(s.charAt(index));
            index++;
        }

        TreeNode node = new TreeNode(Integer.parseInt(num.toString()));

        // construct left child if it exists
        if (index < s.length() && s.charAt(index) == '(') {
            index++; // move over (
            node.left = str2tree(s);
            index++; // move over )

            // check for right child
            if (index < s.length() && s.charAt(index) == '(') {
                index++;
                node.right = str2tree(s);
                index++;
            }
        }

        return node;
    }

    public TreeNode str2tree_withQueue(String s) {
        if (s == null || s.isEmpty())
            return null;
        Queue<Character> queue = s.chars().mapToObj(i -> (char) i).collect(Collectors.toCollection(LinkedList::new));
        return helper(queue);
    }

    public TreeNode helper(Queue<Character> queue) {
        if (queue.isEmpty())
            return null;

        StringBuilder num = new StringBuilder(); // use StringBuilder to
                                                 // calculate the node value,
                                                 // this takes care of the
                                                 // negative numbers
        while (!queue.isEmpty() && !queue.peek().equals('(') && queue.peek() != ')') {
            num.append(queue.poll());
        }
        TreeNode node = new TreeNode(Integer.parseInt(num.toString()));

        // construct left child if it exists
        if (!queue.isEmpty() && queue.peek().equals('(')) {
            queue.poll(); // move over (
            node.left = helper(queue);
            queue.poll(); // move over )

            // check for right child
            if (!queue.isEmpty() && queue.peek().equals('(')) {
                queue.poll(); // move over (
                node.right = helper(queue);
                queue.poll(); // move over )
            }
        }

        return node;
    }
    
    public TreeNode str2tree(StringBuilder sb) {
        if (sb.length() == 0) return null;
        if (sb.charAt(0) == '(') sb.deleteCharAt(0);
        int i = 0;
        while (i < sb.length() && (sb.charAt(i) != '(' && sb.charAt(i) != ')')) {
            //System.out.println("i=" + i + " c=" + sb.charAt(i));
            i++;
        }
        TreeNode node = new TreeNode(Integer.parseInt(sb.substring(0, i)));
        sb.delete(0, i);
        if (sb.length() > 0 && sb.charAt(0) == '(') {
            sb.deleteCharAt(0);
            node.left = str2tree(sb);
            sb.deleteCharAt(0);
            if (sb.length() > 0 && sb.charAt(0) == '(') {
                sb.deleteCharAt(0);
                node.right = str2tree(sb);
                sb.deleteCharAt(0);
            }
        }
        return node;
    }

}
