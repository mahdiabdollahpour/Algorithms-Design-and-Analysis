import org.omg.CORBA.MARSHAL;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by ASUS on 21/09/2017.
 */
public class ClosestPairs {
    public static ArrayList<Point> pointsSortedByY = null;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (true) {
            int n = in.nextInt();
            if (n == 0) {
                break;
            }
            ArrayList<Point> points = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                Point p = new Point(x, y);
                points.add(p);
            }
            points.sort(new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return new Integer(o1.x).compareTo(new Integer(o2.x));
                }
            });

            ArrayList<Point> pointsSortedY = (ArrayList<Point>) points.clone();
            pointsSortedY.sort(new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return new Integer(o1.y).compareTo(new Integer(o2.y));
                }
            });
            pointsSortedByY = pointsSortedY;
            double minDistance = closestPair(points, 0, points.size() - 1);
            if (minDistance < 10000) {
                System.out.println(minDistance);
            } else {
                System.out.println("INFINITY");
            }
        }
    }

    public static double calcDistance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static double findSplitPair(ArrayList<Point> points, int firstIndex, int lastIndex, int XMedian, double landa) {
        //  ArrayList<Integer> leftIndexes = new ArrayList<>();
        //   ArrayList<Integer> rightIndexes = new ArrayList<>();
        ArrayList<Point> sy = new ArrayList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            Point p = points.get(i);
            int distance = (int) (p.getX() - XMedian);
            if (Math.abs(distance) < landa) {
                if (distance < 0) {
                    //             leftIndexes.add(i);
                    sy.add(p);
                } else {
                    //           rightIndexes.add(i);
                    sy.add(p);
                }
            }
        }
        double best = landa;

        ArrayList<Point> sySortedByY = new ArrayList<>();
        for (int i = 0; i < pointsSortedByY.size(); i++) {
            if (!sy.contains(pointsSortedByY.get(i))) {
                continue;
            }
            sySortedByY.add(pointsSortedByY.get(i));
        }

        for (int i = 0; i < sySortedByY.size(); i++) {
            for (int j = i + 1; j <= i + 7 && j < sySortedByY.size(); j++) {
                double distance = calcDistance(sySortedByY.get(i), sySortedByY.get(j));
                if (distance < best) {
                    best = distance;
                }
            }
        }
        return best;

    }

    public static double closestPair(ArrayList<Point> points, int firstIndex, int lastIndex) {
        if (lastIndex - firstIndex + 1 == 2) {
            return calcDistance(points.get(firstIndex), points.get(lastIndex));
        }
        if (lastIndex - firstIndex + 1 == 3) {
            double d1 = calcDistance(points.get(firstIndex), points.get(firstIndex + 1));
            double d2 = calcDistance(points.get(firstIndex), points.get(lastIndex));
            double d3 = calcDistance(points.get(firstIndex + 1), points.get(lastIndex));
            return Math.min(d3, Math.min(d1, d2));
        }
        int XMedian = (int) points.get(lastIndex / 2).getX();
        double landa1 = closestPair(points, firstIndex, lastIndex / 2);
        double landa2 = closestPair(points, (lastIndex / 2) + 1, lastIndex);
        double landa = Math.min(landa1, landa2);
        return findSplitPair(points, firstIndex, lastIndex, XMedian, landa);

    }
}
