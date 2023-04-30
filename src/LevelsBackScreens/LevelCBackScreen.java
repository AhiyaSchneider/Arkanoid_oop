package LevelsBackScreens;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class LevelCBackScreen - implements Sprite.
 */
public class LevelCBackScreen implements Sprite {
    private boolean flagLight, flagDoIt, flagBlack;
    private int frames = 0;
    private int divider = 0;
    private Color rectColor;
    private Rectangle rectangle;

    /**
     * LevelCBackScreen - constructor to class.
     */
    public LevelCBackScreen() {
        this.rectColor = new Color(3, 246, 142);
        this.rectangle = new Rectangle(new Point(0, 0), 800, 600, rectColor);
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (!flagBlack) {
            rectangle.drawOn(d);
            drawSunMoon(d);
            drawBuilding(d);
            if (flagDoIt) {
                d.setColor(Color.cyan);
                d.drawText(250, 300, "DO IT!!!!", 100);
            }
        } else {
            d.setColor(Color.black);
            d.fillRectangle(0, 0, 800, 600);
            drawSunMoon(d);
            d.setColor(Color.white);
            d.fillOval(40, 90, 4, 6);
            d.fillOval(80, 150, 4, 6);
            d.fillOval(100, 44, 4, 6);
            d.fillOval(150, 66, 4, 6);
            d.fillOval(260, 78, 4, 6);
            d.fillOval(400, 300, 4, 6);
            d.fillOval(500, 120, 4, 6);
            d.fillOval(750, 240, 6, 8);
            d.fillOval(50, 90, 3, 6);
            d.fillOval(467, 150, 3, 6);
            d.fillOval(333, 289, 6, 8);
            d.fillOval(689, 300, 3, 7);
            d.fillOval(367, 78, 3, 6);
            d.fillOval(478, 300, 3, 6);
            d.fillOval(566, 120, 3, 6);
            d.fillOval(700, 240, 3, 6);
            drawBuilding(d);
            d.setColor(new Color(245, 171, 83));
            if (frames > 300) {
                d.fillOval(200 + (frames / 5), 3 * (frames - 285), 9, 19);
            }
            if (frames > 600) {
                d.fillOval(400 + (frames / 5), 3 * (frames - 585), 10, 18);
            }
            d.fillOval(90 + (frames / 4), 3 * frames, 10, 18);
        }
    }

    /**
     * drawBuilding - draw the building.
     * @param d - surface.
     */
    private void drawBuilding(DrawSurface d) {
        Color color = new Color(42, 40, 40);
        d.setColor(color);
        d.fillRectangle(80, 400, 110, 200);
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if ((i + j + divider) % 3 == 0) {
                    d.setColor(new Color(176, 170, 170));
                    d.fillRectangle(90 + j * 20, 410 + i * 40, 10, 30);
                    d.setColor(new Color(234, 230, 10));
                    d.fillCircle(95 + j * 20, 415 + i * 40, 3);
                } else {
                    d.setColor(Color.darkGray);
                    d.fillRectangle(90 + j * 20, 410 + i * 40, 10, 30);
                }
            }
        }
        color = new Color(52, 50, 50);
        d.setColor(color);
        d.fillRectangle(117, 340, 35, 60);
        color = new Color(64, 61, 61);
        d.setColor(color);
        d.fillRectangle(130, 150, 10, 190);
        if (flagLight) {
            d.setColor(Color.red);
            d.fillCircle(135, 140, 10);
        } else {
            d.setColor(Color.white);
            d.fillCircle(135, 140, 10);
        }
    }

    /**
     * drawSun - draw sun.
     * @param d - surface.
     */
    private void drawSunMoon(DrawSurface d) {
        int t, x, y;
        if (frames <= 400) {
            t = frames / 4;
        } else {
            t = 100 - (frames - 400) / 4;
        }
        y = 200 - t;
        x = frames + 20;
        if (flagBlack) {
            d.setColor(new Color(183, 183, 183));
            d.fillCircle(x, y, 60);
        } else {
            d.setColor(Color.yellow);
            d.fillCircle(x, y, 60);
            d.setColor(Color.orange);
            d.fillCircle(x, y, 54);
            for (int i = 0; i < 9; i++) {
                d.setColor(new Color(255 - 4 * i, 169 - 5 * i, 8 + 2 * i));
                d.fillCircle(x, y, 46 - i * 5);
            }
        }
    }

    @Override
    public void timePassed() {
        frames++;
        if (flagBlack && frames >= 760) {
            flagBlack = false;
            frames = 0;
        } else {
            if (divider == 29) {
                divider = 0;
            }
            if (frames == 760) {
                frames = 0;
                flagBlack = true;
                flagDoIt = false;
                divider++;
            }
            if (frames == 300) {
                flagDoIt = false;
                divider++;
            }
            if (frames == 200) {
                flagDoIt = true;
                divider++;
            }
            if (frames % (30 - divider) == 0) {
                flagLight = !flagLight;
            }
        }

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
