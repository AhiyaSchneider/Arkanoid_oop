package characters;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class 'characters.Paddle' - implements interfaces.Sprite and interfaces.Collidable.
 * set and operate the paddle with the keyboard.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class Paddle implements Sprite, Collidable {
    private static final int WIDTH = 800;
    private static final int FRAMES_WIDTH_HEIGHT = 15;
    private static final int HEIGHT = 600;
    private static final Color COLOR = Color.orange;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRect;
    private Point paddleUpperLeft;
    private int paddleWidth;
    private int paddleHeight;
    private int step;

    /**
     * Paddle - constructor to paddle.
     * @param keyboard - given keyboard sensor.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     * @param step - the step of the rectangle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, int height, int step) {
        int x, y;
        this.paddleHeight = height;
        this.paddleWidth = width;
        x = (((WIDTH - (2 * FRAMES_WIDTH_HEIGHT)) - paddleWidth) / 2) + FRAMES_WIDTH_HEIGHT;
        y = HEIGHT - (2 * FRAMES_WIDTH_HEIGHT);
        this.keyboard = keyboard;
        this.step = step;
        this.paddleUpperLeft = new Point(x, y);
        this.paddleRect = new Rectangle(paddleUpperLeft, paddleWidth, paddleHeight);
        this.paddleRect.setColor(COLOR);
    }

    /**
     * characters.Paddle - constructor to paddle with given keyboard, the upper left point of the rectangle
     * and height of the paddle.
     * @param keyboard - given keyboard sensor.
     * @param upperLeft - the upper left point of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Point upperLeft, int height) {
        this.keyboard = keyboard;
        paddleHeight = height;
        paddleUpperLeft = upperLeft;
        this.paddleWidth = 150;
        this.step = 10;
        paddleRect = new Rectangle(paddleUpperLeft, paddleWidth, paddleHeight);
        paddleRect.setColor(COLOR);
    }

    /**
     * moveLeft - move the paddle one step to the left.
     */
    public void moveLeft() {
        //check if the paddle after this step will stay in the frame.
        if (paddleUpperLeft.getX() >= step + FRAMES_WIDTH_HEIGHT) {
            paddleUpperLeft.setX(paddleUpperLeft.getX() - step);
        } else {
            paddleUpperLeft.setX(FRAMES_WIDTH_HEIGHT);
        }
        paddleRect.setUpperLeft(paddleUpperLeft);
    }

    /**
     * moveRight - move the paddle one step to right.
     */
    public void moveRight() {
        //check if the paddle after this step will be in the frame.
        if (paddleUpperLeft.getX() <= WIDTH - paddleRect.getWidth() - step - FRAMES_WIDTH_HEIGHT) {
            paddleUpperLeft.setX(paddleUpperLeft.getX() + step);
        } else {
            paddleUpperLeft.setX(WIDTH - paddleRect.getWidth() - FRAMES_WIDTH_HEIGHT);
        }
        paddleRect.setUpperLeft(paddleUpperLeft);
    }

    // interfaces.Sprite:
    /**
     * timePassed - notify that the sprite need to move one step so check
     * if the keys are pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawOn - draw paddle on screen.
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        paddleRect.drawOn(d);
    }

    /**
     * ballInCollidable - check if the ball is in the paddle.
     * @param ball - a ball.
     * @return - boolean.
     */
    public boolean ballInCollidable(Ball ball) {
        int x = ball.getX(), y = ball.getY();
        int downStartX = (int) paddleRect.rectangleDownLine().start().getX();
        int downEndX = (int) paddleRect.rectangleDownLine().end().getX();
        int leftEndY = (int) paddleRect.rectangleLeftLine().end().getY();
        //check if the ball is in the paddle or underneath it.
        if (x >= downStartX && x <= downEndX && y >= leftEndY) {
            return true;
        }
        return false;
    }

    // interfaces.Collidable
    /**
     * getCollisionRectangle - getter to collision rectangle.
     * @return paddle - the paddle rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return paddleRect;
    }

    /**
     * hit - the method hit dill with what happend when the ball collide
     * with the paddle.
     * @param collisionPoint - the point on the rectangle that the ball colliding with.
     * @param currentVelocity - the velocity of the ball.
     * @param ball - hitting ball.
     * @return velocity - new velocity.
     */
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        //divide the paddle up line to 5 region and check where hit.
        double ballSpeed = currentVelocity.getSpeed();
        Line[] regions = getRegionsLine();
        Velocity newVelocity = new Velocity(0, ballSpeed);
        if (regions[0].pointInLine(collisionPoint) != null) {
            newVelocity = Velocity.fromAngleAndSpeed(300, ballSpeed);
        } else if (regions[1].pointInLine(collisionPoint) != null) {
            newVelocity = Velocity.fromAngleAndSpeed(330, ballSpeed);
        } else if (regions[2].pointInLine(collisionPoint) != null) {
            newVelocity = Velocity.fromAngleAndSpeed(0, ballSpeed);
        } else if (regions[3].pointInLine(collisionPoint) != null) {
            newVelocity = Velocity.fromAngleAndSpeed(30, ballSpeed);
        } else if (regions[4].pointInLine(collisionPoint) != null) {
            newVelocity = Velocity.fromAngleAndSpeed(60, ballSpeed);
        } else if (paddleRect.rectangleRightLine().pointInLine(collisionPoint) != null)  {
            newVelocity.setDx(currentVelocity.getDx() * (-1));
        } else if (paddleRect.rectangleLeftLine().pointInLine(collisionPoint) != null)  {
            newVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        return newVelocity;
    }

    /**
     * getRegionsLine - getting 5 diffrent lines from the paddle upper line.
     * @return lines - array of lines.
     */
    private Line[] getRegionsLine() {
        Line[] regions = new Line[5];
        double x = paddleUpperLeft.getX(), y = paddleUpperLeft.getY();
        int regionLength = paddleWidth / 5;
        //set the array to be five difrrent lines.
        for (int i = 0; i < 5; i++) {
            Point startP = new Point(x + (regionLength * i), y);
            Point endP = new Point(x + (regionLength * (i + 1)), y);
            regions[i] = new Line(startP, endP);
        }
        return regions;
    }

    /**
     * addToGame - safe adding the paddle to game.
     * @param g - game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * getPaddleUpperLeft - getter to paddle upper left.
     * @return upper left point.
     */
    public Point getPaddleUpperLeft() {
        return paddleUpperLeft;
    }
}
