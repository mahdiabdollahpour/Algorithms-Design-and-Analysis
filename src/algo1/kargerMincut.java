package algo1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

/**
 * Created by ASUS on 25/01/2018.
 */
public class kargerMincut {
    static final int n = 200;
    static obj[] leaders = new obj[200];

    static class obj {
        int rank;
        int parent;

        public obj(int rank, int parent) {
            this.rank = rank;
            this.parent = parent;
        }
    }

    static class Edge {
        int v1;
        int v2;

        public Edge(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    static ArrayList<Edge> edges;

    public static void main(String[] args) {

    }

    public static int run() {
        //   ArrayList<Integer>[] graph = new ArrayList[n];
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(new File("kargerMinCut.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < leaders.length; i++) {
            leaders[i] = new obj(0, i);
            //  graph[i] = new ArrayList<>();
        }
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String in = null;
            try {
                in = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] ins = in.split("\t");
            for (int i1 = 1; i1 < ins.length; i1++) {
                //  System.out.println("hi");
                int v = Integer.parseInt(ins[i1]) - 1;
                if (i > v) {
                    //    System.out.println("adding");
                    edges.add(new Edge(i, v));
                }
                //    graph[i].add(v);
            }

        }
        return algo();


    }

    static int find(int i) {
        // find root and make root as parent of i (path compression)
        if (leaders[i].parent != i)
            leaders[i].parent = find(leaders[i].parent);

        return leaders[i].parent;
    }

// (uses union by rank)
    static void union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);

        // Attach smaller rank tree under root of high rank tree
        // (Union by Rank)
        if (leaders[xroot].rank < leaders[yroot].rank) {
            leaders[xroot].parent = yroot;
        } else if (leaders[xroot].rank > leaders[yroot].rank) {
            leaders[yroot].parent = xroot;
        }

        // If ranks are same, then make one as root and increment
        // its rank by one
        else {
            leaders[yroot].parent = xroot;
            leaders[xroot].rank++;
        }
    }

    static int vertices = 200;

    public static int algo() {
        vertices = 200;
        Random rand = new Random(System.currentTimeMillis());
        while (vertices > 2) {

            // int randomNumber = (int) (Math.random() * (edges.size() - 1));
            int randomNumber = Math.abs(rand.nextInt()) % edges.size();
            //int randomNumber = 0;
            int n1 = edges.size();
            Edge e = edges.remove(randomNumber);
            int n2 = edges.size();
            if (n1 <= n2) {
                System.out.println("error");
                System.exit(0);
            }
            // edges.remove(randomNumber);
            if (find(e.v1) == find(e.v2)) {
                continue;
            }
            union(e.v1, e.v2);
            vertices--;
        }
        int cuttingEdge = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (find(edge.v2) != find(edge.v1)) {
                cuttingEdge++;
            }
        }
        System.out.println(cuttingEdge);
        return cuttingEdge;
    }


}
