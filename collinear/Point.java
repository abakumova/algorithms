package collinear;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (that.x - this.x == 0 && that.y - this.y == 0) { // Points are equal
            return Double.NEGATIVE_INFINITY;
        } else if (that.x - this.x == 0) { // Verticle line
            return Double.POSITIVE_INFINITY;
        } else if (that.y - this.y == 0) { // Horizontal line
            return +0.0;
        }

        return (double) (that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) {
        // If points are equal
        if (this.y == that.y && this.x == that.x) {
            return 0;
        }

        // If this < that
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        }

        // if this > that
        return 1;
    }

    private class SlopeOrder implements Comparator<Point> {

        Point outer = Point.this;

        @Override
        public int compare(Point t, Point t1) {
            if (outer.slopeTo(t) < outer.slopeTo(t1)) {
                return -1;
            } else if (outer.slopeTo(t) > outer.slopeTo(t1)) {
                return 1;
            }

            return 0;
        }
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
         //Test compareTo method
        Point p = new Point(4, 4);
        Point p1 = new Point(5, 5);
        assert (p.compareTo(p1) == -1);
        assert (p1.compareTo(p1) == 0);
        assert (p1.compareTo(p) == 1);

        // Test slopeTo method
        Point p2 = new Point(4, 5);
        Point p3 = new Point(3, 4);
        assert (p.slopeTo(p2) == Double.POSITIVE_INFINITY);
        assert (p.slopeTo(p3) == +0.0);
        assert (p.slopeTo(p) == Double.NEGATIVE_INFINITY);
        assert (p.slopeTo(p1) == 1.0);

        // Test comparator
        assert (p.slopeOrder().compare(p2, p3) == 1);
        assert (p1.slopeOrder().compare(p, p3) == 1);
        assert (p1.slopeOrder().compare(p3, p) == -1);
        assert (p1.slopeOrder().compare(p3, p3) == 0);
    }
}
