import java.util.Map;
import java.util.Scanner;

/**
 * Created by ASUS on 21/09/2017.
 */
public class KaratsubaMultiplication {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        System.out.println("from function   :" + multiply(x, y));
        System.out.println("really   :" + x * y);


    }

    public static int multiply(int x, int y) {
        int xlength = (int) (Math.log10(x) + 1);
        int ylength = (int) (Math.log10(x) + 1);
        System.out.println("lenght of " + x + " is " + xlength);
        System.out.println("lenght of " + y + " is " + ylength);
        if (xlength <= 1 || ylength <= 1) {
            int res = x * y;
            System.out.println(x + " * " + y + " is :" + res);
            return res;
        }

        int a = (int) (x / (Math.pow(10, xlength / 2)));
        int b = (int) (x - a * Math.pow(10, xlength / 2));

        int c = (int) (y / (Math.pow(10, ylength / 2)));
        int d = (int) (y - c * Math.pow(10, ylength / 2));
        int z1 = multiply(a, c);
        int z3 = multiply(b, d);
        int z2 = multiply(a + b, c + d) - z1 - z3;
        int res = (int) (Math.pow(10, xlength) * z1 + Math.pow(10, xlength / 2) * z2 + z3);
        System.out.println(x + " * " + y + " is :" + res);
        return res;

    }

}
