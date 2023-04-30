package game;

import animation.EndScreen;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import counter.Counter;
import interfaces.LevelInformation;
import score.ScoreLivesIndicator;

import java.util.List;

/**
 * class GameFlow - control the game flow.
 */
public class GameFlow {
    private boolean lose;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;

    /**
     * GameFlow - constructor to class.
     * @param ar - animation runner.
     * @param ks - keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.lose = false;
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter(0);
        this.lives = new Counter(2);
    }

    /**
     * runLevels - run list of levels.
     * @param levels - list of levels info.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, score, animationRunner, keyboardSensor);
            level.initialize();
            Counter numOfBlocks = level.getBlockCounter();
            Counter numOfBalls = level.getBallCounter();
            ScoreLivesIndicator scoreLivesIndicator = new ScoreLivesIndicator(score, lives, levelInfo.levelName());
            scoreLivesIndicator.addToGame(level);

            while (numOfBlocks.getValue() > 0 && lives.getValue() > 0) {
                level.run();
                if (numOfBlocks.getValue() != 0) {
                    lives.decrease(1);
                    level.addBalls();
                    numOfBalls.increase(levelInfo.numberOfBalls());
                }
            }
            if (lives.getValue() == 0) {
                lose = true;
                break;
            }
        }
        EndScreen endScreen = new EndScreen(!lose, score.getValue());
        KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(keyboardSensor, " ", endScreen);
        animationRunner.run(k);
        animationRunner.getOut();
    }
}
