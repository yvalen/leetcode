package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * 
 * Company: Facebook
 * Difficulty: hard
 */
public class RemoveInvalidParentheses {

	// BFS
	// generate all possible states by removing one ( or ), check if they are valid, if found valid ones on the current level, 
	// put them to the final result list and we are done, otherwise, add them to a queue and carry on to the next level.
	public List<String> removeInvalidParentheses_bfs(String s) {
        if (s == null) return Collections.emptyList(); // don't return for empty s since it needs to return [""]
        
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
        	String curr = queue.poll();
        	
        	if (isValid(curr)) {
        		result.add(curr);
        		found = true;
        	}
        	
        	// need to check this outside the isValid check so that we don't need to remove char from the rest of entries in queue
        	// this is because once an answer is found we don't need to check strings shorter than that
        	if (found) continue; 
        	
        	for (int i = 0; i < curr.length(); i++) {
        		// skip character that is not parentheses 
        		if (curr.charAt(i) != '(' && curr.charAt(i) != ')') continue;
        		
        		// remove the parentheses at index i and add the result string to queue
        		String str = curr.substring(0, i) + curr.substring(i+1);
        		if (!visited.contains(str)) {
        			queue.offer(str);
        			visited.add(str);
        		}
        	}
        }
        return result;
    }
	
	private boolean isValid(String s) {
		int count = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') count++;
			else if (c == ')' && count-- == 0) return false;
		}
		return count == 0;
	}
	
	
	// DFS
	public List<String> removeInvalidParentheses_dfs(String s) {
        if (s == null) return Collections.emptyList();
        
        // get the minimum count to remove
        int leftN = 0, rightN = 0;
        for (char c : s.toCharArray()) {
        	if (c == '(') leftN++;
        	else if (c == ')') {
        		if (leftN > 0) leftN--;
        		else rightN++;
        	}
        }
        
        if (leftN == 0 && rightN == 0) return  Collections.singletonList(s);
        Set<String> resultSet = new HashSet<>();
        dfs(s, resultSet, new StringBuilder(), 0, leftN, rightN, 0);
        return new ArrayList<>(resultSet);
	}
	
	private void dfs(String s, Set<String> resultSet, StringBuilder sb, int i, int leftN, int rightN, int openN) {
		if (leftN < 0 || rightN < 0 || openN < 0) return;
		
		if (i == s.length()) { // reach the end of string
			if (leftN == 0 && rightN == 0 && openN == 0) {
				resultSet.add(sb.toString());
			}
			return;
		}
		
		int len = sb.length(); // save the length of the current string
		char c = s.charAt(i);
		
		if (c == '(') {
			dfs(s, resultSet, sb, i+1, leftN-1, rightN, openN);              // not use (
			dfs(s, resultSet, sb.append('('), i+1, leftN, rightN, openN+1);  // use (
		}
		else if (c == ')') {
			dfs(s, resultSet, sb, i+1, leftN, rightN-1, openN);	             // not use )
			dfs(s, resultSet, sb.append(')'), i+1, leftN, rightN, openN-1);  // use )
		}
		else {
			dfs(s, resultSet, sb.append(c), i+1, leftN, rightN, openN);
		}
		
		System.out.println("i=" + i + " before backtrack:" + sb.toString());
		// backtrack, to get all possible combinations
		sb.setLength(len);
		System.out.println("i=" + i + " after backtrack:" + sb.toString());
	}
	
	
	public static void main(String[] args) {
		RemoveInvalidParentheses rip = new RemoveInvalidParentheses();
		String s = "()())()";
		System.out.println(rip.removeInvalidParentheses_dfs(s));
	}
}
