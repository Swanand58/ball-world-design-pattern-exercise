package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class RotatingStopStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;

    private int a;

    private RotatingStopStrategy() {
        dims = BallWorldStore.getCanvasDims();
        a = 1;
    }

    /**
     * Only makes 1 rotatingstop strategy.
     * @return The rotatingstop strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new RotatingStopStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "rotatestop";
    }

    @Override
    public void updateState(Ball context) {
        a *= -1;
        if (a > 0) {
            RotationStrategy.make().updateState(context);

        } else {
            StopStrategy.make().updateState(context);
            StopStrategy.make().updateState(context);
        }
    }
}
