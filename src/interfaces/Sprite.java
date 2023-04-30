package interfaces; // 208687251 Ahiya Schneider

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * interface 'interfaces.Sprite' - interfacing methods to sprites objects.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public interface Sprite {

    /**
     * drawOn - drawing the object on surface.
     * @param d - surface.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed - notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * addToGame - safely adding object to game.
     * @param g - game.
     */
    void addToGame(GameLevel g);
}
