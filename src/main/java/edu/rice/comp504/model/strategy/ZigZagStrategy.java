package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class ZigZagStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;

    private int a;


    public ZigZagStrategy() {
        dims = BallWorldStore.getCanvasDims();
        a = 1;
    }

    /**
     * Only makes 1 zigzag strategy.
     * @return The zigzag strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new ZigZagStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "zigzag";
    }

    @Override
    public void updateState(Ball context) {
        a *= -1;
        if (a > 0) {
            StraightStrategy.make().updateState(context);
        } else {
            VerticalStrategy.make().updateState(context);
        }

    }
}
