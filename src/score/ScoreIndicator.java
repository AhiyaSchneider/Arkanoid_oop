package score;

import biuoop.DrawSurface;
import counter.Counter;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class score.ScoreIndicator implements interfaces.Sprite.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Counter numOfBlocks;
    private Rectangle rect = new Rectangle(new Point(0, 0), 800, 15);
    private String name;

    /**
     * score.ScoreIndicator - constructor.
     * @param score - the score counter.
     * @param numOfBlocks - the num of the blocks.
     * @param name - the level name.
     */
    public ScoreIndicator(Counter score, Counter numOfBlocks, String name) {
        this.score = score;
        rect.setColor(Color.white);
        this.numOfBlocks = numOfBlocks;
        this.name = name;
    }
    /**
     * drawOn - drawing the object on surface.
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        if (numOfBlocks.getValue() == 0) {
            score.increase(100);
        }
         rect.drawOn(d);
         d.setColor(Color.black);
        d.drawText(600, 14, "Name:" + name, 17);
        d.drawText(350, 14, "Score:" + score.getValue(), 17);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * addToGame - safely adding object to game.
     * @param g - game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
