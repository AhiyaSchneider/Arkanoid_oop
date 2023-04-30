package score;

import biuoop.DrawSurface;
import counter.Counter;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class ScoreLivesIndicator - implements Sprite.
 */
public class ScoreLivesIndicator implements Sprite {
    private Counter score;
    private Counter lives;
    private Rectangle rect;
    private String name;

    /**
     * ScoreLivesIndicator - constructor to class.
     * @param score - the score of the game.
     * @param lives - the lives of the game.
     * @param name - the name of the game.
     */
    public ScoreLivesIndicator(Counter score, Counter lives, String name) {
        this.score = score;

        this.lives = lives;
        this.rect = new Rectangle(new Point(0, 0), 800, 15, Color.white);
        this.name = name;
    }
    @Override
    public void drawOn(DrawSurface d) {

        rect.drawOn(d);
        d.setColor(Color.black);
        d.drawText(70, 14, "Lives:" + lives.toString(), 17);

        d.drawText(350, 14, "Score:" + score.toString(), 17);
        d.drawText(570, 14, "Name:" + name, 17);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

        g.addSprite(this);
    }
}
