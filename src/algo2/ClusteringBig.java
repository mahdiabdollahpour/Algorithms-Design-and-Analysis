package algo2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by ASUS on 28/01/2018.
 */
public class ClusteringBig {


    //    static class Node {
//        int num;
//        String bin;
//
//        public Node(int num, String bin) {
//            this.num = num;
//            this.bin = bin;
//        }
//    }
    public static void main(String[] args) {

        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(new File("clustering_big.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = null;
        try {
            s = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] tok = s.split(" ");
        int n = Integer.parseInt(tok[0]);
        int r = Integer.parseInt(tok[1]);
        ArrayList<String> nodes = null;
        HashSet<Integer> nodesIntVal = null;
        try {
            nodes = new ArrayList<>(n);
            nodesIntVal = new HashSet<>(n);
            for (int i = 0; i < n; i++) {
                s = bf.readLine();
                s = s.replaceAll("\\s", "");
                int num = Integer.parseInt(s, 2);
                if (nodesIntVal.contains(num)) {
                    //   System.out.println("repeated");
                } else {
                    nodes.add(s);
                    nodesIntVal.add(num);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int maxNode = (int) (Math.pow(2, 24));
        //  System.out.println(maxNode);
        comp = new Obj[maxNode];
        for (int i = 0; i < maxNode; i++) {
            comp[i] = new Obj(i, 0);
        }

        //    System.out.println(n);
        n = nodes.size();
        components = n;
        //   System.out.println(n);
        for (int i = 0; i < nodes.size(); i++) {
            String node = nodes.get(i);
            int num = Integer.parseInt(node, 2);
            ArrayList<Integer> nearBy = nearByNodes(node);


            for (int i1 = 0; i1 < nearBy.size(); i1++) {
                int near = nearBy.get(i1);
                if (nodesIntVal.contains(near)) {
                    if (find(num) != find(near)) {
                        Union(num, near);
                        components--;
                    }
                }
            }
        }
        System.out.println(components);

    }

    static int components;

    private static void nearByRec(ArrayList<Integer> tillNow, String node, String byNow, int howDiff, int i) {
        if (i == 24) {
            if (howDiff != 0) {
                tillNow.add(Integer.parseInt(byNow, 2));
            }
            return;
        }
        if (howDiff >= 2) {
            String pass1 = byNow.concat(String.valueOf(node.charAt(i)));
            nearByRec(tillNow, node, pass1, howDiff, i + 1);
        } else {
            String pass1 = byNow.concat(String.valueOf(node.charAt(i)));
            nearByRec(tillNow, node, pass1, howDiff, i + 1);
            if (node.charAt(i) == '0') {

                String pass2 = byNow.concat("1");
                nearByRec(tillNow, node, pass2, howDiff + 1, i + 1);
            } else {
                String pass2 = byNow.concat("0");
                nearByRec(tillNow, node, pass2, howDiff + 1, i + 1);

            }


        }


    }

    private static ArrayList<Integer> nearByNodes(String node) {

        ArrayList<Integer> nbn = new ArrayList<>();
        nearByRec(nbn, node, "", 0, 0);
        return nbn;
    }

    private static class Obj {
        int parent;
        int rank;

        public Obj(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    private static Obj[] comp;

    static int find(int i) {
        if (comp[i].parent != i) {
            comp[i].parent = find(comp[i].parent);
        }
        return comp[i].parent;
    }

    static void Union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        if (comp[xroot].rank < comp[yroot].rank) {
            comp[xroot].parent = yroot;
        } else if (comp[xroot].rank > comp[yroot].rank) {
            comp[yroot].parent = xroot;
        } else {
            comp[yroot].parent = xroot;
            comp[xroot].rank++;
        }
    }

}
