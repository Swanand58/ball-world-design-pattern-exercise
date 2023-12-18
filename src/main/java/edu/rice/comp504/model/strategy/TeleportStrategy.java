package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;

public class TeleportStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;

    private TeleportStrategy() {
        dims = BallWorldStore.getCanvasDims();
    }

    /**
     * Only makes 1 teleport strategy.
     * @return The teleport strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new TeleportStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public void updateState(Ball context) {

        int radius = context.getRadius();

        Point loc = new Point(RandUtil.getRnd(0 + radius, dims.x - radius), RandUtil.getRnd(0 + radius, dims.y - radius));
        context.setLocation(new Point(loc.x, loc.y));
    }
}
