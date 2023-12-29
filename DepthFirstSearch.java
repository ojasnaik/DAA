package DAAAssignments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearch {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i <= 7; i++) {
            graph.put(i, new ArrayList<>());
        }

        graph.get(0).addAll(List.of(2, 3));
        graph.get(1).addAll(List.of(2, 5, 6));
        graph.get(2).addAll(List.of(0, 1, 4));
        graph.get(3).addAll(List.of(0, 7));
        graph.get(4).addAll(List.of(2, 6));
        graph.get(5).addAll(List.of(1, 6));
        graph.get(6).addAll(List.of(1, 4, 5));
        graph.get(7).addAll(List.of(3));

        System.out.println(dfsTraversal(graph));
    }

    private static List<Integer> dfsTraversal(Map<Integer, List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        List<Integer> traversalOrder = new ArrayList<>();
        for (Integer node : graph.keySet()) {
            if (!visited[node]) {
                visited[node] = true;
                dfs(node, visited, traversalOrder, graph);
            }
        }
        return traversalOrder;
    }
    private static void dfs(Integer currentNode, boolean[] visited, List<Integer> traversalOrder, Map<Integer, List<Integer>> graph) {
        traversalOrder.add(currentNode);
        for (Integer neighbor : graph.get(currentNode)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfs(neighbor, visited, traversalOrder, graph);
            }
        }
    }

}
