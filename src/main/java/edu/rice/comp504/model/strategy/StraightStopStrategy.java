package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class StraightStopStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;

    private static Point dims;

    private int a = 1;

    private StraightStopStrategy() {
        dims = BallWorldStore.getCanvasDims();
        a = 1;
    }

    /**
     * Only makes 1 straightstop strategy.
     * @return The straightstop strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new StraightStopStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "straightstop";
    }

    @Override
    public void updateState(Ball context) {
        a *= -1;
        if (a > 0) {
            StraightStrategy.make().updateState(context);
        } else {
            StopStrategy.make().updateState(context);
            StopStrategy.make().updateState(context);
        }
    }
}
