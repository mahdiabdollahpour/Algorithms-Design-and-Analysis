package algo2;

import java.io.*;

/**
 * Created by ASUS on 28/01/2018.
 */
public class Knapsack {

    public static void main(String[] args) {
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(new File("knapsack1.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = null;
        int size = 0;
        int n = 0;
        int[] v = null;
        int[] w = null;
        try {
            s = bf.readLine();
            String[] tok = s.split(" ");
            size = Integer.parseInt(tok[0]);
            n = Integer.parseInt(tok[1]);
            v = new int[n];
            w = new int[n];

            for (int i = 0; i < n; i++) {
                s = bf.readLine();
                tok = s.split(" ");
                v[i] = Integer.parseInt(tok[0]);
                w[i] = Integer.parseInt(tok[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[][] a = new int[n][size];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < size; j++) {
                int max;
                if (j - w[i] >= 0) {
                    max = Math.max(a[i - 1][j], a[i - 1][j - w[i]] + v[i]);
                } else {
                    max = a[i - 1][j];
                }
                a[i][j] = max;
            }
        }
        System.out.println(a[n - 1][size - 1]);


    }


}
