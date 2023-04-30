package LevelsBackScreens;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class LevelBBackScreen - implements Sprite.
 */
public class LevelBBackScreen implements Sprite {
    private Rectangle rectangle;
    private int ray = 0;
    private int frames = 0;

    /**
     * LevelBBackScreen - level B back screen.
     */
    public LevelBBackScreen() {
        this.rectangle = new Rectangle(new Point(0, 0), 800, 600, new Color(128, 186, 245));
    }

    @Override
    public void drawOn(DrawSurface d) {
        //draw the background
        rectangle.drawOn(d);
        d.setColor(new Color(235, 252, 42));
        for (int i = 0; i <= 38; i++) {
            d.drawLine(160, 140, 15 + 20 * i - ray, 250);
        }
        d.setColor(Color.yellow);
        d.fillCircle(160, 140, 70);
        d.setColor(Color.orange);
        d.fillCircle(160, 140, 60);
        d.setColor(new Color(255, 169, 8));
        d.fillCircle(160, 140, 50);
    }

    @Override
    public void timePassed() {
        frames++;
        if (frames == 160) {
            ray = 0;
            frames = 0;
        }
        ray = frames / 4;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
