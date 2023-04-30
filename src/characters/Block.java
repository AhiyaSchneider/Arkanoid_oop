package characters;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * class 'characters.Block' - class for definition a block complex from rectangle and color.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private Color color;

    /**
     * characters.Block - constructor to block.
     * @param rectangle - rectangle param.
     * @param color - the color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        rectangle.setColor(color);
        this.color = color;
        this.hitListeners = new ArrayList<>(0);
    }

    /**
     * characters.Block - constructor to block class.
     * @param upperLeft - a point.
     * @param width - the width of the block.
     * @param height - the height of the block.
     */
    public Block(Point upperLeft, double width, double height) {
        rectangle = new Rectangle(upperLeft, width, height);
        color = Color.BLACK;
        this.hitListeners = new ArrayList<>(0);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * addHitListener - Add hl as a listener to hit events.
     * @param hl - a interfaces.HitListener to add.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removeHitListener - Remove hl from the list of listeners to hit events.
     * @param hl - interfaces.HitListener to remove.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * getCollisionRectangle - the method return the rectangle.
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * drawOn - draw the block on surface.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        //draw the rectangle.
        d.setColor(color);
        rectangle.drawOn(d);
        //draw the rectangle lines.
        d.setColor(Color.BLACK);
        rectangle.rectangleRightLine().drawOn(d);
        rectangle.rectangleUpLine().drawOn(d);
        rectangle.rectangleLeftLine().drawOn(d);
        rectangle.rectangleDownLine().drawOn(d);
    }

    /**
     * timePassed - implement method from sprite for now null.
     */
    public void timePassed() { }

    /**
     * addToGame - safe adding block to game.
     * @param g - the game to add to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removeFromGame - remove the block from a game.
     * @param game - game to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * ballInCollidable - check if there is a ball in the block.
     */
    @Override
    public boolean ballInCollidable(Ball ball) {
        if (rectangle.ballInRectangle(ball)) {
            return true;
        }
        return false;
    }

    /**
     * setColor - setter to color.
     * @param color - new color.
     */
    public void setColor(Color color) {
        this.color = color;
        rectangle.setColor(color);
    }

    /**
     * setRandColor - setting a random color.
     */
    private void setRandColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        setColor(new Color(r, g, b));
    }


    /**
     * hit - the method dill with change the velocity after collision.
     * @param collisionPoint - the point that the ball will collide with.
     * @param currentVelocity - the current velocity of the ball.
     * @param hitter - ball hiting.
     * @return currentvelocity - updated velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //set a new random color to show that this block had been hit.
        this.notifyHit(hitter);
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        //if the collision is on the upper or lower side of the block.
        if (rectangle.rectangleDownLine().pointInLine(collisionPoint) != null) {
            newVelocity.setDy((-1) * currentVelocity.getDy());
            //check if the ball hit in a vertex that not suppose to change the velocity.
            if (rectangle.rectangleDownLine().start().equals(collisionPoint) && currentVelocity.getDy() > 0) {
                newVelocity.setDy(currentVelocity.getDy());
            } else if (rectangle.rectangleDownLine().end().equals(collisionPoint) && currentVelocity.getDy() > 0) {
                newVelocity.setDy(currentVelocity.getDy());
            }
        } else if (rectangle.rectangleUpLine().pointInLine(collisionPoint) != null) {
            newVelocity.setDy((-1) * currentVelocity.getDy());
            //check if the ball hit in a vertex that not suppose to change the velocity.
            if (rectangle.rectangleUpLine().start().equals(collisionPoint) && currentVelocity.getDy() < 0) {
                newVelocity.setDy(currentVelocity.getDy());
            } else if (rectangle.rectangleUpLine().end().equals(collisionPoint) && currentVelocity.getDy() < 0) {
                newVelocity.setDy(currentVelocity.getDy());
            }
        }
        //check if the collision is with the right or left sides of the block.
        if (rectangle.rectangleRightLine().pointInLine(collisionPoint) != null) {
            newVelocity.setDx((-1) * currentVelocity.getDx());
            //check if the ball hit in a vertex that not suppose to change the velocity.
            if (rectangle.rectangleRightLine().start().equals(collisionPoint) && currentVelocity.getDy() < 0) {
                newVelocity.setDx(currentVelocity.getDx());
            } else if (rectangle.rectangleRightLine().end().equals(collisionPoint) && currentVelocity.getDy() < 0) {
                newVelocity.setDx(currentVelocity.getDx());
            }
        } else if (rectangle.rectangleLeftLine().pointInLine(collisionPoint) != null) {
            newVelocity.setDx((-1) * currentVelocity.getDx());
            //check if the ball hit in a vertex that not suppose to change the velocity.
            if (rectangle.rectangleLeftLine().start().equals(collisionPoint) && currentVelocity.getDx() > 0) {
                newVelocity.setDx(currentVelocity.getDx());
            } else if (rectangle.rectangleLeftLine().end().equals(collisionPoint) && currentVelocity.getDx() > 0) {
                newVelocity.setDx(currentVelocity.getDx());
            }
        }

        return newVelocity;
    }
}
