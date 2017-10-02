package leetcode.tree;

/*
 * LEETCODE 606
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs 
 * that don't affect the one-to-one mapping relationship between the string and the original binary tree.
 * Example 1: Input: Binary tree: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /    
 *   4     
 * Output: "1(2(4))(3)"
 * Explanation: Originallay it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary empty parenthesis pairs. 
 * And it will be "1(2(4))(3)".
 * Example 2: Input: Binary tree: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \ 
 *       4 
 * Output: "1(2()(4))(3)"
 * Explanation: Almost the same as the first example,  except we can't omit the first parenthesis pair to break the one-to-one mapping 
 * relationship between the input and the output.
 * 
 * Company: Amazon
 * Difficulty: easy
 * Similar Question: 536(ConstructBinaryTreeFromString), 606(FindDuplicateSubtree)
 */
public class ConstructStringFromBinaryTree {
	public String tree2str(TreeNode t) {
		if (t == null) return "";
		
		String result = String.valueOf(t.val);
		String left = tree2str(t.left), right = tree2str(t.right);
		if (left.length() > 0 || right.length() > 0) { 
			if (left.length() > 0) result += "(" + left + ")" + (right.length() > 0 ? "("+right+")" : "");
			else if (right.length() > 0) result += "()(" + right + ")"; 
		}
		return result;
	} 
	
	public String tree2str_withStringBuilder(TreeNode t) {
		if (t == null) return "";
		StringBuilder sb = new StringBuilder();
		helper(t, sb);
		return sb.toString();
	} 
	
	private void helper(TreeNode root, StringBuilder sb) {
		sb.append(root.val);
		if (root.left == null && root.right == null) return;
		
		if (root.left != null) {
			sb.append("(");
			helper(root.left, sb);
			sb.append(")");
		}
		
		if (root.right != null) {
			if (root.left == null) sb.append("()");
			sb.append("(");
			helper(root.right, sb);
			sb.append(")");
		}
	}
}
