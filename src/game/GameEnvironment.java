package game;

import collidables.CollisionInfo;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;

/**
 * class 'game.GameEnvironment' - have colliding point and array list of the collidable obj.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class GameEnvironment {
    private Point collidingPoint;
    private ArrayList<Collidable> collidableList;

    /**
     * game.GameEnvironment - constructor to this class with no param.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>(0);
    }

    /**
     * addCollidable - adding collidable object to list.
     * @param c - collidable object.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * removeCollidable - remove given collidable from the list.
     * @param c - the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * getClosestCollision - getting the closest collision point in the list.
     * @param trajectory - the line.
     * @return collisioninfo - info of the collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int listSize = collidableList.size();
        //check if there are collidable obj.
        if (listSize == 0) {
            //if there are not return null.
            return null;
        }
        Point susPoint;
        //set a suspect point.
        int  counter = 0;
        susPoint = trajectory.closestIntersectionToStartOfLine(collidableList.get(counter).getCollisionRectangle());
        //while sus point is null get the next one if reach to end of the array return null.
        while (susPoint == null) {
            //check if the start of the line is on the rectangle lines.
            if (collidableList.get(counter).getCollisionRectangle().isPointOnRectangleLines(trajectory.start())) {
                collidingPoint = trajectory.start();
                CollisionInfo collisionInfo = new CollisionInfo(collidingPoint, collidableList.get(counter));
                return collisionInfo;
            }
            counter++;
            if (counter == listSize) {
                return null;
            }
            susPoint = trajectory.closestIntersectionToStartOfLine(collidableList.get(counter).getCollisionRectangle());
        }
        //set the suspect point as the closest point.
        Point closestP = susPoint;
        int currentMin = counter;
        //check if there is closer collision point.
        for (int i = counter; i < listSize; i++) {
            //check if the start of the line is on the rectangle lines.
            if (collidableList.get(i).getCollisionRectangle().isPointOnRectangleLines(trajectory.start())) {
                collidingPoint = trajectory.start();
                CollisionInfo collisionInfo = new CollisionInfo(collidingPoint, collidableList.get(i));
                return collisionInfo;
            }
            susPoint = trajectory.closestIntersectionToStartOfLine(collidableList.get(i).getCollisionRectangle());
            if (susPoint == null) {
                continue;
            }
            if (closestP.distance(trajectory.start()) > susPoint.distance(trajectory.start())) {
                closestP = susPoint;
                currentMin = i;
            }
        }
        collidingPoint = closestP;
        //setting new collision info.
        CollisionInfo collisionInfo = new CollisionInfo(collidingPoint, collidableList.get(currentMin));
        return collisionInfo;
    }

    /**
     * getCollidableList - getter to collidale list.
     * @return the list.
     */
    public ArrayList<Collidable> getCollidableList() {
        return collidableList;
    }
}
