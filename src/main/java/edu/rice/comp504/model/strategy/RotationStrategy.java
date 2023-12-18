package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class RotationStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;

    private RotationStrategy() {
        dims = BallWorldStore.getCanvasDims();
    }

    /**
     * Only makes 1 rotation strategy.
     * @return The rotation strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new RotationStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "rotation";
    }

    @Override
    public void updateState(Ball context) {

        Point center = new Point(dims.x / 2, dims.y / 2);
        Point position = context.getLocation();

        double theta = Math.PI / 7;

        double x = center.x + Math.cos(theta) * (position.x - center.x) - Math.sin(theta) * (position.y - center.y);
        double y = center.y + Math.sin(theta) * (position.x - center.x) + Math.cos(theta) * (position.y - center.y);

        context.setLocation(new Point((int) x, (int) y));
    }
}
