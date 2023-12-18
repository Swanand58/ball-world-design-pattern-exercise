package edu.rice.comp504.model;

import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.model.strategy.StrategyFac;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/**
 * A store containing current Ball World.
 */
public class BallWorldStore {
    private PropertyChangeSupport pcs;
    private static Point dims;
    private static StrategyFac ONLY;

    /**
     *  Ball responds to the property change support indicating that property has changed.
     */
    public static StrategyFac getOnlyFac() {
        if (ONLY == null) {
            ONLY = new StrategyFac();
        }
        return ONLY;
    }

    /**
     * Constructor.
     */
    public BallWorldStore() {
        pcs = new PropertyChangeSupport(this);
        dims = new Point(0,0);
    }

    /**
     * Get the canvas dimensions.
     * @return The canvas dimensions
     */
    public static Point getCanvasDims() {
        return dims;
    }

    /**
     * Set the canvas dimensions.
     * @param dims1 The canvas width (x) and height (y).
     */
    public static void setCanvasDims(Point dims1) {
        dims = dims1;
    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world.
     */
    public PropertyChangeListener[] updateBallWorld() {
        // TODO: fill in
        pcs.firePropertyChange(null, null, null);
        return null;
    }

    /**
     * Load a ball into the Ball World.
     * @param body  The REST request body has the strategy names.
     * @param type The type of object (e.g. ball) created.
     * @return A ball
     */
    public Ball loadBall(String body, String type) {
        // TODO: fill in

        int radius = RandUtil.getRnd(10,20);

        Point loc = new Point(RandUtil.getRnd(0 + radius, dims.x - radius), RandUtil.getRnd(0 + radius, dims.y - radius));


        if (body == "rotation") {
            loc = new Point(RandUtil.getRnd(80 + radius, 350 - radius), RandUtil.getRnd(80 + radius, 350 - radius));
        }

        Point vel = new Point(RandUtil.getRnd(20,20), RandUtil.getRnd(20,20));
        int index = new Random().nextInt(9);
        String color = Ball.availColors[index];
        Ball tempBall = new Ball(loc, radius, vel, color);
        tempBall.setStrategy(getOnlyFac().make(body));
        addBallToStore(tempBall);
        return tempBall;

    }

    /**
     * Switch the strategy of switcher balls
     * @param body  The REST request strategy.
     */
    public PropertyChangeListener[] switchStrategy(String body) {
        // TODO: fill in
        return null;
    }

    /**
     * Add a ball that will listen for a property change (i.e. time elapsed)
     * @param pcl  The ball
     */
    private void addBallToStore(PropertyChangeListener pcl) {
        // TODO: fill in
        pcs.addPropertyChangeListener(pcl);
    }

    /**
     * Remove all balls from listening for property change events for a particular property.
     */
    public void removeBallsFromStore() {
        // TODO: fill in
        pcs = new PropertyChangeSupport(this);
    }
}
