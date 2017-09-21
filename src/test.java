import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by ASUS on 21/09/2017.
 */
public class test {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(7);
        a.add(4);
        a.add(2);
        a.add(3);
        a.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(a);
    }
}
