package levels;

import LevelsBackScreens.LevelDBackScreen;
import characters.Block;
import geometry.Point;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class LevelD - implements LevelInformation.
 */
public class LevelD implements LevelInformation {
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private LevelDBackScreen backGround;
    private List<Block> blocks;
    private String levelName;
    private List<Velocity> velocities;

    /**
     * LevelA - the constructor.
     */
    public LevelD() {
        this.numOfBalls = 4;
        //velocities
        this.velocities =  new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(10, 10));
        velocities.add(Velocity.fromAngleAndSpeed(350, 7));
        velocities.add(Velocity.fromAngleAndSpeed(30, 7));
        velocities.add(Velocity.fromAngleAndSpeed(0, 10));
        //paddle
        this.paddleSpeed = 10;
        this.paddleWidth = 160;

        this.levelName = ("Beautiful Day");
        //background
        this.backGround = new LevelDBackScreen();
        //blocks
        setBlocks();
    }

    /**
     * setBlocks - set the block of the level.
     */
    private void setBlocks() {
        this.blocks = new ArrayList<Block>();
        int startX = 15, startY = 80;
        // set an array of colors
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.cyan};
        //creates the blocks
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 11; j++) {
                blocks.add(new Block(new geometry.Rectangle(new Point(startX + 70 * j, startY + 15 * i),
                        70, 15), colors[i]));
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
        return blocks.size();
    }
}
