package algo1;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by ASUS on 29/09/2017.
 */
public class ProgrammingAssignment6Question1 {
    public static void main(String[] args) {
        long[] numbers = new long[1000000];
        HashSet<Long> hashSet = new HashSet<>(1000000);
        Scanner in = new Scanner(System.in);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("algo1-programming_prob-2sum.txt"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1000000; i++) {
            try {
                numbers[i] = Long.parseLong(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            hashSet.add(numbers[i]);
        }
        int count = 0;
        for (int i = -10000; i <= 10000; i++) {
            int t = i;
            for (int i1 = 0; i1 < numbers.length; i1++) {
                if (hashSet.contains(t - numbers[i1])) {
                    if (numbers[i1] != t - numbers[i1]) {
                        count++;
                        break;
                    }
                }
            }
        }
        System.out.println(count);


    }


}

