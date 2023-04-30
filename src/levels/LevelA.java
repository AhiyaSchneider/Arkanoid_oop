package levels;

import LevelsBackScreens.LevelABackScreen;
import characters.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class LevelA - first level.
 */
public class LevelA implements LevelInformation {
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private LevelABackScreen backGround;
    private List<Block> blocks;
    private String levelName;
    private List<Velocity> velocities;

    /**
     * LevelA - the constructor.
     */
    public LevelA() {
        this.numOfBalls = 1;
        //velocities
        this.velocities =  new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 6));
        //paddle
        this.paddleSpeed = 10;
        this.paddleWidth = 100;

        this.levelName = ("Direct Hit");
        //background
        this.backGround = new LevelABackScreen();
        //blocks
        this.blocks = new ArrayList<Block>();
        blocks.add(new Block(new Rectangle(new Point(390, 180), 30, 30), Color.red));
    }

    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return backGround;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }
}
