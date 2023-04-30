// 208687251 Ahiya Schneider

package characters;

import biuoop.DrawSurface;
import collidables.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

import java.util.ArrayList;

/**
 * class 'characters.Ball' - this class has 3 parameters that represent the center of a ball,
 * 1 parameter represent the radius of the ball, 1 parameter represent the color,
 * 1 parameter represent the velocity, 2 parameters represent the frame,1 parameter
 * represent the top left point of the ball's frame, and there is game environment.
 * in the class there are 3 constructors, setters getters and methods that moving the
 * ball to the next move point.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class Ball implements Sprite {
    private Point center;
    private int x;
    private int y;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private int width;
    private int height;
    private Point topLeftP;
    private GameEnvironment gameEnvironment;

    /**
     * characters.Ball - constructor to class characters.Ball.
     *
     * @param center - point that mean the center of the circle.
     * @param r - the radius of the circle.
     * @param color - the color of the circle.
     * @param g - game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = center;
        this.x = (int) center.getX();
        this.y = (int) center.getY();
        this.r = r;
        this.color = color;
        setVelocity(0, 0);
        width = 800;
        height = 600;
        topLeftP = new Point(0, 0);
        this.gameEnvironment = g;
    }

    /**
     * characters.Ball - constructor to class characters.Ball.
     *
     * @param center - point that mean the center of the circle.
     * @param r - the radius of the circle.
     * @param v - velocity.
     * @param color - the color of the circle.
     * @param g - game environment.
     */
    public Ball(Point center, int r, Velocity v, java.awt.Color color, GameEnvironment g) {
        this.center = center;
        this.x = (int) center.getX();
        this.y = (int) center.getY();
        this.r = r;
        this.color = color;
        this.v = v;
        width = 800;
        height = 600;
        topLeftP = new Point(0, 0);
        this.gameEnvironment = g;

    }

    /**
     * characters.Ball - constructor to class characters.Ball.
     * @param x - the x value of the center point of the circle.
     * @param y - the y value of the center point of the circle.
     * @param r - the radius of the circle.
     * @param color - the color of the circle.
     * @param g - game environment.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment g) {
        center = new Point(x, y);
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        setVelocity(0, 0);
        width = 800;
        height = 600;
        topLeftP = new Point(0, 0);
        this.gameEnvironment = g;
    }



    /**
     * setWidthHeight - set other width and height for ball's frame.
     * @param w - given width of frame.
     * @param h - given height of frame.
     */
    public void setWidthHeight(int w, int h) {
        width = w;
        height = h;
    }

    /**
     * setVelocity - set new velocity.
     *
     * @param v - new velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
        checkVelocity();
    }

    /**
     * setGameEnvironment - setter to game environment.
     * @param gameEnvironment - new game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * setVelocity - set new velocity.
     *
     * @param dx - new dx value
     * @param dy - new dy value
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
        checkVelocity();
    }

    /**
     * setY - setter to y.
     * @param y - new y.
     */
    public void setY(int y) {
        this.y = y;
        center.setY(y);
    }

    /**
     * setX - setter to x.
     * @param x - new x.
     */
    public void setX(int x) {
        this.x = x;
        center.setX(x);
    }

    /**
     * setCenter - setter to center.
     * @param center - new center.
     */
    public void setCenter(Point center) {
        this.center = center;
        x = (int) center.getX();
        y = (int) center.getY();
    }

    /**
     * checkVelocity - check if the velocity with the size of the ball will put ball out of bounds if it
     * does put in velocity 0 at the proper value dx/dy.
     */
    private void checkVelocity() {
        int dx = (int) v.getDx();
        int dy = (int) v.getDy();
        //check if dx is negative value and change it to positive
        if (dx < 0) {
            dx = ((-1) * dx);
        }
        //do the same as dx to dy
        if (dy < 0) {
            dy = ((-1) * dy);
        }
        //check if when the ball will do a step in x line if the ball will get out of bound
        if ((x + dx + r) > width && (x - dx - r) < 0) {
            //if true set velocity dx = 0
            v.setDx(0);
        }
        //check if when the ball will do a step in y line if the ball will get out of bound
        if ((y + dy + r) > height && (y - dy - r) < 0) {
            //if true set velocity dy = 0
            v.setDy(0);
        }
    }

    /**
     * drawOn - draw ball on given surface.
     *
     * @param surface - the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(x + (int) topLeftP.getX(), y + (int) topLeftP.getY(), r);
    }

    /**
     * timePassed - method for the while loop in game.GameLevel moving sprite collection one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * addToGame - safe adding ball to game.
     * @param g - game.GameLevel.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removeFromGame - safe remove of the ball from the game.
     * @param g - a game level.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * setTopLeftP - setting new top left point for this ball.
     * @param newTopLeft - given top left point.
     */
    public void setTopLeftP(Point newTopLeft) {
        topLeftP = newTopLeft;
    }

    /**
     * moveOneStep - move the ball one step with the ball velocity.
     */
    public void moveOneStep() {
        //to check if there is a ball in a rectangle.
        Rectangle ballInRect = checkIfBallInCollidable();
        if (ballInRect != null) {
            //if true put the ball above the rectangle - the paddle.
            this.setY((int) ballInRect.getUpperLeft().getY() - 1);
            return;
        }
        //a line between the center of the ball to the next point.
        Line ballLine = new Line(x, y, x + v.getDx(), y + v.getDy());
        //check with the ball line if there will be any collision and get the closest.
        CollisionInfo collisionI = gameEnvironment.getClosestCollision(ballLine);
        //check there will be a collision.
        if (collisionI != null) {
            //if there will be a collision change velocity.
            Point collisionP = collisionI.collisionPoint();
            Rectangle collisionRect = collisionI.collisionObject().getCollisionRectangle();
            setVelocity(collisionI.collisionObject().hit(this, collisionP, v));
            moveBallAlmostStep(collisionP, collisionRect);
            return;
        }
        //change the center point to next point
        this.center = this.getVelocity().applyToPoint(this.center);
        //change x,y values to be the values of the new center
        this.x = (int) center.getX();
        this.y = (int) center.getY();
    }

    /**
     * checkIfBallInCollidable - check if the ball is in a rectangle.
     * @return if it is in rectangle return the rectangle else null.
     */
    public Rectangle checkIfBallInCollidable() {
        //check if the ball is in a rectangle.
        ArrayList<Collidable> collidableList = gameEnvironment.getCollidableList();
        for (Collidable collidable : collidableList) {
            if (collidable.ballInCollidable(this)) {
                return collidable.getCollisionRectangle();
            }
        }
        return null;
    }

    /**
     *moveBallAlmostStep - move the ball almost to collision.
     * @param collisionP - the collision point.
     * @param rectangle - the rectangle the ball collide with.
     */
    public void moveBallAlmostStep(Point collisionP, Rectangle rectangle) {
        //check if the collision is on the left line of the rectangle.
        if (rectangle.rectangleLeftLine().isPointInLine(collisionP)) {
            setX((int) collisionP.getX() - 1);
        }
        //check if the collision is on the right line of the rectangle.
        if (rectangle.rectangleRightLine().isPointInLine(collisionP)) {
            setX((int) collisionP.getX() + 1);
        }
        //check if the collision is on the up line of the rectangle.
        if (rectangle.rectangleUpLine().isPointInLine(collisionP)) {
            setY(((int) collisionP.getY() - 1));
        }
        //check if the collision is on the down line of the rectangle.
        if (rectangle.rectangleDownLine().isPointInLine(collisionP)) {
            setY((int) collisionP.getY() + 1);
        }
    }

    /**
     * checkNextMove - check if the next move on x/y values will get the ball out of bounds.
     * if it does multiplication dx/dy by (-1).
     */
    private void checkNextMove() {
        //check if after the next step the ball will be out of the x bound
        ArrayList<Collidable> collidableList = gameEnvironment.getCollidableList();
        for (Collidable collidable : collidableList) {
            if (collidable.ballInCollidable(this)) {
                this.v.setDx((-1) * v.getDx());
                this.v.setDy((-1) * v.getDy());
            }
        }
        if ((x + (int) v.getDx() + r) > width || (x + (int) v.getDx() - r) < 0) {
            //if true change velocity dx to be (-1) * dx
            this.v.setDx((-1) * v.getDx());
        }
        //check if after the next step the ball will be out of the y bound
        if ((y + (int) v.getDy() + r) > height || (y + (int) v.getDy() - r) < 0) {
            //if true change velocity dy to be (-1) * dy
            this.v.setDy((-1) * v.getDy());
        }
    }

    /**
     * getX - return x value of the center point.
     * @return x - the x value of the center of the circle.
     */
    public int getX() {
        return x;
    }

    /**
     * getY - return the y value of the center of the ball.
     * @return y - the value of y.
     */
    public int getY() {
        return y;
    }

    /**
     * getCenter - return the center point of the ball.
     * @return center - the center of the ball.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getSize - return the radius of the circle.
     * @return r - the radius of the circle.
     */
    public int getSize() {
        return r;
    }

    /**
     * getColor - return the color of the circle.
     * @return color - the color of the circle.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * getVelocity - return the velocity of the ball.
     * @return v - the ball's velocity.
     */
    public Velocity getVelocity() {
        return v;
    }

}