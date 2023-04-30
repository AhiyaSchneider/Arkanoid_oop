package LevelsBackScreens;

import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class LevelDBackScreen - implements Sprite.
 */
public class LevelDBackScreen implements Sprite {
    private boolean sunny = false, rainy = true, lightning;
    private int frames;
    private int rainDrop = 0;
    private int divider = 0;


    @Override
    public void drawOn(DrawSurface d) {
        // draw the background
        d.setColor(new Color(26, 120, 180));
        d.fillRectangle(15, 15, 770, 585);
        if (rainy) {
            if (!sunny) {
                drawSun(d);
            }
            d.setColor(Color.white);
            for (int i = 0; i <= 80; i++) {
                for (int j = 0; j < 9; j++) {
                    if ((i + rainDrop) % 4 == 0) {
                        d.fillOval((140 -  i) + 10 * j, 360 + (i * 4), 2, 4);
                        d.fillOval((600 - i) + 10 * j, 250 + (i * 6), 2, 4);
                    }
                }
            }
            Color color = new Color(193, 187, 187);
            d.setColor(color);
            d.fillCircle(600, 250, 30);
            d.fillCircle(620, 280, 30);
            d.fillCircle(140, 360, 30);
            d.fillCircle(160, 380, 30);
            color = new Color(186, 182, 182);
            d.setColor(color);
            d.fillCircle(640, 250, 30);
            d.fillCircle(180, 350, 30);
            color = new Color(175, 169, 169);
            d.setColor(color);
            d.fillCircle(660, 280, 30);
            d.fillCircle(680, 250, 30);
            d.fillCircle(200, 380, 35);
            d.fillCircle(220, 350, 30);
            if (lightning) {
                d.setColor(Color.white);
                d.drawLine(600, 15, 350, 180);
                d.drawLine(350, 180, 450, 400);
                d.drawLine(450, 400, 200, 560);
            }
        }
        if (sunny) {
            drawSun(d);
        }
        d.setColor(new Color(25, 128, 6));
        d.fillRectangle(15, 560, 770, 40);
    }

    private void drawSun(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillCircle(670, 290, 60);
        d.setColor(Color.orange);
        d.fillCircle(670, 290, 54);
        for (int i = 0; i < 9; i++) {
            d.setColor(new Color(255 - 4 * i, 169 - 5 * i, 8 + 2 * i));
            d.fillCircle(670, 290, 46 - i * 5);
        }
    }

    @Override
    public void timePassed() {
        frames++;
        if (!rainy) {
            if (frames == 200) {
                frames = 0;
                rainy = true;
                sunny = false;
            }
        }
        if (frames % 200 == 0) {
            lightning = true;
        }
        if (lightning && (frames - 20) % 200 == 0) {
            lightning = false;
        }
        if (frames == 300) {
            divider = 11;
        }
        if (frames == 600) {
            divider = 18;
            sunny = !sunny;
        }
        if (frames % (20 - divider) == 0) {
            rainDrop++;
            if (rainDrop == 4) {
                rainDrop = 0;
            }
        }
        if (frames == 900) {
            rainy = false;
            sunny = true;
            frames = 0;
        }
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
