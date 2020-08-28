package collinear;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.IllegalArgumentException("null argument to constructor");
        checkNullEntries(points);
        java.util.ArrayList<LineSegment> segmentsList = new java.util.ArrayList<>();
        Point[] pointsCopy = java.util.Arrays.copyOf(points, points.length);
        java.util.Arrays.sort(pointsCopy);
        checkDuplicatedEntries(pointsCopy);
        for (int i = 0; i < (pointsCopy.length - 3); ++i)
            for (int j = i + 1; j < (pointsCopy.length - 2); ++j)
                for (int k = j + 1; k < (pointsCopy.length - 1); ++k)
                    for (int m = k + 1; m < (pointsCopy.length); ++m) {
                        if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[m]) &&
                                pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[k])) {
                            LineSegment tempLineSegment = new LineSegment(pointsCopy[i], pointsCopy[m]);
                            if (!segmentsList.contains(tempLineSegment))
                                segmentsList.add(tempLineSegment);
                        }
                    }
        segments = segmentsList.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicated entries in given points");
            }
        }
    }

    private void checkNullEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("One of the point in points array is null");
            }
        }
    }
}