package leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * LEETCODE 133
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors. 
 * 
 * Company: Pocket Gems, Google, Uber, Facebook
 * Difficulty: medium
 * Similar Questions: 138(CopyWithRandomPointer)
 */
public class CloneGraph {
    //
    // BFS
    // Time O((n) Space O(n)
    //
    public UndirectedGraphNode cloneGraph_bfs(UndirectedGraphNode node) {
        if (node == null)
            return null;

        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap<>();
        nodeMap.put(node, clone);
        Queue<UndirectedGraphNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(node);
        while (!nodeQueue.isEmpty()) {
            UndirectedGraphNode original = nodeQueue.poll();
            for (UndirectedGraphNode neighbor : original.neighbors) {
                if (nodeMap.containsKey(neighbor)) {
                    // use map to get the clone for original
                    nodeMap.get(original).neighbors.add(nodeMap.get(neighbor));
                } else {
                    UndirectedGraphNode neighborClone = new UndirectedGraphNode(neighbor.label);
                    nodeMap.put(neighbor, neighborClone);
                    nodeMap.get(original).neighbors.add(neighborClone);
                    nodeQueue.offer(neighbor);
                }
            }
        }

        return clone;
    }

    //
    // DFS
    // // Time O((n) Space O(n)
    //
    /*
     * Map<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
     * public UndirectedGraphNode cloneGraph_dfs(UndirectedGraphNode node) { if
     * (node == null) return null;
     * 
     * if (visited.containsKey(node)) return visited.get(node);
     * 
     * UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
     * visited.put(node, clone); for (UndirectedGraphNode neighbor :
     * node.neighbors) { clone.neighbors.add(cloneGraph_dfs(neighbor)); } return
     * clone; }
     */

    public UndirectedGraphNode cloneGraph_dfs(UndirectedGraphNode node) {
        if (node == null)
            return null;
        // need to use a map to remember nodes cloned and corresponding cloned
        // node, otherwise won't be able to able to handle self loop
        Map<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
        return dfs(node, visited);
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> visited) {
        // returned the cloned node if the original node has been cloned already
        if (visited.containsKey(node))
            return visited.get(node);

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        visited.put(node, newNode);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            // can't check visited here, otherwise self loop will not be cloned
            // visited is checked already at the beginning
            newNode.neighbors.add(dfs(neighbor, visited));
        }
        return newNode;
    }

    public static void main(String[] args) {
        CloneGraph c = new CloneGraph();

        // UndirectedGraphNode node = new UndirectedGraphNode(0);
        // node.neighbors.add(node);
        // c.cloneGraph_dfs(node);

        // node.neighbors.add(new UndirectedGraphNode(0));
        // node.neighbors.add(new UndirectedGraphNode(0));

        /*
         * UndirectedGraphNode node0 = new UndirectedGraphNode(0);
         * UndirectedGraphNode node1 = new UndirectedGraphNode(1);
         * UndirectedGraphNode node2 = new UndirectedGraphNode(2);
         * node0.neighbors.add(node1); node1.neighbors.add(node2);
         * node2.neighbors.add(node2); c.cloneGraph_bfs(node0);
         */

        UndirectedGraphNode node1 = new UndirectedGraphNode(-1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(1);
        node1.neighbors.add(node2);
        System.out.println(c.cloneGraph_bfs(node1));
    }

}
