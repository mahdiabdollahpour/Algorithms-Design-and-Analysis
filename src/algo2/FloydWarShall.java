package algo2;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by ASUS on 28/01/2018.
 */
public class FloydWarShall {


    final static int INF = 99999;

    public static void APSP(String address) {
        BufferedReader bf = null;
        try {

            bf = new BufferedReader(new FileReader(new File(address)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = null;
        int n = 0;

        //       ArrayList<Edge>[] adj = null;
        int[][] adj = null;
        try {
            s = bf.readLine();

            String[] tok = s.split(" ");
            n = Integer.parseInt(tok[0]);
            int m = Integer.parseInt(tok[1]);

            //      adj = new ArrayList[n];
            adj = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        adj[i][j] = 0;
                    } else {
                        adj[i][j] = INF;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                s = bf.readLine();
                tok = s.split(" ");
                int a = Integer.parseInt(tok[0]);
                int b = Integer.parseInt(tok[1]);
                int c = Integer.parseInt(tok[2]);
                //   adj[a - 1].add(new Edge(b - 1, c));
                adj[a - 1][b - 1] = c;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        int[][] dist = new int[n][n];
        int i, j, k;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {

                dist[i][j] = adj[i][j];
            }
        }

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        //  pre = a;
        //    a = new int[n][n];


        int min = INF;
        for (int l = 0; l < n; l++) {
            for (int m = 0; m < n; m++) {
                int now = dist[l][m];
                if (l == m) {
                    if (dist[l][m] < 0) {
                        System.out.println("negetive cycle");
                        return;
                    }
                }
                if (now < min) {
                    min = now;
                }
            }
        }
        System.out.println(min);
    }

}
