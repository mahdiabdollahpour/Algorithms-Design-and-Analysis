package algo1;

import java.util.Scanner;

/**
 * Created by ASUS on 21/09/2017.
 */
public class QuickSort {
    static long comapre = 0;

    static int patritionFirst(int[] arr, int l, int r) {
        int p = arr[l];
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (arr[j] < p) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }

        }
        int t = arr[l];
        arr[l] = arr[i - 1];
        arr[i - 1] = t;
        return i - 1;
    }

    static int patritionMedian3(int[] arr, int l, int r) {
        int a = arr[l];
        int b = arr[r];
        int c = arr[(r + l) / 2];
        if (c < Math.max(a, b) && c > Math.min(a, b)) {
            int t3 = arr[(r + l) / 2];
            arr[(r + l) / 2] = arr[l];
            arr[l] = t3;

        } else if (b < Math.max(a, c) && b > Math.min(a, c)) {
            int t4 = arr[r];
            arr[r] = arr[l];
            arr[l] = t4;

        }


        int p = arr[l];
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (arr[j] < p) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }

        }
        int t = arr[l];
        arr[l] = arr[i - 1];
        arr[i - 1] = t;
        return i - 1;
    }

    static int patritionlast(int[] arr, int l, int r) {
        int t1 = arr[r];
        arr[r] = arr[l];
        arr[l] = t1;

        int p = arr[l];
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (arr[j] < p) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }

        }
        int t = arr[l];
        arr[l] = arr[i - 1];
        arr[i - 1] = t;
        return i - 1;
    }

    static void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = patritionMedian3(arr, l, r);
        comapre += r - l;
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    public static void main(String[] args) {
        int[] arr = new int[10000];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        sort(arr, 0, arr.length - 1);


        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("comparisons :" + comapre);
    }

}
