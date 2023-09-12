package DAAAssignments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiGraphToSimpleGraph {

    public static void main(String[] args) {

        Node nodeA = new Node('a');
        Node nodeB = new Node('b');
        Node nodeC = new Node('c');
        Node nodeD = new Node('d');
        Node nodeE = new Node('e');
        Node nodeF = new Node('f');
        Node nodeG = new Node('g');

        nodeA.neighbors.addAll(List.of());
        nodeB.neighbors.addAll(List.of(nodeA, nodeD, nodeB));
        nodeC.neighbors.addAll(List.of(nodeA, nodeA));
        nodeD.neighbors.addAll(List.of(nodeA, nodeC));
        nodeE.neighbors.addAll(List.of(nodeB, nodeD, nodeG, nodeD, nodeE));
        nodeF.neighbors.addAll(List.of(nodeC, nodeD));
        nodeG.neighbors.addAll(List.of(nodeF, nodeD));

        List<Node> multiGraph = new ArrayList<>(List.of(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG));
        List<Node> simpleGraph = convertToSimpleGraph(multiGraph);
    }

    private static List<Node> convertToSimpleGraph(List<Node> graph) {
        for (Node node: graph)
        {
            Set<Node> nodeSet = new HashSet<>();
            node.neighbors.forEach(neighbor -> {
                if (neighbor != node) nodeSet.add(neighbor);
            });
            node.neighbors = new ArrayList<>(nodeSet);
        }
        return graph;
    }

}
