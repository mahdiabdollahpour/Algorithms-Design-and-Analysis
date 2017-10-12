package algo2;

import java.awt.*;
import java.util.*;

/**
 * Created by ASUS on 12/10/2017.
 */
public class PrimeMST {
    static class PII {
        long cost;
        int vertic;

        public PII(long cost, int vertic) {
            this.cost = cost;
            this.vertic = vertic;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//vertices
        int m = in.nextInt();//edges
        ArrayList<PII>[] graph = new ArrayList[n];
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            marked[i] = false;
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            long cost = in.nextLong();
            graph[x - 1].add(new PII(cost, y - 1));
            graph[y - 1].add(new PII(cost, x - 1));
        }
        System.out.println(prim(graph, marked));


    }

    public static long prim(ArrayList<PII>[] graph, boolean[] marked) {
        Queue<PII> heap = new PriorityQueue(Comparator.comparing(o -> (((PII) o).cost)));
        PII p = new PII(0, 0);
        heap.add(p);
        long minCost = 0;
        while (!heap.isEmpty()) {
            PII polled = heap.poll();
            int x = polled.vertic;
            if (marked[x]) {
                continue;
            } else {
                minCost += polled.cost;
                marked[x] = true;
                for (int i = 0; i < graph[x].size(); i++) {
                    int y = graph[x].get(i).vertic;
                    if (!marked[y]) {
                        heap.add(graph[x].get(i));
                    }
                }
            }

        }
        return minCost;

    }


}
