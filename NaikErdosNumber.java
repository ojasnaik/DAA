package DAAAssignments;

import java.io.*;
import java.util.*;

public class NaikErdosNumber {
    public static Map<Integer, List<Integer>> graph;
    public static Map<Integer, Integer> erdosNumber;
    public static Map<Integer, Set<Integer>> erdosNumber123;

    public static int maxNode;

    public static void main(String[] args) {
        String readFilePath = "C:\\Users\\naiko\\Downloads\\ErdosCA.txt";
        maxNode = readInputFileAndCreateGraph(readFilePath);
        populateErdosNumberForEachNode();
        outputCollaboratorN1N2N3();
        createMaximalIndependentSet(graph);
        String writeFilePath = "C:\\Users\\naiko\\Downloads\\Naik_ErdosNi_Collaborators.txt";
        writeCollaboratorsToAFile(erdosNumber123, writeFilePath);
    }

    private static void outputCollaboratorN1N2N3() {
        System.out.println("Number of authors with erdos number 1 = " + erdosNumber123.get(1).size());
        System.out.println("The authors = " + erdosNumber123.get(1));
        System.out.println("Number of authors with erdos number 2 = " + erdosNumber123.get(2).size());
        System.out.println("The authors = " + erdosNumber123.get(2));
        System.out.println("Number of authors with erdos number 3 = " + erdosNumber123.get(3).size());
        System.out.println("The authors = " + erdosNumber123.get(3));
    }

    private static void writeCollaboratorsToAFile(Map<Integer, Set<Integer>> erdosNumber123, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Integer, Set<Integer>> entry : erdosNumber123.entrySet()) {
                writer.write("N" + entry.getKey().toString() + " = " + entry.getValue().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateErdosNumberForEachNode() {
        erdosNumber = new HashMap<>();
        erdosNumber123 = new HashMap<>(); //to keep track of authors with Erdős number 1, 2 and 3
        erdosNumber123.put(1, new TreeSet<>());
        erdosNumber123.put(2, new TreeSet<>());
        erdosNumber123.put(3, new TreeSet<>());
        boolean[] visited = new boolean[maxNode + 1];
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(1);
        visited[1] = true;
        bfsQueue.add(-1);
        int counter = 0;
        int prev = 0;
        while (!bfsQueue.isEmpty()) {
            int node = bfsQueue.remove();
            if (node != -1) {
                prev = node;
                erdosNumber.put(node, counter);
                if (counter >= 1 && counter <= 3) erdosNumber123.get(counter).add(node);
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        bfsQueue.add(neighbor);
                        visited[neighbor] = true;
                    }
                }

            } else {
                if (prev == -1) break;
                prev = -1;
                bfsQueue.add(-1);
                counter++;
            }
        }
    }

    private static void createMaximalIndependentSet(Map<Integer, List<Integer>> graph) {
        Set<Integer> mis = new TreeSet<>();
        boolean[] visited = new boolean[maxNode + 1];
        for (int key : graph.keySet()) {
            if (!visited[key]) {
                mis.add(key);
                visited[key] = true;
                for (int neighbor : graph.get(key)) {
                    visited[neighbor] = true;
                }
            }
        }
        System.out.println("Size of Maximal Independent Set = " + mis.size());
        System.out.println("Maximal Independent Set = " + mis);
        writeMISToAFile("C:\\Users\\naiko\\Downloads\\Naik_Maximal_Independent_Set.txt", mis);
    }

    private static void writeMISToAFile(String filePath, Set<Integer> mis) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Maximal Independent Set = " + mis.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int readInputFileAndCreateGraph(String fileLocation) {
        graph = new HashMap<>();
        int maxNode = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            String line = br.readLine();
            String[] constraints = line.substring(1).trim().split(" ");
            maxNode = Integer.parseInt(constraints[0]);
            int lines = Integer.parseInt(constraints[2]);
            int numberOfNodes = 0, numberOfEdges = 0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                int vertex1 = Integer.parseInt(split[0]);
                int vertex2 = Integer.parseInt(split[1]);
                if (!graph.containsKey(vertex1)) {
                    graph.put(vertex1, new ArrayList<>());
                    numberOfNodes++;
                }
                if (!graph.containsKey(vertex2)) {
                    graph.put(vertex2, new ArrayList<>());
                    numberOfNodes++;
                }
                graph.get(vertex1).add(vertex2);
                graph.get(vertex2).add(vertex1); //Will be an undirected Graph
                numberOfEdges++;
            }
            System.out.println("Number Of Nodes = " + numberOfNodes);
            System.out.println("Number Of Edges = " + numberOfEdges);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxNode;
    }
}
