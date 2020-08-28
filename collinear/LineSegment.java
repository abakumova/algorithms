package collinear;

public class LineSegment {

    public final Point p;   // one endpoint of this line segment
    public final Point q;   // the other endpoint of this line segment

    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.q = q;
    }

    public void draw() {
        p.drawTo(q);
    }

    public String toString() {
        return p + " -> " + q;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }
}
