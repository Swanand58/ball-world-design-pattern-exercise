package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class StopStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;

    public StopStrategy() {
        dims = BallWorldStore.getCanvasDims();
    }

    /**
     * Only makes 1 stop strategy.
     * @return The stop strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new StopStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public void updateState(Ball context) {

    }
}
