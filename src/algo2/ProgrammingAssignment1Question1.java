package algo2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by ASUS on 28/01/2018.
 */
public class ProgrammingAssignment1Question1 {
    static class Job {
        int w;
        int l;

        @Override
        public String toString() {
            return "Job{" +
                    "w=" + w +
                    ", l=" + l +
                    '}';
        }

        public Job(int w, int l) {
            this.w = w;
            this.l = l;
        }
    }

    public static void main(String[] args) {
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(new File("jobs.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Job> jobs = null;
        try {
            int n = 0;
            String s = bf.readLine();
            n = Integer.parseInt(s);
            System.out.println(n);
            ;
            jobs = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                s = bf.readLine();
                String[] tok = s.split(" ");
                int w = Integer.parseInt(tok[0]);
                int l = Integer.parseInt(tok[1]);
                // int l = bf.read();
                jobs.add(new Job(w, l));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        jobs.sort(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                int s1 = o1.w - o1.l;
                int s2 = o2.w - o2.l;
                if (s1 > s2) {
                    return -1;
                } else if (s1 == s2) {
                    if (o1.w > o2.w) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;

                }

            }
        });
        long c = 0;
        long wct = 0;
        System.out.println(jobs);
        for (int i = 0; i < jobs.size(); i++) {
            c += (long) jobs.get(i).l;
            wct += c * (long) jobs.get(i).w;
        }
        System.out.println(wct);
    }

}
