import java.util.*;

class Graph {
    private final int nodes;
    private final int[][] adjacencyMatrix;

    public Graph(int nodes) {
        this.nodes = nodes;
        adjacencyMatrix = new int[nodes][nodes];
        for (int[] row : adjacencyMatrix) Arrays.fill(row, Integer.MAX_VALUE); // Initialize with infinity
    }

    public void addEdge(int src, int dest, int cost) {
        adjacencyMatrix[src][dest] = cost;
        adjacencyMatrix[dest][src] = cost; // Undirected graph
    }

    public void dijkstra(int source) {
        int[] distance = new int[nodes];
        boolean[] visited = new boolean[nodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int i = 0; i < nodes - 1; i++) {
            int u = minDistance(distance, visited);
            visited[u] = true;

            for (int v = 0; v < nodes; v++) {
                if (!visited[v] && adjacencyMatrix[u][v] != Integer.MAX_VALUE &&
                    distance[u] + adjacencyMatrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + adjacencyMatrix[u][v];
                }
            }
        }

        displayRoutingTable(source, distance);
    }

    private int minDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < nodes; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void displayRoutingTable(int source, int[] distance) {
        System.out.println("Routing Table for Node " + source);
        System.out.println("------------------------------");
        for (int i = 0; i < nodes; i++) {
            System.out.println("Destination: " + i + " | Cost: " + distance[i]);
        }
        System.out.println();
    }
}

public class LinkStateRouting {
    public static void main(String[] args) {
        Graph graph = new Graph(4);

        // Add edges with costs
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 3, 3);

        // Compute shortest paths for each node
        for (int i = 0; i < 4; i++) {
            graph.dijkstra(i);
        }
    }
}
