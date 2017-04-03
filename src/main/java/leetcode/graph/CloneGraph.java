package leetcode.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
	
	// Time O((n) Space O(n)
	public UndirectedGraphNode cloneGraph_bfs(UndirectedGraphNode node) {
		if (node == null) return null;
		
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap<>();
		nodeMap.put(node, clone);
		Queue<UndirectedGraphNode> nodeQueue = new LinkedList<>();
		nodeQueue.offer(node);
		while (!nodeQueue.isEmpty()) {
			UndirectedGraphNode original = nodeQueue.poll();
			for (UndirectedGraphNode neighbor : original.neighbors) {
				if (nodeMap.containsKey(neighbor)) {
					nodeMap.get(original).neighbors.add(nodeMap.get(neighbor)); // use nodeMap.get(original) to get the current node
				}
				else {
					UndirectedGraphNode neighborClone = new UndirectedGraphNode(neighbor.label);
					nodeMap.put(neighbor, neighborClone);
					nodeMap.get(original).neighbors.add(neighborClone);
					nodeQueue.offer(neighbor);
				}
			}
		}
		
		return clone;
    }
	
	
	Map<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
	// Time O((n) Space O(n)
	public UndirectedGraphNode cloneGraph_dfs(UndirectedGraphNode node) {
		if (node == null) return null;
		
		if (visited.containsKey(node)) return visited.get(node);
		
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		visited.put(node, clone);
		for (UndirectedGraphNode neighbor : node.neighbors) {
			clone.neighbors.add(cloneGraph_dfs(neighbor));
		}
		return clone;
    }
	
	public static void main(String[] args) {
		CloneGraph c = new CloneGraph();
		
		//UndirectedGraphNode node = new UndirectedGraphNode(0);
		//node.neighbors.add(new UndirectedGraphNode(0));
		//node.neighbors.add(new UndirectedGraphNode(0));
		
		UndirectedGraphNode node0 = new UndirectedGraphNode(0);
		UndirectedGraphNode node1 = new UndirectedGraphNode(1);
		UndirectedGraphNode node2 = new UndirectedGraphNode(2);
		node0.neighbors.add(node1);
		node1.neighbors.add(node2);
		node2.neighbors.add(node2);
		c.cloneGraph_bfs(node0);
	}
 
}
