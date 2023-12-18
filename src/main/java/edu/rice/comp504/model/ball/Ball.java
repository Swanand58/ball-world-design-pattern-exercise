package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The balls that will be drawn in the ball world.
 */
public class Ball implements PropertyChangeListener {
    private Point loc;
    private int radius;
    private Point vel;
    private String color;
    private IUpdateStrategy strategy;
    public static String[] availColors = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};
    /**
     * Constructor.
     * @param loc  The location of the ball on the canvas
     * @param radius The ball radius
     * @param vel  The ball velocity
     * @param color The ball color
     */
    public Ball(Point loc, int radius, Point vel, String color) {
        this.loc = loc;
        this.radius = radius;
        this.vel = vel;
        this.color = color;
        this.strategy = null;
    }

    /**
     * Get the ball color.
     * @return ball color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Set the ball color.
     * @param c The new ball color
     */
    public void setColor(String c) {
        this.color = c;
    }


    /**
     * Get the ball location in the ball world.
     * @return The ball location.
     */
    public Point getLocation() {
        return this.loc;
    }

    public String[] getColorField() {
        return this.availColors;
    }

    /**
     * Set the ball location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The ball coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }

    public IUpdateStrategy getStrategy() {
        return strategy;
    }


    public void setStrategy(IUpdateStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Get the velocity of the ball.
     * @return The ball velocity
     */
    public  Point getVelocity() {
        return this.vel;
    }

    /**
     * Set the velocity of the ball.
     * @param vel The new ball velocity
     */
    public void setVelocity(Point vel) {
        this.vel = vel;
    }


    /**
     * Get the radius of the ball.
     * @return The ball radius.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Set the radius of the ball.
     * @param r The ball radius.
     */
    public void setRadius(int r) {
        this.radius = r;
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @return True if there was a collision and false otherwise.
     */
    public boolean detectCollision() {
        // TODO: fill in

        Point p = BallWorldStore.getCanvasDims();
        if (loc.x <= radius || loc.y <= radius || loc.x + radius >= p.x || loc.y + radius >= p.y) {
            return true;
        }
        return false;
    }

    @Override
    /**
     *  Ball responds to the property change support indicating that property has changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: fill in
        strategy.updateState(this);

    }

}
