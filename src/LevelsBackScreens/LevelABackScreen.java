package LevelsBackScreens;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class LevelABackScreen - implements Sprite.
 */
public class LevelABackScreen implements Sprite {
    private Rectangle rectangle;

    /**
     * BackGround - constructor.
     */
    public LevelABackScreen() {
        this.rectangle = new Rectangle(new Point(0, 0), 800, 600, Color.black);
    }
    @Override
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d);
        d.setColor(Color.red);
        Point midBlock = new Point(405, 195);
        Line line1 = new Line(midBlock.getX() - 150, midBlock.getY(), midBlock.getX() + 150, midBlock.getY());
        Line line2 = new Line(midBlock.getX(), midBlock.getY() - 150, midBlock.getX(), midBlock.getY() + 150);
        d.drawCircle((int) midBlock.getX(), (int) midBlock.getY(), 150);
        d.drawCircle((int) midBlock.getX(), (int) midBlock.getY(), 125);
        d.drawCircle((int) midBlock.getX(), (int) midBlock.getY(), 100);
        d.drawCircle((int) midBlock.getX(), (int) midBlock.getY(), 75);
        d.drawCircle((int) midBlock.getX(), (int) midBlock.getY(), 50);
        line1.drawOn(d);
        line2.drawOn(d);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
