package algo1;

/**
 * Created by ASUS on 21/09/2017.
 */
public class KargerMinCutRunner {
    static int min = 1000000;

    public static void main(String[] args) {
        for (int i = 0; i < 40000 * 6; i++) {
            int a = kargerMincut.run();
            if (a < min) {
                min = a;
            }
        }
        System.out.println("min : " + min);
    }
}
