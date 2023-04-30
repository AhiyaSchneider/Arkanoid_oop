package collidables;

import geometry.Point;
import interfaces.Collidable;

/**
 * class 'collidables.CollisionInfo' - store the info of a collision.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022.
 */
public class CollisionInfo {
    /**
     * y - the value in axe y.
     * x - the value on axe x.
     * collidable - the collidable object.
     */
    private double x;
    private double y;
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * collidables.CollisionInfo - constructor to this class.
     * @param x - the point on axe x where the collision will occur.
     * @param y - the point on axe y where the collision will occur.
     * @param c - collidable param(getractangle, hit).
     *
     */
    public CollisionInfo(double x, double y, Collidable c) {
        this.x = x;
        this.y = y;
        this.collisionPoint = new Point(x, y);
        this.collidable = c;
    }

    /**
     * collidables.CollisionInfo - constructor to this class with point and collidable obj.
     * @param collisionPoint - the collision point.
     * @param c - the obj.
     */
    public CollisionInfo(Point collisionPoint, Collidable c) {
        this.x = collisionPoint.getX();
        this.y = collisionPoint.getY();
        this.collisionPoint = collisionPoint;
        this.collidable = c;
    }

    /**
     * collisionPoint - return the collision point.
     * @return - collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * getX - getter to x.
     * @return x.
     */
    public double getX() {
        return x;
    }

    /**
     * getY - getter to Y.
     * @return y.
     */
    public double getY() {
        return y;
    }

    /**
     * setX - setter to X.
     * @param x - x.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setY - setter to y.
     * @param y - y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * collisionObject - return the collidable.
     * @return collidable - the collidable.
     */
    public Collidable collisionObject() {
        return collidable;
    }
}
