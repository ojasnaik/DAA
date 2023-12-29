package DAAAssignments;

import java.util.*;

public class LongestSimplePath {
    public static Map<Integer, List<Edge>> graph = new HashMap<>();
    public static double[] dp;
    public static int[] parent;

    public static void main(String[] args) {
        int V = 6;
        dp = new double[V];
        parent = new int[V];
        for (int i = 0; i < V; i++) {
            graph.put(i, new ArrayList<>());
        }
        graph.get(0).add(new Edge(1, 5));
        graph.get(0).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 6));
        graph.get(1).add(new Edge(2, 2));
        graph.get(2).add(new Edge(4, 4));
        graph.get(2).add(new Edge(5, 2));
        graph.get(3).add(new Edge(5, 1));
        graph.get(4).add(new Edge(5, 3));
        int source = 0, destination = 5;
        Arrays.fill(dp, Double.NEGATIVE_INFINITY);
        Arrays.fill(parent, -1);
        double longestPath = dfs(source, destination);
        System.out.println("Length of Longest simple path from " + source + " to " + destination + " is: " + longestPath);
        System.out.println("Actual path: " + getPath(destination));
    }

    public static double dfs(int u, int t) {
        if (u == t) return 0;
        if (dp[u] != Double.NEGATIVE_INFINITY) return dp[u];
        double maxDist = Double.NEGATIVE_INFINITY;
        for (Edge edge : graph.get(u)) {
            double dist = dfs(edge.to, t) + edge.weight;
            if (dist > maxDist) {
                maxDist = dist;
                parent[edge.to] = u;
            }
        }
        dp[u] = maxDist;
        return maxDist;
    }

    public static List<Integer> getPath(int t) {
        List<Integer> path = new ArrayList<>();
        for (int v = t; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }

    public static class Edge {
        int to;
        double weight;

        Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }

}
