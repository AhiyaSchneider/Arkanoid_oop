package interfaces;

import characters.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * interface 'interfaces.Collidable' - is interfacing methods for colliding objects
 * getCollisionRectangle - return the rectangle.
 * hit - return the velocity after a hit.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022.
 */
public interface Collidable {
    /**
     * getCollisionRectangle - method for getting rectangle.
     * @return a rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit - method that dilling with ball hitting with the rectangle.
     * @param collisionPoint - the point on the rectangle that the ball colliding with.
     * @param currentVelocity - the velocity of the ball.
     * @param hitter - the hitting ball.
     * @return new velocity for ball.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * ballInCollidable - to check if the ball in the collidable.
     * @param ball - a ball.
     * @return true if the ball in the collidable.
     */
    boolean ballInCollidable(Ball ball);
}
