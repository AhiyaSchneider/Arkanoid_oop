// 208687251 Ahiya Schneider
package geometry;

/**
 * class 'geometry.Point' - the class is representing point as two double variables one on the x line
 *and one on the y line in the class there are setters getters constructors and method that check
 *if two points are equals.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class Point {

    private double x;
    private double y;

    /**
     * geometry.Point - constructor to geometry.Point class.
     * @param point - new point.
     */
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * point - the constructor to point class build point with x and y.
     *
     * @param x - the wanted value of x
     * @param y - the wanted value of y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - get another point and return the distance between the two points.
     *
     * @param other - other point.
     * @return Math.sqrt(sqrdist) - the distance between two points.
     */
    public double distance(Point other) {
        if (other == null) {
            return 100000000;
        }
        double x1 = x, y1 =  y, x2 = other.getX(), y2 = other.getY();
        double sqrdist = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        return Math.sqrt(sqrdist);
    }

    /**
     * equals - check if other point is equal in the values to this point.
     *
     * @param other - other point to check if both equal.
     * @return true/false - if equal return true if not false.
     */
    public boolean equals(Point other) {
        if (other != null && Double.compare(x, other.getX()) == 0 && Double.compare(y, other.getY()) == 0) {
            return true;
        }
        return false;
    }

    /**
     * getx - return the value of x.
     *
     * @return _x - the value of x.
     */
    public double getX() {
        return x;
    }

    /**
     * gety - return the value of y.
     *
     * @return _y - the value of y.
     */
    public double getY() {
        return y;
    }

    /**
     * setX - setter for the x value.
     * @param x - given value for x.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setY - setter for y.
     * @param y - given value for y.
     */
    public void setY(double y) {
        this.y = y;
    }
}
