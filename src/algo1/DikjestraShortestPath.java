package algo1;

import java.util.*;

/**
 * Created by ASUS on 22/09/2017.
 */
public class DikjestraShortestPath {
    static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

    }

    static class Node implements Comparator<Node> {
        int distance;
        int source;

        public Node() {
        }

        public Node(int distance, int source) {
            this.distance = distance;
            this.source = source;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.distance < node2.distance)
                return -1;
            if (node1.distance > node2.distance)
                return 1;
            return 0;
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {

            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            graph[a - 1].add(new Edge(b - 1, w));
        }
        int[] distances = dijkstra(graph, n);
        for (int i = 1; i < n; i++) {
            if (distances[i] == -1) {
                System.out.print("1000000000");
            } else {
                System.out.print(distances[i]);
            }
            System.out.print(" ");
        }


    }

    public static int[] dijkstra(ArrayList<Edge>[] graph, int n) {
        int[] distances = new int[n];
        distances[0] = 0;
        boolean[] marked = new boolean[n];
        marked[0] = false;
        for (int i = 1; i < n; i++) {
            distances[i] = -1;
            marked[i] = false;
        }
        //  Queue<algo1.node> q = new PriorityQueue<>(new algo1.node());
          Queue<Node> q = new PriorityQueue<>(new Node());
   //     HashSet<algo1.node> q = new HashSet<>();

//                new PriorityQueue((o1, o2) -> {
//            algo1.node n1 = (algo1.node) o1;
//            algo1.node n2 = (algo1.node) o2;
//            return new Integer(n2.distance).compareTo(new Integer(n1.distance));
//        });
        q.add(new Node(0, 0));
        while (!q.isEmpty()) {
//            System.out.println("q size " + q.size());
            Node node = q.poll();//retrive and remove
            if (!marked[node.source]) {
                marked[node.source] = true;
                ArrayList<Edge> edges = graph[node.source];
                for (int i = 0; i < edges.size(); i++) {
                    Edge edge = edges.get(i);
                    int edgeDestinationDistance = distances[edge.destination];
                    int newDistance = edge.weight + node.distance;
                    // edgeDestinationDistance == -1  means to be infinte
                    if (edgeDestinationDistance == -1 || edgeDestinationDistance > newDistance) {
                        q.add(new Node(newDistance, edge.destination));
                        distances[edge.destination] = newDistance;
                    }

                }

            }

        }
        return distances;

    }


}
