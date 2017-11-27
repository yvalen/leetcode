package leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<>();
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<UndirectedGraphNode> visited = new HashSet<>();
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		queue.offer(this);
		visited.add(this);
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				UndirectedGraphNode current = queue.poll();
				sb.append(current.label);
				for (UndirectedGraphNode neighbor : current.neighbors) {
					if (!visited.contains(neighbor)) {
						visited.add(neighbor);
						queue.offer(neighbor);
					}
					sb.append(",");
					sb.append(neighbor.label);
				}
			}
			if (!queue.isEmpty()) sb.append("#");
		}

		return sb.toString();
	}





}
