package algo2;

import java.io.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by ASUS on 28/01/2018.
 */
public class Clustering {

    static class Edge {
        int n1;
        int n2;
        int c;

        public Edge(int n1, int n2, int c) {
            this.n1 = n1;
            this.n2 = n2;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "n1=" + n1 +
                    ", n2=" + n2 +
                    ", c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) {
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(new File("clustering1.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = 0;

        String s = null;
        ArrayList<Edge> edges = null;
        try {
            s = bf.readLine();
            n = Integer.parseInt(s);
            int m = n * (n - 1) / 2;
            edges = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                s = bf.readLine();
                String[] tok = s.split(" ");
                int n1 = Integer.parseInt(tok[0]);
                int n2 = Integer.parseInt(tok[1]);
                int c = Integer.parseInt(tok[2]);
                edges.add(new Edge(n1 - 1, n2 - 1, c));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        k = n;
        subsets = new Obj[n];
        for (int i = 0; i < n; i++) {
            subsets[i] = new Obj(i, 0);
        }
        edges.sort(Comparator.comparing(o -> new Integer(o.c)));
//        for (int i = 0; i < edges.size(); i++) {
//            System.out.println(edges.get(i));
//        }
        while (k > 4) {
            Edge e = edges.remove(0);
            int l1 = find(e.n1);
            int l2 = find(e.n2);
            if (l1 != l2) {
                Union(e.n1, e.n2);
                k--;
            }
        }
        int maxSpace = 10000000;
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            if (find(e.n1) != find(e.n2)) {
                if (e.c < maxSpace) {
                    maxSpace = e.c;
                }
            }
        }
        System.out.println(maxSpace);

    }

    static int k;

    static class Obj {
        int parent;
        int rank;

        public Obj(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    static Obj[] subsets;

    static int find(int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets[i].parent);
        }
        return subsets[i].parent;
    }

    static void Union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }


}
