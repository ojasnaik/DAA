package DAAAssignments;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSort {

    public static void main(String[] args) {
        Node nodeA = new Node('a');
        Node nodeB = new Node('b');
        Node nodeC = new Node('c');
        Node nodeD = new Node('d');
        Node nodeE = new Node('e');
        Node nodeF = new Node('f');
        Node nodeG = new Node('g');

        nodeA.neighbors.addAll(List.of());
        nodeB.neighbors.addAll(List.of(nodeA, nodeD));
        nodeC.neighbors.addAll(List.of(nodeA));
        nodeD.neighbors.addAll(List.of(nodeA, nodeC));
        nodeE.neighbors.addAll(List.of(nodeB, nodeD, nodeG));
        nodeF.neighbors.addAll(List.of(nodeC, nodeD));
        nodeG.neighbors.addAll(List.of(nodeF, nodeD));

        List<Node> graph = new ArrayList<>(List.of(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG));

        List<Node> list = getTopologicalOrder(graph);
        System.out.println(list);

    }

    private static List<Node> getTopologicalOrder(List<Node> graph) {
        List<Node> topoList = new LinkedList<>();
        boolean[] visited = new boolean[130];
        for (Node graphNode : graph) {
            if (!visited[graphNode.val]) {
                dfs(graphNode, visited, topoList);
            }
        }

        return topoList;
    }

    private static void dfs(Node graphNode, boolean[] visited, List<Node> topoList) {

        visited[graphNode.val] = true;

        for (Node neighbor : graphNode.neighbors) {
            if (!visited[neighbor.val]) {
                dfs(neighbor, visited, topoList);
            }
        }
        topoList.add(0, graphNode);

    }


}

class Node {
    public char val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(char _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(char _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
