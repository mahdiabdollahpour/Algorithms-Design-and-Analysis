package algo1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by ASUS on 16/10/2017.
 */
//    for submisson to
//    https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/tutorial/
public class StronglyConnectedComponents {
    static class node {
        //  public int src;
        //  public int dst;
        int leader;
        ArrayList<Integer> edges = new ArrayList<>();
        //int fi;

    }

    static int n = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();//nodes
        int m = in.nextInt();//edges
        node[] graph = new node[n];
        node[] rev = new node[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new node();
            rev[i] = new node();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a - 1].edges.add(b - 1);
            rev[b - 1].edges.add(a - 1);
        }
        //  indexInrev = new int[n];
        components = new int[n];
        dfsLoop(graph);
        dfsRev(rev);

        for (int i = 0; i < rev.length; i++) {
            int l = rev[i].leader;
          //  System.out.println("node : " + (i + 1) + "   leader " + (l + 1));
            components[l]++;
        }
        int odd = 0;
        int even = 0;
        for (int i = 0; i < components.length; i++) {
            if (components[i] != 0) {
                if (components[i] % 2 == 0) {
                    even += components[i];
                } else {
                    odd += components[i];
                }
            }
        }
        System.out.println(odd - even);


    }

    static int[] components;


    static boolean[] explored;
    static boolean[] exploredForRevDfs;

    public static void dfsLoop(node[] graph) {
        int cuurVertex = 0;
        explored = new boolean[graph.length];
        for (int i = 0; i < explored.length; i++) {
            explored[i] = false;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (!explored[i]) {
                cuurVertex = i;
                dfs(graph, i, cuurVertex);
            }
        }


    }


    public static void dfs(node[] graph, int i, int currVertex) {
        explored[i] = true;
        for (int j = 0; j < graph[i].edges.size(); j++) {
            int hasArcTo = graph[i].edges.get(j);
            if (!explored[hasArcTo]) {
                dfs(graph, hasArcTo, currVertex);
            }
        }
        stack.add(i);
    }

    static Stack<Integer> stack = new Stack<>();

    static void findMAtes(int leader, int index, node[] rev) {
        if (exploredForRevDfs[index]) {
            return;
        } else {
            exploredForRevDfs[index] = true;
        }
        rev[index].leader = leader;
        for (int i = 0; i < rev[index].edges.size(); i++) {
            int dist = rev[index].edges.get(i);
            findMAtes(leader, dist, rev);
        }

    }

    static void dfsRev(node[] rev) {
        exploredForRevDfs = new boolean[rev.length];
        for (int i = 0; i < exploredForRevDfs.length; i++) {
            exploredForRevDfs[i] = false;
        }

        while (!stack.empty()) {
            int index = stack.pop();
            findMAtes(index, index, rev);
        }

    }


}
