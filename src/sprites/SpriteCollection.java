package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;

/**
 * class 'sprites.SpriteCollection' - in this class there is a list array of sprites and some methods on
 * this list.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class SpriteCollection {
    private ArrayList<Sprite> listArray;

    /**
     * sprites.SpriteCollection - constructor to this class with no given param.
     */
    public SpriteCollection() {
        listArray = new ArrayList<Sprite>(0);
    }

    /**
     * sprites.SpriteCollection - constructor to this class with a list array of sprite obj.
     * @param listArray - list array of sprite obj.
     */
    public SpriteCollection(ArrayList<Sprite> listArray) {
        this.listArray = listArray;
    }

    /**
     * addSprite - adding new sprite to list.
     * @param s - new sprite.
     */
    public void addSprite(Sprite s) {
        listArray.add(s);
    }

    /**
     * removeSprite - remove given sprite from collection.
     * @param s - given sprite.
     */
    public void removeSprite(Sprite s) {
        listArray.remove(s);
    }

    /**
     * getSpriteList - getter to the sprite list.
     * @return the list.
     */
    public ArrayList<Sprite> getSpriteList() {
        return listArray;
    }

    /**
     * notifyAllTimePassed - notify to all sprite obj that time passed.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> sprites = new ArrayList<>(listArray);
        for  (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * drawAllOn - drawing all the sprite obj on screen.
     * @param d - surface.
     */
    public void drawAllOn(DrawSurface d) {
        for  (Sprite sprite : listArray) {
            sprite.drawOn(d);
        }
    }
}
