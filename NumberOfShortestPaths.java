package DAAAssignments;

import java.util.*;

public class NumberOfShortestPaths {

    static int count = 0;
    static int minHops = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Map<Integer,List<Integer>> graph = new HashMap<>();

        // Adding vertices
        for (int i = 0; i < 10; i++) {
            graph.put(i, new ArrayList<>());
        }

        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 5);
        addEdge(graph, 2, 6);
        addEdge(graph, 3, 7);
        addEdge(graph, 4, 8);
        addEdge(graph, 5, 9);

        // Adding the new path from 4 to 2 to 3 to 9
        addEdge(graph, 4, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 9);

        System.out.println(getNumberOfShortestPaths(5, 3, graph));



    }

    private static int getNumberOfShortestPaths(int from, int to, Map<Integer, List<Integer>> graph) {

        boolean[] visited = new boolean[graph.size()];
        visited[from] = true;
        bfs(from, to, visited, graph, 0);

        return count;
    }

    private static void bfs(Integer node, int to, boolean[] visited, Map<Integer, List<Integer>> graph, int currentHops) {
        currentHops++;
        if (node == to)
        {
            if (currentHops==minHops){
                count++;
            }else if(currentHops<minHops)
            {
                count = 1;
                minHops = currentHops;
            }
        }else {
            for (Integer neighbor: graph.get(node))
            {
                if (!visited[neighbor]){
                    visited[neighbor] = true;
                    bfs(neighbor, to, visited, graph, currentHops);
                }

            }
        }
        visited[node]=false;

    }
    private static void addEdge(Map<Integer, List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }


}
