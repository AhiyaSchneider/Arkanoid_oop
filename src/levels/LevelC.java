package levels;


import LevelsBackScreens.LevelCBackScreen;
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
 * class LevelC - implements LevelInformation.
 */
public class LevelC implements LevelInformation {
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private LevelCBackScreen backGround;
    private List<Block> blocks;
    private String levelName;
    private List<Velocity> velocities;
    private int numOfBlocks;

    /**
     * LevelA - the constructor.
     */
    public LevelC() {
        this.numOfBalls = 8;
        this.numOfBlocks = 40;
        //velocities
        this.velocities =  new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(205, 8));
        velocities.add(Velocity.fromAngleAndSpeed(335, 8));
        //paddle
        this.paddleSpeed = 10;
        this.paddleWidth = 90;

        this.levelName = ("NEW YORK");
        //background
        this.backGround = new LevelCBackScreen();
        //blocks
        setBlocks();
    }

    /**
     * setBlocks - set the level blocks.
     */
    private void setBlocks() {
        this.blocks = new ArrayList<Block>();
        //set an array of colors
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.blue, Color.white};
        int j = 140, line = 0, index = 0;
        int firstX, firstY;
        for (int i = 0; i < 5; i++) {
            firstX = 785 - ((10 - i) * 60);
            firstY = 50;
            for (int k = 0; k < 10 - i; k++) {
                int blockX = firstX + (k * 60);
                int blockY = firstY + (i * 15);
                blocks.add(new Block(new Rectangle(new Point(blockX, blockY), 60, 15), colors[i]));
            }
        }
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
        return numOfBlocks;
    }
}
