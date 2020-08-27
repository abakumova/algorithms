package collinear;

public class FastCollinearPoints {

    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        checkForNullPoints(points);
        Point[] pointsCopySO = java.util.Arrays.copyOf(points, points.length);
        Point[] pointsCopyNO = java.util.Arrays.copyOf(points, points.length);
        java.util.ArrayList<LineSegment> segmentsList = new java.util.ArrayList<>();
        java.util.Arrays.sort(pointsCopyNO);
        checkForDuplicatedPoints(pointsCopyNO);
        for (Point origin : pointsCopyNO) {
            java.util.Arrays.sort(pointsCopySO);
            java.util.Arrays.sort(pointsCopySO, origin.slopeOrder());
            int count = 1;
            Point lineBeginning = null;
            for (int j = 0; j < pointsCopySO.length - 1; ++j) {
                if (pointsCopySO[j].slopeTo(origin) == pointsCopySO[j + 1].slopeTo(origin)) {
                    count++;
                    if (count == 2) {
                        lineBeginning = pointsCopySO[j];
                        count++;
                    } else if (count >= 4 && j + 1 == pointsCopySO.length - 1) {
                        if (lineBeginning.compareTo(origin) > 0) {
                            segmentsList.add(new LineSegment(origin, pointsCopySO[j + 1]));
                        }
                        count = 1;
                    }
                } else if (count >= 4) {
                    if (lineBeginning.compareTo(origin) > 0) {
                        segmentsList.add(new LineSegment(origin, pointsCopySO[j]));
                    }
                    count = 1;
                } else {
                    count = 1;
                }
            }

        }
        segments = segmentsList.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return java.util.Arrays.copyOf(segments, numberOfSegments());
    }

    private void checkForDuplicatedPoints(Point[] points) {
        for (int i = 0; i < points.length - 1; ++i) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException("Duplicated points");
            }
        }
    }

    private void checkForNullPoints(Point[] points) {
        for (Point point : points) {
            if (point == null) {
                throw new NullPointerException("At least one point in array is null");
            }
        }
    }
}