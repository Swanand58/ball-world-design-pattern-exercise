package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class DrunkRotateStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;
    private int a;

    private DrunkRotateStrategy() {
        a = 1;
        dims = BallWorldStore.getCanvasDims();
    }

    /*** Only makes 1 drunkrotate strategy.
     * @return The drunkrotate strategy.
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new DrunkRotateStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "drunkrotate";
    }

    @Override
    public void updateState(Ball context) {
        a *= -1;

        if (a > 0) {
            RotationStrategy.make().updateState(context);
        } else {
            CompositeStrategy.make().updateState(context);
        }
    }
}
