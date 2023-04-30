package geometry;

import java.util.List;
import biuoop.DrawSurface;

/**
 * class 'geometry.Line' - in this class there a variables that representing the values of the starting
 * and ending points of the line. in the class there are constructors, setters, getters, method
 * that return the length of a line, method that return the middle point in the line, and methods
 * that finding the intersecting points of two lines.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class Line {
    /*
    the variables ending with 1 represent the starting point of the line.
    the variables ending with 2 represent the ending point of the line.
    */
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private Point start;
    private Point end;

    /**
     * geometry.Line - constructor to class geometry.Line by two Points.
     *
     * @param start - a geometry.Point that symbol the starting geometry.Point.
     * @param end   - a geometry.Point that symbol the ending geometry.Point.
     */
    public Line(Point start, Point end) {
        x1 = start.getX();
        y1 = start.getY();
        x2 = end.getX();
        y2 = end.getY();
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * geometry.Line - constructor to class geometry.Line by 4 variables of double.
     *
     * @param x1 - symbol the x value of the starting geometry.Point.
     * @param y1 - symbol the y value of the starting geometry.Point.
     * @param x2 - symbol the x value of the ending geometry.Point.
     * @param y2 - symbol the y value of the ending geometry.Point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * length - the function return the length of the geometry.Line.
     *
     * @return start.distance(end) - the length of the geometry.Line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * middle - return the middle point of the geometry.Line.
     *
     * @return middle - the middle geometry.Point of the geometry.Line.
     */
    public Point middle() {
        double middleX = (x1 + x2) / 2; //find the middle point in the x line.
        double middleY = (y1 + y2) / 2; //find the middle point in the y line.
        return new Point(middleX, middleY);
    }

    /**
     * start - return the starting point of the geometry.Line.
     *
     * @return start - the starting point of the geometry.Line.
     */
    public Point start() {
        return start;
    }

    /**
     * end - return the ending point of the geometry.Line.
     *
     * @return end - the ending point of the geometry.Line.
     */
    public Point end() {
        return end;
    }

    /**
     * closestIntersectionToStartOfLine - return the intersection point between line
     *and rectangle.
     * @param rect - rectangle.
     * @return the intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectingPoints = rect.intersectionPoints(new Line(start, end));
        int listLength = intersectingPoints.size();
        if (listLength == 0) {
            return null;
        }
        Point closestPoint = intersectingPoints.get(0);
        for (int i = 0; i < listLength; i++) {
            if (start.distance(closestPoint) > start.distance(intersectingPoints.get(i))) {
                closestPoint = intersectingPoints.get(i);
            }
        }
        return closestPoint;
    }

    /**
     * lineEquation - calculate the line equation as f(x) = (m * x) + d.
     *
     * @return lineEquation - the line equation as f(x) = (m * x) + d
     */
    private double[] lineEquation() {
        //find the m , d of the equation f(x) = m*x + d
        double[] lineEquation = new double[2];
        double m, d;
        //check if x1 - x2 = 0 if it is return null signify for vertical line.
        if (Double.compare(x1, x2) == 0) {
            return null;
        }
        //the m value of the geometry.Line
        m = ((y1 - y2) / (x1 - x2));
        d = y1 - (m * x1);
        lineEquation[0] = m;
        lineEquation[1] = d;
        return lineEquation;
    }

    /**
     * isVertical - checking if one of the line is vertical.
     * @return boolean - true if vertical
     */
    private boolean isVertical() {
        //check if the x values of the line's points are the same.
        if (Double.compare(x1, x2) == 0) {
            return true;
        }
        //check if the y values of the line's points are the same.
        if (Double.compare(y1, y2) == 0) {
            return true;
        }
        return false;
    }

    /**
     * isVerticalIntersecting - return if the lines intersecting or not for vertical line.
     * @param other - other line to compare with.
     * @return true/false - if intersecting true else false.
     */
    private boolean isVerticalIntersecting(Line other) {
        //check for both lines are verticals.
        if (isVertical() && other.isVertical()) {
            //check for both parallel to y line.
            if (Double.compare(x1, x2) == 0 && Double.compare(other.x1, other.x2) == 0) {
                //check if both lines are on the same lines.
                if (Double.compare(x1, other.x1) == 0) {
                    //check if the starting point is on the other line.
                    if ((y1 >= other.y1 && y1 <= other.y2) || (y1 <= other.y1 && y1 >= other.y2)) {
                        return true;
                    }
                    //check if the ending point is on the other line.
                    if ((y2 >= other.y1 && y2 <= other.y2) || (y2 <= other.y1 && y2 >= other.y2)) {
                        return true;
                    }
                    return false;
                }
                return false;
            }
            //check for both parallel to x line.
            if (Double.compare(y1, y2) == 0 && Double.compare(other.y1, other.y2) == 0) {
                if (Double.compare(y1, other.y1) == 0) {
                    //check if the starting point is on the other line.
                    if ((x1 >= other.x1 && x1 <= other.x2) || (x1 <= other.x1 && x1 >= other.x2)) {
                        return true;
                    }
                    //check if the ending point is on the other line.
                    if ((x2 >= other.x1 && x2 <= other.x2) || (x2 <= other.x1 && x2 >= other.x2)) {
                        return true;
                    }
                    return false;
                }
                return false;
            }
            //check if both verticals but not the same way.
            if (Double.compare(x1, x2) == 0 && Double.compare(other.y1, other.y2) == 0) {
                //check for intersecting point.
                if ((x1 >= other.x1 && x1 <= other.x2) || (x1 <= other.x1 && x1 >= other.x2)) {
                    return true;
                }
                return false;
            }
            //check if both verticals but not the same way.
            if (Double.compare(y1, y2) == 0 && Double.compare(other.x1, other.x2) == 0) {
                //check for intersecting point.
                if ((y1 >= other.y1 && y1 <= other.y2) || (y1 <= other.y1 && y1 >= other.y2)) {
                    return true;
                }
                return false;
            }
        }
        //check if this line is vertical and the other is not.
        if (isVertical() && !other.isVertical()) {
            double[] otherEquation = other.lineEquation();
            //check for line parallel to y if intersecting.
            if (Double.compare(x1, x2) == 0 && otherEquation != null) {
                //f(x) = m * x + d
                double y = ((otherEquation[0] * x1) + otherEquation[1]);
                if (pointInLine(new Point(x1, y)) != null) {
                    return true;
                }
                return false;
            }
            //check for line parallel to x if intersecting.
            if (Double.compare(y1, y2) == 0 && otherEquation != null) {
                //x = (y - d ) / m
                double intersectX = ((y1 - otherEquation[1]) / otherEquation[0]);
                if (pointInLine(new Point(intersectX, y1)) != null) {
                    return true;
                }
                return false;
            }
        }
        //check for line not vertical and other is vertical.
        if (!isVertical() && other.isVertical()) {
            double[] lineEquation = lineEquation();
            //check for line parallel to y if intersecting.
            if (Double.compare(other.x1, other.x2) == 0 && lineEquation != null) {
                double y = ((lineEquation[0] * other.x1) + lineEquation[1]);
                if (pointInLine(new Point(other.x1, y)) != null) {
                    return true;
                }
                return false;
            }
            //check for line parallel to x if intersecting.
            if (Double.compare(other.y1, other.y2) == 0 && lineEquation != null) {
                double intersectX = ((other.y1 - lineEquation[1]) / lineEquation[0]);
                if (pointInLine(new Point(intersectX, other.y1)) != null) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * isIntersecting - check if two lines intersecting if they are return true else false.
     *
     * @param other - another line to compare with.
     * @return boolean - true if intersecting else false.
     */
    public boolean isIntersecting(Line other) {
        //if one of them is vertical send to other method to check.
        if (isVertical() || other.isVertical()) {
            return isVerticalIntersecting(other);
        }
        //get the lines equations.
        double[] line1Equation = lineEquation();
        double[] line2Equation = other.lineEquation();
        //the equation cannot be null because the line aren't vertical
        //check if both lines are parallel.
        if ((Double.compare(line1Equation[0], line2Equation[0]) == 0)) {
            //check if parallel but not on the same line
            if (Double.compare(line1Equation[1], line2Equation[1]) != 0) {
                return false;
            }
            //check for both lines are on the same line.
            if (Double.compare(line1Equation[1], line2Equation[1]) == 0) {
                //check if the starting points are on the lines.
                if (pointInLine(other.start) != null || pointInLine(other.end) != null) {
                    return true;
                }
                //checking if the ending points are on the same line.
                if (other.pointInLine(start) != null || other.pointInLine(end) != null) {
                    return true;
                }
                return false;
            }
        }
        //get the intersecting point.
        Point interscPoint = intersectionWith(other);
        //check if the point was on lines.
        if (interscPoint == null) {
            return false;
        }
        return true;
    }

    /**
     * intersectionBetweenTwoVertical - checking intersection between two verticals.
     * @param other - other line
     * @return - point.
     */
    private Point intersectionBetweenTwoVertical(Line other) {
        Point intersectingPoint;
        //check for both parallel to y line.
        if (Double.compare(x1, x2) == 0 && Double.compare(other.x1, other.x2) == 0) {
            //check if the all other line is in the line.
            if (pointInLine(other.start) != null && pointInLine(other.end) != null) {
                return null;
            }
            //check if all line is in the other line.
            if (other.pointInLine(start) != null && other.pointInLine(end) != null) {
                return null;
            }
            //check if the start is one of start or end of the other line.
            if (start.equals(other.start) || start.equals(other.end)) {
                return start;
            }
            //check if the end is one of start or end of the other line.
            if (end.equals(other.end) || end.equals(other.start)) {
                return end;
            }
            return null;
        }
        //check for both parallel to x line.
        if (Double.compare(y1, y2) == 0 && Double.compare(other.y1, other.y2) == 0) {
            //check if the all other line is in the line.
            if (pointInLine(other.start) != null && pointInLine(other.end) != null) {
                return null;
            }
            //check if all line is in the other line.
            if (other.pointInLine(start) != null && other.pointInLine(end) != null) {
                return null;
            }
            //check if the start is one of start or end of the other line.
            if (start.equals(other.start) || start.equals(other.end)) {
                return start;
            }
            //check if the end is one of start or end of the other line.
            if (end.equals(other.end) || end.equals(other.start)) {
                return end;
            }
            return null;
        }
        //check for one parallel to x the other to y.
        if (Double.compare(x1, x2) == 0 && Double.compare(other.y1, other.y2) == 0) {
            intersectingPoint = new Point(x1, other.y1);
            //check if the inspected as intersecting point in on both lines.
            if (pointInLine(intersectingPoint) != null && other.pointInLine(intersectingPoint) != null) {
                return intersectingPoint;
            }
            return null;
        }
        //check for one parallel to x the other to y.
        if (Double.compare(y1, y2) == 0 && Double.compare(other.x1, other.x2) == 0) {
            intersectingPoint = new Point(other.x1, y1);
            //check if the inspected as intersecting point in on both lines.
            if (pointInLine(intersectingPoint) != null && other.pointInLine(intersectingPoint) != null) {
                return intersectingPoint;
            }
            return null;
        }
        return null;
    }

    /**
     * verticalsIntersectingPoint - if one or both of the lines vertical this function will
     *return the intersection point.
     * @param other - other line to compare with.
     * @return intersectionPoint/null - if the lines intersecting return that point else null.
     */
    private Point verticalsIntersectingPoint(Line other) {
        Point intersectingPoint;
        //check if the two lines are the same.
        if (equals(other)) {
            return null;
        }
        //check for one vertical other not.
        if (isVertical() && !other.isVertical()) {
            //get the other line equation.
            double[] otherEquation = other.lineEquation();
            //check for line parallel to y.
            if (Double.compare(x1, x2) == 0 && otherEquation != null) {
                double y = ((otherEquation[0] * x1) + otherEquation[1]);
                intersectingPoint = new Point(x1, y);
                //check if the point is on both lines.
                if (pointInLine(intersectingPoint) != null && other.pointInLine(intersectingPoint) != null) {
                    return intersectingPoint;
                }
                return null;
            }
            //check for line parallel to x.
            if (Double.compare(y1, y2) == 0 && otherEquation != null) {
                double intersectX = ((y1 - otherEquation[1]) / otherEquation[0]);
                intersectingPoint = new Point(intersectX, y1);
                //check if the point is on both lines.
                if (pointInLine(intersectingPoint) != null && other.pointInLine(intersectingPoint) != null) {
                    return intersectingPoint;
                }
                return null;
            }
        }
        //check for not vertical line with vertical line.
        if (!isVertical() && other.isVertical()) {
            //get the line equation.
            double[] lineEquation = lineEquation();
            //check for other parallel to y line.
            if (Double.compare(other.x1, other.x2) == 0 && lineEquation != null) {
                double fX = ((lineEquation[0] * other.x1) + lineEquation[1]);
                intersectingPoint = new Point(other.x1, fX);
                //check if point on both lines.
                if (pointInLine(intersectingPoint) != null && other.pointInLine(intersectingPoint) != null) {
                    return intersectingPoint;
                }
                return null;
            }
            //check for other parallel to x line.
            if (Double.compare(other.y1, other.y2) == 0 && lineEquation != null) {
                double intersectX = ((other.y1 - lineEquation[1]) / lineEquation[0]);
                intersectingPoint = new Point(intersectX, other.y1);
                //check if point on both lines.
                if (pointInLine(intersectingPoint) != null && other.pointInLine(intersectingPoint) != null) {
                    return intersectingPoint;
                }
                return null;
            }
        }
        return null;
    }

    /**
     * intersectionWith - check with other line if intersecting if they do return the intersection point
     * else null.
     *
     * @param other - an other line.
     * @return geometry.Point/null - if the lines intersecting return the point else null
     */
    public Point intersectionWith(Line other) {
        //check if both lines are vertical.
        if (isVertical() && other.isVertical()) {
            return intersectionBetweenTwoVertical(other);
        }
        //check if only one of the line is vertical.
        if (isVertical() || other.isVertical()) {
            return verticalsIntersectingPoint(other);
        }
        //getting the lines equations.
        double[] line1Equation = lineEquation();
        double[] line2Equation = other.lineEquation();
        double intersectionX, intersectionY, m1, d1, m2, d2;
        m1 = line1Equation[0];
        d1 = line1Equation[1];
        m2 = line2Equation[0];
        d2 = line2Equation[1];
        /*
        the equation for finding the intersection point is
        m1*x + d1 = m2*x + d2
        = (m1 - m2)*x = d2 - d1
        = x = (d2 - d1) / (m1 - m2)
        */
        //check if the equation have the same m
        //the equation cannot be null because the line aren't vertical
        if (Double.compare(line1Equation[0], line2Equation[0]) == 0) {
            //if the m the same and the d isn't the lines won't intersect.
            if (Double.compare(line1Equation[1], line2Equation[1]) != 0) {
                return null;
            }
            //check for the same d.
            if (Double.compare(line1Equation[1], line2Equation[1]) == 0) {
                //check if only start in line.
                if (start.equals(other.end) && pointInLine(other.start) == null && other.pointInLine(end) == null) {
                    return start;
                }
                //check if only start in line.
                if (start.equals(other.start) && pointInLine(other.end) == null && other.pointInLine(end) == null) {
                    return start;
                }
                //check if only end in line.
                if (end.equals(other.end) && pointInLine(other.start) == null && other.pointInLine(start) == null) {
                    return end;
                }
                //check if only end in line.
                if (end.equals(other.start) && pointInLine(other.end) == null && other.pointInLine(start) == null) {
                    return end;
                }
                return null;
            }
        }
        //if m1 not equal to m2 so m1 - m2 not equal 0.
        intersectionX = ((d2 - d1) / (m1 - m2));
        intersectionY = ((m1 * intersectionX) + d1);
        Point intersectionPoint = new Point(intersectionX, intersectionY);
        //check if suspected point is in line
        if (pointInLine(intersectionPoint) != null && other.pointInLine(intersectionPoint) != null) {
                return intersectionPoint;
        }
        return null;
    }

    /**
     * isPointInLine - check if given point is in line or not.
     *
     * @param point - given point to check if in line.
     * @return point/null - if point in line return point else null.
     */
    public Point pointInLine(Point point) {
        double x = point.getX();
        double y = point.getY();
        //check if the point is between the both points.
        if (Double.compare(x1, x) >= 0 && Double.compare(x2, x) <= 0) {
            if ((Double.compare(y1, y) >= 0 && Double.compare(y2, y) <= 0)) {
                return point;
            }
            if ((Double.compare(y1, y) <= 0 && Double.compare(y2, y) >= 0)) {
                return point;
            }
        }
        if (Double.compare(x1, x) <= 0 && Double.compare(x2, x) >= 0) {
            if ((Double.compare(y1, y) >= 0 && Double.compare(y2, y) <= 0)) {
                return point;
            }
            if ((Double.compare(y1, y) <= 0 && Double.compare(y2, y) >= 0)) {
                return point;
            }
        }
        return null;
    }

    /**
     * isPointInLine - check if the point is on line.
     * @param point - given point to check.
     * @return boolean.
     */
    public boolean isPointInLine(Point point) {
        //check if the point is on the line.
        if (pointInLine(point) != null) {
            return true;
        }
        return false;
    }

    /**
     * drawOn - draw the line on surface.
     * @param d - drawsurface param.
     */
    public void drawOn(DrawSurface d) {
        d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    /**
     * equals - check if two lines are equal return boolean type.
     *
     * @param other - given line to compare to.
     * @return boolean - if equals return true else false.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.end.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

}