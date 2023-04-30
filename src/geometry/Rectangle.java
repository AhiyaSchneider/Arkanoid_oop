package geometry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import characters.Ball;

/**
 * class 'geometry.Rectangle' - the class store information of a rectangle in the frame.
 *
 * @author Ahiya Schneider
 * Date: 11.04.2022
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Color color;

    /**
     * geometry.Rectangle - constructor to rectangle class.
     * @param upperLeft - the upper left point.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        //default will be pink.
        color = Color.PINK;
    }

    /**
     * geometry.Rectangle - constructor to rectangle class.
     * @param upperLeft - the upper left point.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     * @param color - the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * intersectionPoints - getting the intersection points with a given line and the edge of the obj.
     * @param line - line to compare with.
     * @return an array of collision points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //declare on new array list.
        List<Point> intersectingPoints = new ArrayList<>();
        //getting the lines of the edges.
        Line rectangleDownLine = rectangleDownLine();
        Line rectangleUpLine = rectangleUpLine();
        Line rectangleLeftLine = rectangleLeftLine();
        Line rectangleRightLine = rectangleRightLine();
        //checking if the given line intersecting with the edges lines and add the points to the list.
        if (line.intersectionWith(rectangleDownLine) != null) {
            intersectingPoints.add(line.intersectionWith(rectangleDownLine));
        }
        if (line.intersectionWith(rectangleUpLine) != null) {
            intersectingPoints.add(line.intersectionWith(rectangleUpLine));
        }
        if (line.intersectionWith(rectangleLeftLine) != null) {
            intersectingPoints.add(line.intersectionWith(rectangleLeftLine));
        }
        if (line.intersectionWith(rectangleRightLine) != null) {
            intersectingPoints.add(line.intersectionWith(rectangleRightLine));
        }
        return intersectingPoints;
    }

    /**
     * isPointOnRectangleLines - check if a point is on the rectangle lins.
     * @param point - given point.
     * @return boolean.
     */
    public boolean isPointOnRectangleLines(Point point) {
        if (rectangleDownLine().isPointInLine(point) || rectangleUpLine().isPointInLine(point)) {
            return true;
        }
        if (rectangleRightLine().isPointInLine(point) || rectangleLeftLine().isPointInLine(point)) {
            return true;
        }
        return false;
    }

    /**
     * rectangleDownLine = get the down line.
     * @return geometry.Line - the anted line.
     */
    public Line rectangleDownLine() {
        Point start = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point end = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        return new Line(start, end);
    }

    /**
     * rectangleUpLine - get the upper line.
     * @return geometry.Line - the wanted line.
     */
    public Line rectangleUpLine() {
        Point start = new Point(upperLeft.getX(), upperLeft.getY());
        Point end = new Point(upperLeft.getX() + width, upperLeft.getY());
        return new Line(start, end);
    }

    /**
     * rectangleRightLine - get the right side line.
     * @return geometry.Line - the wanted line.
     */
    public Line rectangleRightLine() {
        Point start = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point end = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        return new Line(start, end);
    }

    /**
     * rectangleLeftLine - get the left side line.
     * @return geometry.Line - the wanted line.
     */
    public Line rectangleLeftLine() {
        Point start = new Point(upperLeft.getX(), upperLeft.getY());
        Point end = new Point(upperLeft.getX(), upperLeft.getY() + height);
        return new Line(start, end);
    }

    /**
     * ballInRectangle - check if the ball is inside the rectangle.
     * @param ball - given ball.
     * @return boolean.
     */
    public boolean ballInRectangle(Ball ball) {
        int x = ball.getX(), y = ball.getY();
        int downStartX = (int) rectangleDownLine().start().getX();
        int downEndX = (int) rectangleDownLine().end().getX();
        int leftStartY = (int) rectangleLeftLine().start().getY();
        int leftEndY = (int) rectangleLeftLine().end().getY();
        if (x >= downStartX && x <= downEndX && y >= leftEndY && y <= leftStartY) {
            return true;
        }
        return false;
    }

    /**
     * drawOn - draw rectangle on surface.
     * @param d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        int x = (int) upperLeft.getX(), y = (int) upperLeft.getY();
        d.fillRectangle(x, y, (int) width, (int) height);
    }

    /**
     * getWidth - getter to width.
     * @return width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * getHeight - getter to height.
     * @return height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * getUpperLeft - getter to upper left point.
     * @return upperLeft - the point.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * setWidth - setter to width.
     * @param width - new value to width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * setHeight - setter to height.
     * @param height - new value to height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * setColor - setter to the color.
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * setUpperLeft - setter to upperLeft value.
     * @param upperLeft - new value to upperLeft.
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
}
