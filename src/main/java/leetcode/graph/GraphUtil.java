package leetcode.graph;

import java.util.HashSet;
import java.util.Set;

public class GraphUtil {
	public static UndirectedGraphNode deserialize(String s) {
		String[] nodes = s.split("#");
		Set<UndirectedGraphNode> visited = new HashSet<>();
		for (String node : nodes) {
			String[] edges = node.split(",");
			for (int i = 0; i < edges.length; i++) {
				if (visited.contains(edges[i])) continue;
				//visited.add(e).add(edges[i]);
				
			}
			
		}
		
		
		return null;
	}

}
