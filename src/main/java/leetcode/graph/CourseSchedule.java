package leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import leetcode.array.ArrayUtil;

/*
 * Kahn's algorithm
 * L ← Empty list that will contain the sorted elements
 * S ← Set of all nodes with no incoming edges
 * while S is non-empty do
 * 		remove a node n from S
 * 		add n to tail of L
 * 		for each node m with an edge e from n to m do
 * 			remove edge e from the graph
 * 			if m has no other incoming edges then
 * 				insert m into S
 * if graph has edges then
 * 		return error (graph has at least one cycle)
 * else 
 * 		return L (a topologically sorted order)
 */

/*
 * Depth-first search
 * L ← Empty list that will contain the sorted nodes
 * while there are unmarked nodes do
 * 		select an unmarked node n
 * 		visit(n) 
 * 
 * function visit(node n)
 * 		if n has a temporary mark then stop (not a DAG)
 * 		if n is not marked (i.e. has not been visited yet) then
 * 			mark n temporarily
 * 			for each node m with an edge from n to m do
 * 				visit(m)
 * 			mark n permanently
 * 			unmark n temporarily
 * 			add n to head of L
 */

public class CourseSchedule {
	/* There are a total of n courses you have to take, labeled from 0 to n - 1. Some courses may have prerequisites, 
	 * for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
	 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
	 * For example:
	 * 2, [[1,0]] 
	 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
	 * 2, [[1,0],[0,1]]
	 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 
	 * you should also have finished course 1. So it is impossible.
	 * Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. You may assume 
	 * that there are no duplicate edges in the input prerequisites.
	 * 
	 * This problem can be converted to finding if a graph contains a cycle. 
	 */
	public boolean canFinish_bfs(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || 
        		prerequisites.length == 0 || prerequisites[0].length == 0) {
        	return true;
        }
        
        // array stores the number of prereq count for each course
        // used to find 
        int[] prereqCount = new int[numCourses];
        
        // build graph with adjacency list representation
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites, prereqCount);
        
        // enqueue all course with no prereq
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
        	if (prereqCount[i] == 0) queue.offer(i);
        }
        
        int numOfEdges = prerequisites.length;
        while (!queue.isEmpty()) {
        	int current = queue.poll();
        	// get all courses depend on the current one
        	List<Integer> courses = graph.get(current); 
        	for (Integer course : courses) {
        		if (--prereqCount[course] == 0) queue.offer(course);
        		numOfEdges--;
        	}
        }
		
		return (numOfEdges == 0);
    }
	
	// use list of list to represent graph since vertex is an int from 0 to n-1
	// key of the outer list is the vertex, inner list contains the edges 
	// we can also use map to represent graph, key is the vertex, value are the edges 
	private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites, int[] prereqCount) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < prerequisites.length; i++) { // use index to loop through two dimensional array
			int course = prerequisites[i][0];
			int prereq = prerequisites[i][1];
			graph.get(prereq).add(course);
			prereqCount[course]++;
		}
		return graph;
	}


	
	//
	//
	//

	/*
	 * Extension of canFinish
	 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take
	 * to finish all courses. There may be multiple correct orders, you just need to return one of them. If it is impossible 
	 * to finish all courses, return an empty array
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] result = new int[numCourses];
		
		int[] prereqCount = new int[numCourses];
		List<List<Integer>> graph = buildGraph(numCourses, prerequisites, prereqCount);
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (prereqCount[i] == 0) queue.offer(i);
		}
		
		int idx = 0;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			result[idx++] = current;
			for (int c : graph.get(current)) {
				if (--prereqCount[c] == 0 ) queue.offer(c);
			}
		}
		
		return idx == numCourses ? result : new int[0];
    }
	
	
	public static void main(String[] args) {
		CourseSchedule cs = new CourseSchedule();
		int numCourses = 2;
		int[][] prerequisites = {};
		//int[][] prerequisites = {
		//		{1, 0},
				//{0, 1}
		//};
		/*
		int numCourses = 4;
		int[][] prerequisites = {
				{1, 0},
				{2, 0},
				{3, 1},
				{3, 2}
		};
		*/
		//System.out.println(cs.canFinish_bfs(numCourses, prerequisites));
		ArrayUtil.printArray(cs.findOrder(numCourses, prerequisites));
	}
	
}
