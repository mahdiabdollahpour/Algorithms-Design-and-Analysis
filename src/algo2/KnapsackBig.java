package algo2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ASUS on 29/01/2018.
 */
public class KnapsackBig {

    private static class KObj {

        int w;
        int v;

        public KObj(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    private static ArrayList<KObj> objects = new ArrayList<>();

    public static void main(String[] args) {
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(new File("knapsack_big.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = null;
        int size = 0;
        int n = 0;
        try {
            s = bf.readLine();
            String[] tok = s.split(" ");
            size = Integer.parseInt(tok[0]);
            n = Integer.parseInt(tok[1]);
            for (int i = 0; i < n; i++) {
                s = bf.readLine();
                tok = s.split(" ");
                int v = Integer.parseInt(tok[0]);
                int w = Integer.parseInt(tok[1]);
                objects.add(new KObj(w, v));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(knapsack(n - 1, size));
    }

    private static HashMap<String, Integer> cache = new HashMap<>();

    private static int knapsack(int i, int x) {
        if (i < 0) {
            return 0;
        }
        KObj obj = objects.get(i);
        int vi = obj.v;
        int wi = obj.w;

        String key = i + "," + x;

        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            if (x > wi) {
                int ans = Math.max(knapsack(i - 1, x), knapsack(i - 1, x - wi) + vi);
                cache.put(key, ans);
                return ans;
            } else {
                int ans = knapsack(i - 1, x);
                cache.put(key, ans);
                return ans;

            }
        }
    }

}

