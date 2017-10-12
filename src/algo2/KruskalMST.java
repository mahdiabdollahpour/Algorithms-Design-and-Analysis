package algo2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by ASUS on 13/10/2017.
 */
public class KruskalMST {
    public static int[] leader = null;

    public static int getLeader(int x) {
        while (leader[x] != x) {
            leader[x] = leader[leader[x]];
            x = leader[x];
        }
        return x;
    }

    public static void union(int x, int y) {
        int p = getLeader(x);
        int q = getLeader(y);
        leader[p] = leader[q];
    }

    public static long kruskal(ArrayList<Edge> edges) {
        long minCost = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            int v1 = e.v1;
            int v2 = e.v2;
            if (getLeader(v1) != getLeader(v2)) {
                minCost += e.cost;
                union(v1, v2);
            }
        }
        return minCost;


    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//vertices
        int m = in.nextInt();//edges
        ArrayList<Edge> edges = new ArrayList<>(m);
        leader = new int[n];
        for (int i = 0; i < n; i++) {
            leader[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            long cost = in.nextLong();
            edges.add(new Edge(x - 1, y - 1, cost));
        }
        edges.sort((o1, o2) -> new Long(o1.cost).compareTo(o2.cost));
        System.out.println(kruskal(edges));

    }


    private static class Edge {
        int v1;
        int v2;
        long cost;

        public Edge(int v1, int v2, long cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }


    }
}
