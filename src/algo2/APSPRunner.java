package algo2;

/**
 * Created by ASUS on 28/01/2018.
 */
public class APSPRunner {
    public static void main(String[] args) {
        System.out.println("test1");
        FloydWarShall.APSP("g1.txt");
        System.out.println("test2");
        FloydWarShall.APSP("g2.txt");
        System.out.println("test3");
        FloydWarShall.APSP("g3.txt");
    }
}
