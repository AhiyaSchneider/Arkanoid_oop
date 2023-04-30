package geometry;

import static java.lang.Math.sqrt;

/**
 * class 'geometry.Velocity' - the class has 2 parameters 1 represent the advance in the x line in any step
 * and 1 represent the advance in the y line in any step.
 * the class has constructor setters getters, method that change angle and speed to dx, dy and
 * method that move given point to new point with dx, dy values.
 *
 * @author Ahiya Schneider
 * Date: 27.03.2022
 */
public class Velocity {
    private double dx;
    private double dy;
    private double speed;

    /**
     *geometry.Velocity - constructor to velocity class.
     * @param dx - the given velocity in the x line.
     * @param dy - the given velocity in y line.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.speed = sqrt((dx * dx) + (dy * dy));
    }

    /**
     * getDx - return the value of dx.
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * getDy - return the value of dx.
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * setDx - set new dx.
     * @param dx - a new dx.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * setDy - set a new dy.
     * @param dy - new dy.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * getSpeed - getter to the speed.
     * @return speed.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * fromAngleAndSpeed - change angle and speed to velocity as dx and dy.
     * @param angle - the angle in 0 - 360 range.
     * @param speed - the given speed of the ball.
     * @return geometry.Velocity(dx, dy) - the velocity from angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //change the angle from 0-360 range into radian angle
        double radiansAngle = Math.toRadians(angle);
        //calculate the dx and dy
        double dx = (speed * Math.sin(radiansAngle));
        double dy = ((-1) * (speed * Math.cos(radiansAngle)));
        return new Velocity(dx, dy);
    }

    /**
     * applyToPoint - generate new point with x = given point x + dx and y = given point y + dy.
     * @param p - given point.
     * @return newP - the new point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}
