package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class ZigZagStopStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;
    private int a;

    private ZigZagStopStrategy() {
        dims = BallWorldStore.getCanvasDims();
        a = 1;
    }

    /**
     * Only makes 1 zigzagstop strategy.
     * @return The zigzagstop strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new ZigZagStopStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "zigzagstop";
    }

    @Override
    public void updateState(Ball context) {
        a *= -1;
        if (a > 0) {
            ZigZagStrategy.make().updateState(context);
        } else {
            StopStrategy.make().updateState(context);
            StopStrategy.make().updateState(context);
        }
    }
}
