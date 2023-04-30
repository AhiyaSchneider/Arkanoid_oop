package animation;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;

import java.awt.Color;

/**
 * class EndScreen - implements Animation.
 */
public class EndScreen implements Animation {
    private boolean win;
    private boolean stop;
    private int score;
    private Rectangle rectangle;
    private String massage;
    private Color textColor;
    private Color rectColor;
    private int frames = 0;

    /**
     * EndScreen - constructor.
     * @param win - indicate if the user win.
     * @param score - indicate the score.
     */
    public EndScreen(boolean win, int score) {
        this.stop = false;
        this.win = win;
        if (win) {
            rectColor = Color.pink;
            textColor = Color.black;
            massage = "You Win! Your score is ";
        } else {
            rectColor = Color.black;
            textColor = Color.white;
            massage = "Game Over. Your score is ";
        }
        Point upperLeft = new Point(0, 0);
        rectangle = new Rectangle(upperLeft, 800, 600, rectColor);
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        rectangle.drawOn(d);
        d.setColor(textColor);
        d.drawText(30, 250, massage + score, 40);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
