package DAAAssignments;

import java.util.*;

public class BreadthFirstSearch {
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

        System.out.println(bfsTraversal(graph));
    }

    private static List<Integer> bfsTraversal(Map<Integer, List<Integer>> graph) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        List<Integer> traversalList = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        for (Integer node : graph.keySet()) {
            if (!visited[node]) {
                bfsQueue.add(node);
                visited[node] = true;
            }
            while (!bfsQueue.isEmpty()) {
                Integer currentNode = bfsQueue.remove();
                traversalList.add(currentNode);
                for (Integer neighbor : graph.get(currentNode)) {
                    if (!visited[neighbor]) {
                        bfsQueue.add(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
        }
        return traversalList;
    }
}
