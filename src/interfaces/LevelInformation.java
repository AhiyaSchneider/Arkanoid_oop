package interfaces;

import characters.Block;
import geometry.Velocity;

import java.util.List;

/**
 * interface interfaces.LevelInformation.
 */
public interface LevelInformation {

    /**
     * numberOfBalls - num of balls to start level with.
     * @return num of balls.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities - the velocity to start balls with.
     * The initial velocity of each ball.
     * @return list of velocities. Note that initialBallVelocities().size() == numberOfBalls().
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed - the speed of the paddle.
     * @return speed of the paddle.
     */
    int paddleSpeed();

    /**
     * paddleWidth - the width of the paddle.
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * levelName - the level name will be displayed at the top of the screen.
     * @return the level name.
     */
    String levelName();

    /**
     * getBackground - Returns a sprite with the background of the level.
     * @return background of the level.
     */
    Sprite getBackground();

    /**
     * blocks - The Blocks that make up this level, each block contains its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove - Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size().
     * @return number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
