import java.util.*;

class Router {
    private final int id;
    private final Map<Integer, Integer> distanceTable;

    public Router(int id, int totalRouters) {
        this.id = id;
        distanceTable = new HashMap<>();
        for (int i = 0; i < totalRouters; i++) {
            distanceTable.put(i, (i == id) ? 0 : Integer.MAX_VALUE); // Initialize distances
        }
    }

    public void updateDistance(int neighbor, int cost) {
        distanceTable.put(neighbor, cost);
    }

    public void applyBellmanFord(List<Router> routers) {
        for (Router router : routers) {
            for (Map.Entry<Integer, Integer> entry : router.distanceTable.entrySet()) {
                int neighbor = entry.getKey();
                int cost = entry.getValue();
                if (cost != Integer.MAX_VALUE && distanceTable.containsKey(router.id)) {
                    int newCost = distanceTable.get(router.id) + cost;
                    if (newCost < distanceTable.get(neighbor)) {
                        distanceTable.put(neighbor, newCost);
                    }
                }
            }
        }
    }

    public void displayRoutingTable() {
        System.out.println("Routing Table for Router " + id);
        System.out.println("---------------------------");
        for (Map.Entry<Integer, Integer> entry : distanceTable.entrySet()) {
            System.out.println("Destination: " + entry.getKey() + " | Cost: " + entry.getValue());
        }
        System.out.println();
    }
}

public class DistanceVectorRouting {
    public static void main(String[] args) {
        int totalRouters = 3;
        List<Router> routers = new ArrayList<>();

        // Initialize routers
        for (int i = 0; i < totalRouters; i++) {
            routers.add(new Router(i, totalRouters));
        }

        // Define direct connection costs
        routers.get(0).updateDistance(1, 2);
        routers.get(1).updateDistance(0, 2);
        routers.get(1).updateDistance(2, 3);
        routers.get(2).updateDistance(1, 3);

        // Apply Distance Vector Routing (Bellman-Ford algorithm)
        for (Router router : routers) {
            router.applyBellmanFord(routers);
        }

        // Display routing tables
        for (Router router : routers) {
            router.displayRoutingTable();
        }
    }
}
