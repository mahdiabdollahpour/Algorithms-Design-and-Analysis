package algo1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    static class Node implements Comparable<Node> {
        int distance;
        int source;

        public Node() {
        }

        public Node(int distance, int source) {
            this.distance = distance;
            this.source = source;
        }


        @Override
        public int compareTo(Node o) {
            if (distance < o.distance) {
                return 1;
            } else if (distance > o.distance) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 200;
        //  int m = 0;
        ArrayList[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("dijkstraData.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line, str;
        StringTokenizer st1, st2;
        try {
            while ((line = br.readLine()) != null) {
                st1 = new StringTokenizer(line);
                int i = Integer.parseInt(st1.nextToken());
                while (st1.hasMoreTokens()) {
                    str = st1.nextToken();
                    st2 = new StringTokenizer(str, ",");
                    int v = Integer.parseInt(st2.nextToken());
                    int d = Integer.parseInt(st2.nextToken());
                    graph[i - 1].add(new Edge(v - 1, d));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (int i = 0; i < 200; i++) {
//
//            //  int a = in.nextInt();
//            String s = in.nextLine();
//            String[] splited = s.split(",");
//            for (int i1 = 0; i1 < splited.length; i1 += 2) {
//
//                int b = Integer.parseInt(splited[i1]);
//                int w = Integer.parseInt(splited[i1 + 1]);
//                graph[(i + 1) - 1].add(new Edge(b - 1, w));
//            }
//        }
        int[] distances = dijkstra(graph, n);
//        for (int i = 1; i < n; i++) {
//            if (distances[i] == -1) {
//                System.out.print("1000000000");
//            } else {
//                System.out.print(distances[i]);
//            }
//            System.out.println();
//         //   System.out.print(" ");
//        }
        System.out.println(distances[7 - 1]);
        System.out.println(distances[37 - 1]);
        System.out.println(distances[59 - 1]);
        System.out.println(distances[82 - 1]);
        System.out.println(distances[99 - 1]);
        System.out.println(distances[115 - 1]);
        System.out.println(distances[133 - 1]);
        System.out.println(distances[165 - 1]);
        System.out.println(distances[188 - 1]);
        System.out.println(distances[197 - 1]);
        // 7,37,59,82,99,115,133,165,188,197


    }

    public static int[] dijkstra(ArrayList<Edge>[] graph, int n) {
        int[] distances = new int[n];
        distances[0] = 0;
        boolean[] marked = new boolean[n];
        marked[0] = false;
        for (int i = 1; i < n; i++) {
            distances[i] = 1000000;
            marked[i] = false;
        }
        Queue<Node> q = new PriorityQueue<>();

        q.add(new Node(0, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();//retrive and remove
            //      if (!marked[node.source]) {
            //        marked[node.source] = true;
            ArrayList<Edge> edges = graph[node.source];
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);

                int edgeDestinationDistance = distances[edge.destination];
                int newDistance = edge.weight + node.distance;
                if (edgeDestinationDistance > newDistance) {
                    q.add(new Node(newDistance, edge.destination));
                    distances[edge.destination] = newDistance;
                }

            }

            //  }

        }
        return distances;

    }


}
