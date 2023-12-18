package edu.rice.comp504.adapter;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {

    private static Point dims;
    public ArrayList<Ball> balls;
    public BallWorldStore ballWorldStore;

    /**
     * Constructor call.
     */
    public DispatchAdapter() {
        balls = new ArrayList<Ball>();
        ballWorldStore = new BallWorldStore();
    }

    /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y).
     */
    public void setCanvasDims(Point dims) {
        this.dims = dims;
        BallWorldStore.setCanvasDims(dims);
    }

    /**
     * Update the ball world.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] updateBallWorld() {
        // TODO: fill in
        ballWorldStore.updateBallWorld();
        return null;
    }

    /**
     * Load a ball into the paint world.
     * @param strat  The REST request strategy.
     * @param type The type of object to load on the canvas.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] loadBall(String strat, String type) {
        // TODO: fill in
        Ball ball = ballWorldStore.loadBall(strat, type);
        balls.add(ball);
        return null;
    }


    /**
     * Switch the strategy for switcher balls
     * @param strat  The REST request strategy.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] switchStrategy(String strat) {
        // TODO: fill in
        return null;
    }
    /**
     * remove the last ball from canvas.
     */
    public PropertyChangeListener[] remove() {
        int index = balls.size() - 1;
        balls.remove(index);
        return null;
    }


    /**
     * Remove all balls from listening for property change events for a particular property.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] removeAll() {
        // TODO: fill in
        ballWorldStore.removeBallsFromStore();
        balls.clear();
        return null;
    }

}
