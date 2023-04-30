package animation;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * class animation.PauseScreen - implements interfaces.Animation.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * animation.PauseScreen - constructor to class.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * doOneFrame - draw one frame on surface.
     * @param d - draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillCircle(400, 200, 150);
        d.setColor(Color.cyan);
        d.fillRectangle(335, 110, 30, 160);
        d.fillRectangle(445, 110, 30, 160);
        d.fillRectangle(365, 180, 110, 30);
        d.setColor(Color.red);
        d.drawText(150, 400, "paused -- press space to continue", 32);
    }

    /**
     * shouldStop - when it should stop.
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
