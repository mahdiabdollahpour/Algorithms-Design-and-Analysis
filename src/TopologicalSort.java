import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by ASUS on 22/09/2017.
 */
public class TopologicalSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            graph[x - 1].add(y - 1);

        }
        Comparator<Integer> c = (o1, o2) -> o2.compareTo(o1);
        for (int i = 0; i < n; i++) {
            graph[i].sort(c);
        }
        int[] sorted = topSort(graph, n);
        for (int i = 0; i < sorted.length; i++) {
            System.out.print((sorted[i] + 1) + " ");
        }
    }

    public static int[] topSort(ArrayList<Integer>[] graph, int n) {
        int label = n - 1;
        int[] sorted = new int[n];
        while (label >= 0) {
            int start = 0;
            for (int i = n - 1; i > -0; i--) {
                if (graph[i] != null) {
                    start = i;
                    break;
                }
            }
            //   System.out.println("choosed a start V");
            int toRemove = dfs(start, graph, label, sorted);
            graph[toRemove] = null;
            label--;
        }
        return sorted;
    }

    public static int dfs(int startVertex, ArrayList<Integer>[] graph, int label, int[] sorted) {
        ArrayList<Integer>[] graphToPass = graph.clone();
        graphToPass[startVertex] = null;
        boolean flag = true;
        for (int i = 0; i < graph[startVertex].size(); i++) {
            if (graph[startVertex] != null) {
                if (graph[graph[startVertex].get(i)] != null) {
                    flag = false;
                    int res = dfs(graph[startVertex].get(i), graphToPass, label, sorted);
                    if (res != -1) {
                        return res;
                    }
                }
            }
        }
        if (flag) {
            sorted[label] = startVertex;
            // System.out.println(startVertex + " in place of " + label);
            return startVertex;
        }

        return -1;
    }


}
