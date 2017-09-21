import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by ASUS on 16/09/2017.
 */
public class CountInversions {
    static int arr[];

    static int n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int j = 0; j < t; j++) {

            n = in.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(countInv(arr, 0, n - 1));
        }

    }

    public static BigInteger countInv(int[] arr, int startIndex, int endIndex) {
        if (endIndex - startIndex < 1) {
            return new BigInteger(String.valueOf(0));
        } else {
            int mid = (startIndex + endIndex) / 2;
            BigInteger count = new BigInteger(String.valueOf(0));
            count = count.add(countInv(arr, startIndex, mid));
            count = count.add(countInv(arr, mid + 1, endIndex));
            count = count.add(new BigInteger(String.valueOf(countSplitInv(arr, startIndex, mid, mid + 1, endIndex))));
            return count;
        }
    }

    public static int countSplitInv(int[] arr, int st1, int end1, int st2, int end2) {
        int[] d = new int[end2 - st1 + 1];
        int splitInv = 0;
        int i = st1;
        int j = st2;
        int k = 0;
        while (i <= end1 && j <= end2) {
            if (arr[i] < arr[j]) {
                d[k++] = arr[i++];
            } else if (arr[j] < arr[i]) {
                d[k++] = arr[j++];
                splitInv += end1 - i + 1;
            }
        }

        while (i <= end1) {
            d[k++] = arr[i++];
        }
        while (j <= end2) {
            d[k++] = arr[j++];
        }
        int l = 0;
        while (l < d.length) {
            arr[st1 + l] = d[l];
            l++;
        }

        return splitInv;

    }


}
