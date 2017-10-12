package algo1;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by ASUS on 21/09/2017.
 */
public class IthOrderStatisticRandomized {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int order = in.nextInt();
        System.out.println(ithOrderStatistic(arr, 0, n - 1, order - 1));
    }

    public static void swap(int[] arr, int i, int j) {
        System.out.println("swaping " + i + " and " + j + " with values" + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int ithOrderStatistic(int[] arr, int firstIndex, int lastIndex, int orderIndex) {
        if (lastIndex - firstIndex + 1 == 1) {
            System.out.println("returned " + arr[firstIndex] + " as the number in: " + firstIndex);
            return arr[firstIndex];
        }
        if (lastIndex - firstIndex + 1 == 2) {
            if (arr[firstIndex] > arr[lastIndex]) {
                swap(arr, firstIndex, lastIndex);
            }
            System.out.println("returned " + arr[orderIndex] + " as the number in: " + orderIndex);
            return arr[firstIndex + orderIndex];
        }
        Random random = new Random();
        int pivotIndex = random.nextInt(lastIndex - firstIndex) + firstIndex;
        int pivot = arr[pivotIndex];//gonna choose randomly
        int i = firstIndex;
        int j = lastIndex;
        // boolean flag = true;
        while (i < j) {
            //       flag = false;
            while (arr[j] >= pivot) {
                j--;
                //        flag = true;
            }
            while (arr[i] <= pivot) {
                i++;
                //      flag = true;
            }
            swap(arr, i, j);

        }
        if (i == j) {

            swap(arr, i, pivotIndex);
        }
        if (i > j) {
            if (pivotIndex > i) {
                swap(arr, pivotIndex, i);
            } else if (pivotIndex < j) {
                swap(arr, pivotIndex, j);
            }
        }

        System.out.println("i is " + i + " j is :" + j);
        System.out.println(arr[pivotIndex] + "at the place of " + pivotIndex);

        if (pivotIndex == firstIndex + orderIndex) {
            System.out.println("just right ->" + pivotIndex);
            return pivot;
        } else if (pivotIndex > firstIndex + orderIndex) {
            return ithOrderStatistic(arr, firstIndex, pivotIndex - 1, orderIndex);
        } else {
            return ithOrderStatistic(arr, pivotIndex + 1, lastIndex, orderIndex - pivotIndex - 1);
        }


    }

}
