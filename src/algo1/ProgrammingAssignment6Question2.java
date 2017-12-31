package algo1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ASUS on 03/10/2017.
 */
public class ProgrammingAssignment6Question2 {
    public static int getMedian() {
        return 0;
    }

    static ArrayList<Integer> numbers = new ArrayList<>(10000);
    //public static BinarySearchTree bst = new BinarySearchTree();

    public static void main(String[] args) {
        int k = 1;
        int sum = 0;
        Scanner in = new Scanner(System.in);
        for (int j = 0; j < 10000; j++) {


            int xk = in.nextInt();
            int index = 0;
            for (int i = 0; i < numbers.size(); i++) {
                if (xk <= numbers.get(i)) {
                    index = i;
                    break;
                }
            }
            numbers.add(index, xk);

            sum += numbers.get(((numbers.size() + 1) / 2) - 1);
            System.out.println("k is :" + k);
            System.out.println("size is :" + numbers.size());
            System.out.println("median at" + (((numbers.size() + 1) / 2) - 1));
            System.out.println("number is :" + xk);
            k++;
            sum = sum % 10000;
        }
        System.out.println(numbers);
        System.out.println(sum % 10000);


    }
}
