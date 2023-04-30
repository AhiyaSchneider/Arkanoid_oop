import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import interfaces.LevelInformation;
import levels.LevelA;
import levels.LevelB;
import levels.LevelC;
import levels.LevelD;

import java.util.ArrayList;
import java.util.List;

/**
 * class Ass6Game - the class with the main method starting all the game.
 */
public class Ass6Game {

    /**
     * main - the main method.
     * @param args - arguments from user.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = getLevels(args);
        GUI gui = new GUI("arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor());
        gameFlow.runLevels(levels);
    }

    /**
     * getLevels - get levels from arguments.
     * @param args - arguments.
     * @return list of levels.
     */
    private static List<LevelInformation> getLevels(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation[] levelsArr = new LevelInformation[4];
        levelsArr[0] = new LevelA();
        levelsArr[1] = new LevelB();
        levelsArr[2] = new LevelC();
        levelsArr[3] = new LevelD();
        if (args != null) {
            for (String arg : args) {
                if (arg.equals("1")) {
                    levels.add(levelsArr[0]);
                } else if (arg.equals("2")) {
                    levels.add(levelsArr[1]);
                } else if (arg.equals("3")) {
                    levels.add(levelsArr[2]);
                } else if (arg.equals("4")) {
                    levels.add(levelsArr[3]);
                }
            }
        }
        if (args == null || levels.size() == 0) {
            for (LevelInformation level : levelsArr) {
                levels.add(level);
            }
        }
        return levels;
    }
}
