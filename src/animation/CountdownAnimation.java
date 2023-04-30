package animation;

import biuoop.DrawSurface;
import interfaces.Animation;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * class animation.CountdownAnimation - implements interfaces.Animation,
 * The animation.CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show, a countdown from countFrom back to 1,
 * where each number will appear on the screen for (numOfSeconds / countFrom) seconds,
 * before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private static final double SECONDS_PER_FRAME = 0.016;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int numToDisplay;

    /**
     * animation.CountdownAnimation - constructor to class.
     * @param numOfSeconds - the num of seconds till the game start.
     * @param countFrom - starting number to count.
     * @param gameScreen - the sprite collection.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * doOneFrame - draw one frame on surface.
     * @param d - draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        this.numToDisplay = (int) numOfSeconds + 1;
        d.setColor(Color.cyan);
        d.drawText(345, d.getHeight() / 2 + 100, String.valueOf(numToDisplay), 200);
        numOfSeconds -= SECONDS_PER_FRAME;
        if (numOfSeconds <= 0) {
            this.stop = true;
        }
    }

    /**
     * shouldStop - when it should stop.
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}