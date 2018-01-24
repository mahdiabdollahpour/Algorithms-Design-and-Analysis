package algo1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by ASUS on 03/10/2017.
 */
public class ProgrammingAssignment6Question2 {

    static ArrayList<Integer> numbers = new ArrayList<>(10000);
    //public static BinarySearchTree bst = new BinarySearchTree();

    public static void main(String[] args) {
        int k = 1;
        long sum = 0;
        Scanner in = new Scanner(System.in);
        for (int j = 0; j < 10000; j++) {


            int xk = in.nextInt();

            numbers.add(xk);
            numbers.sort(Comparator.naturalOrder());

            // sum += numbers.get(((numbers.size() + 1) / 2) - 1);
            //System.out.println("k is :" + k);
           // System.out.println("size is :" + numbers.size());
            //  System.out.println("median at" + (((numbers.size() + 1) / 2) - 1));
            //  System.out.println("number is :" + xk);
            if (k % 2 == 1) {
                sum += numbers.get(k / 2);
            } else {
                sum += numbers.get((k - 1) / 2);
            }

            k++;
            sum = sum % 10000;
        }
        System.out.println(numbers);
        System.out.println(sum % 10000);


    }
}
