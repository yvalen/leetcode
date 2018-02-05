package leetcode.math;

public class Point {
    public int x;
    public int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int a, int b) {
        this.x = a;
        this.y = b;
    }
    
    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
}
