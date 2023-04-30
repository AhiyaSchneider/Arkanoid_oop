package game;

import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import characters.Ball;
import characters.Block;
import characters.Paddle;
import counter.Counter;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import remover.BallRemover;
import remover.BlockRemover;
import score.ScoreIndicator;
import score.ScoreTrackingListener;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * class 'game.GameLevel' - initialize game and run one.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class GameLevel implements Animation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int RADIOS = 7;
    private static final int FRAMES_WIDTH = 15;
    private static final int FRAMES_HEIGHT = 15;
    private static final int PADDLE_HEIGHT = 10;

    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private Sprite backGround;
    private List<Block> blocks;
    private String levelName;
    private List<Velocity> velocities;
    private int numOfBlocks;
    private ScoreTrackingListener scoreT;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Paddle paddle;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;

    /**
     * GameLevel - constructor to level.
     * @param levelInfo  - the level info.
     * @param score - the score of the game.
     * @param runner - runner.
     * @param ks - keyboard sensor.
     */
    public GameLevel(LevelInformation levelInfo, Counter score, AnimationRunner runner, KeyboardSensor ks) {
        this.runner = runner;
        this.keyboard = ks;
        this.numOfBalls = levelInfo.numberOfBalls();
        this.paddleSpeed = levelInfo.paddleSpeed();
        this.paddleWidth = levelInfo.paddleWidth();
        this.backGround = levelInfo.getBackground();
        this.blocks = levelInfo.blocks();
        this.levelName = levelInfo.levelName();
        this.velocities = levelInfo.initialBallVelocities();
        this.numOfBlocks = levelInfo.numberOfBlocksToRemove();
        this.paddle = new Paddle(keyboard, paddleWidth, PADDLE_HEIGHT, paddleSpeed);

        //set listeners;
        blockCounter = new Counter(numOfBlocks);
        this.blockRemover = new BlockRemover(this, blockCounter);
        ballCounter = new Counter(numOfBalls);
        this.ballRemover = new BallRemover(this, ballCounter);
        //set the score
        this.score = score;
        this.scoreT = new ScoreTrackingListener(score);
    }

    /**
     * getBlockCounter - getter to block counter.
     * @return block counter.
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * getBallCounter - getter to block counter.
     * @return ball counter.
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * addCollidable - adding collidable to environment.
     * @param c - collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite - adding sprite to the collection.
     * @param s - a sprite obj.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removeCollidable - removing collidable from list.
     * @param c - the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removeSprite - remove sprite from collection.
     * @param s - the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * initialize - initialize new game create the Blocks Balls and characters.Paddle, and add them to the game.
     */
    public void initialize() {
        //initialize the game variables.
        this.running = true;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        //this.runner = new AnimationRunner(gui, 60);
        //set a screen
        backGround.addToGame(this);
        //set a paddle.
        paddle.addToGame(this);
        //set the frames:
        addFrames(ballRemover);
        //set the score block:


        ScoreIndicator scoreIndicator = new ScoreIndicator(score, blockCounter, levelName);
        scoreIndicator.addToGame(this);

        addBlocks();

        //adding balls:
        addBalls();
    }

    /**
     * addFrames - adding the frames to game.
     * @param ballRemover - a listener.
     */
    private void addFrames(BallRemover ballRemover) {
        Rectangle leftSide = new Rectangle(new Point(0, 15), FRAMES_WIDTH, HEIGHT);
        Rectangle rightSide = new Rectangle(new Point(WIDTH - FRAMES_WIDTH, 15), FRAMES_WIDTH, HEIGHT);
        int widthOfUpOrD = WIDTH - (2 * FRAMES_WIDTH);
        Point upperLeftDown = new Point(FRAMES_WIDTH, HEIGHT);
        Rectangle downSide = new Rectangle(upperLeftDown, widthOfUpOrD, FRAMES_HEIGHT);
        Rectangle upperSide = new Rectangle(new Point(FRAMES_WIDTH, 15), widthOfUpOrD, FRAMES_HEIGHT);
        Block leftB = new Block(leftSide, Color.LIGHT_GRAY);
        Block rightB = new Block(rightSide, Color.LIGHT_GRAY);
        Block upperB = new Block(upperSide, Color.LIGHT_GRAY);
        Block downB = new Block(downSide, Color.green);
        leftB.addToGame(this);
        rightB.addToGame(this);
        upperB.addToGame(this);
        downB.addToGame(this);
        downB.addHitListener(ballRemover);
    }

    /**
     * addBlocks - adding the blocks from list.
     */
    public void addBlocks() {
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreT);
        }
    }

    /**
     * addBalls - adding the balls to game.
     */
    public void addBalls() {
        int i = 0, midPaddleX, midPaddleY;
        midPaddleX = (int) paddle.getPaddleUpperLeft().getX() + (paddleWidth / 2);
        midPaddleY = (int) paddle.getPaddleUpperLeft().getY();
        Point aboveMidPaddle;
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        int sizeOfVelocity = velocities.size();
        for (int j = 0; j < numOfBalls; j++) {
            aboveMidPaddle = new Point(midPaddleX + j * 5, midPaddleY - 15);
            if (j + 1 <= sizeOfVelocity) {
                Ball ball = new Ball(aboveMidPaddle, RADIOS, velocities.get(j),  new Color(r, g, b), environment);
                ball.addToGame(this);

            } else {
                Velocity velocity = Velocity.fromAngleAndSpeed(330 + 2 * j, 5);
                Ball ball = new Ball(aboveMidPaddle, RADIOS, velocity, new Color(r, g, b), environment);
                ball.addToGame(this);
            }
        }
    }

    /**
     * shouldStop - implement animation.
     * @return boolean.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * doOneFrame - print to frame one move.
     * @param d - draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        if (!(blockCounter.getValue() > 0) || !(ballCounter.getValue() > 0)) {
            this.running = false;
        }
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, " ", new PauseScreen()));
            this.runner.run(new CountdownAnimation(2, 2, sprites));
            running = true;
        }
    }

    /**
     * run - run the game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }
}
