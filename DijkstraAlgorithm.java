// DijkstraAlgorithm.java
// Handles Dijkstra's algorithm part of PA5

import java.util.*;

public class DijkstraAlgorithm {
    static final int INF = Integer.MAX_VALUE;

    // Provided matrix (hardcoded)
    static int[][] graph = {
            {0, 54, 11, 13, 0, 0, 0, 0, 0, 0},
            {54, 0, 37, 0, 3, 0, 102, 0, 0, 0},
            {11, 37, 0, 10, 36, 19, 0, 0, 0, 0},
            {13, 0, 10, 0, 0, 18, 0, 0, 7, 0},
            {0, 3, 36, 0, 0, 15, 124, 123, 0, 0},
            {0, 0, 19, 18, 15, 0, 0, 138, 8, 0},
            {0, 102, 0, 0, 124, 0, 0, 9, 0, 72},
            {0, 0, 0, 0, 123, 138, 9, 0, 146, 67},
            {0, 0, 0, 7, 0, 8, 0, 146, 0, 213},
            {0, 0, 0, 0, 0, 0, 72, 67, 213, 0}
    };

    static String[] labels = {"A", "J", "M", "R", "K", "S", "I", "N", "T", "D"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter start node (letter A-J): ");
        String startNode = scanner.nextLine().toUpperCase();
        System.out.print("Enter destination node (letter A-J): ");
        String endNode = scanner.nextLine().toUpperCase();

        int startIdx = findIndex(startNode);
        int endIdx = findIndex(endNode);

        if (startIdx == -1 || endIdx == -1) {
            System.out.println("Invalid node input.");
            return;
        }

        dijkstra(startIdx, endIdx);
    }

    static int findIndex(String node) {
        for (int i = 0; i < labels.length; i++) {
            if (labels[i].equals(node)) {
                return i;
            }
        }
        return -1;
    }

    static void dijkstra(int start, int end) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];

        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        System.out.println("Shortest distance from " + labels[start] + " to " + labels[end] + ": " + dist[end]);

        List<String> path = new ArrayList<>();
        int crawl = end;
        while (crawl != -1) {
            path.add(labels[crawl]);
            crawl = prev[crawl];
        }

        Collections.reverse(path);
        System.out.println("Path: " + String.join(" -> ", path));
    }

    static int minDistance(int[] dist, boolean[] visited) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}
