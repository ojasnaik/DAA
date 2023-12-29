package DAAAssignments;

import java.util.*;

public class ShortestPath {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i <= 7; i++) {
            graph.put(i, new ArrayList<>());
        }
        LinkedList<Integer> list = new LinkedList<>();
        graph.get(0).addAll(List.of(2, 3));
        graph.get(1).addAll(List.of(2, 5, 6));
        graph.get(2).addAll(List.of(0, 1, 4));
        graph.get(3).addAll(List.of(0, 7));
        graph.get(4).addAll(List.of(2, 6));
        graph.get(5).addAll(List.of(1, 6));
        graph.get(6).addAll(List.of(1, 4, 5));
        graph.get(7).addAll(List.of(3));

        System.out.println(getDistance(graph, 0, 5));
    }

    private static int getDistance(Map<Integer, List<Integer>> graph, int startNode, int endNode) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        int[] distanceArray = new int[graph.size()];
        Arrays.fill(distanceArray, -1);
        distanceArray[startNode] = 0;
        boolean[] visited = new boolean[graph.size()];
        visited[startNode] = true;
        bfsQueue.add(startNode);
        while (!bfsQueue.isEmpty()) {
            Integer currentNode = bfsQueue.remove();
            for (Integer neighbor : graph.get(currentNode)) {
                if (!visited[neighbor]) {
                    bfsQueue.add(neighbor);
                    visited[neighbor] = true;
                    distanceArray[neighbor] = distanceArray[currentNode] + 1;
                }
            }
        }

        return distanceArray[endNode];
    }
}
