package company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import leetcode.math.Point;

/*
 * 
 */
public class NearestPoint {
    
    public static List<Point> findPoints(int k, final Point center, Point[] points) {
        /*
        PriorityQueue<Point> pq = new PriorityQueue<>(k+1, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double d = getDistance(o1, center) - getDistance(o2, center);
                if (d == 0) return 0;
                else if (d > 0) return -1;
                else return 1;
            }    
        });*/
        PriorityQueue<Point> pq = new PriorityQueue<>(k+1, (a, b)-> {
            return Double.compare(getDistance(b, center), getDistance(a, center));
        });
        
        for (Point point : points) {
            //System.out.println("p=" + point + " dist=" + getDistance(point, center));
            pq.offer(point);
            if (pq.size() > k) pq.poll();
        }
        
        LinkedList<Point> result = new LinkedList<>();
        while (!pq.isEmpty()) result.addFirst(pq.poll());
        return result;
    }

    private static double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
    }
    
    public static void main(String[] args) {
        Point[] pp = new Point[10];
        for (int i = 10; i >= 1; i--) {
            pp[i - 1] = new Point(i, i);
        }
        Point center = new Point(0, 0);
        System.out.println(findPoints(5, center, pp));
    }
}
