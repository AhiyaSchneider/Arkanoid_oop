package levels;

import LevelsBackScreens.LevelBBackScreen;
import characters.Block;
import geometry.Point;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *  class LevelB - implements LevelInformation.
 */
public class LevelB implements LevelInformation {
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private LevelBBackScreen backGround;
    private List<Block> blocks;
    private String levelName;
    private List<Velocity> velocities;
    private int numOfBlocks;

    /**
     * LevelA - the constructor.
     */
    public LevelB() {
        this.numOfBalls = 15;
        this.numOfBlocks = 15;
        //velocities
        this.velocities =  new ArrayList<Velocity>();
        for (int i = 0; i < numOfBalls; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-60 + (i * 12), 7));
        }
        //paddle
        this.paddleSpeed = 1;
        this.paddleWidth = 600;

        this.levelName = ("Much Wide");
        //background
        this.backGround = new LevelBBackScreen();
        //blocks
        setBlocks();
    }

    /**
     * setBlocks - set the level blocks.
     */
    private void setBlocks() {
        this.blocks = new ArrayList<Block>();
        int startX = 40, startY = 250;
        for (int i = 0; i < numOfBlocks; i++) {
            // add the blocks to the list
            if (i == 0 || i == 1) {
                blocks.add(new Block(new geometry.Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.red));
            } else if (i == 2 || i == 3) {
                blocks.add(new Block(new geometry.Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.orange));
            } else if (i == 4 || i == 5) {
                blocks.add(new Block(new geometry.Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.yellow));
            } else if (i == 6 || i == 7 || i == 8) {
                blocks.add(new Block(new geometry.Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.green));
            } else if (i == 9 || i == 10) {
                blocks.add(new Block(new geometry.Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.blue));
            } else if (i == 11 || i == 12) {
                blocks.add(new Block(new geometry.Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.pink));
            } else if (i == 13 || i == 14) {
                blocks.add(new Block(new geometry.Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.CYAN));
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
