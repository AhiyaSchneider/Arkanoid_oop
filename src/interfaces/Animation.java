package interfaces;

import biuoop.DrawSurface;

/**
 * interface interfaces.Animation.
 */
public interface Animation {
    /**
     * doOneFrame - print every thing to frame.
     * @param d - draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop - indicate if the loop need to stop.
     * @return boolean.
     */
    boolean shouldStop();
}