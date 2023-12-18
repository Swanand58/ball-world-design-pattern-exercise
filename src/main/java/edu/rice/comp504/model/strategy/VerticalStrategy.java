package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class VerticalStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private VerticalStrategy() {
        dims = BallWorldStore.getCanvasDims();
    }

    /**
     *  Ball responds to the property change support indicating that property has changed.
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new VerticalStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "vertical";
    }

    @Override
    public void updateState(Ball context) {
        Point pos = context.getLocation();
        Point ve = context.getVelocity();
        if (pos.y + context.getRadius() + ve.y > dims.y) {
            context.setLocation(new Point(pos.x, dims.y - context.getRadius()));
            context.setVelocity(new Point(ve.x, - ve.y));
        } else if (pos.y - context.getRadius() + ve.y < 0) {
            context.setLocation(new Point(pos.x, context.getRadius()));
            context.setVelocity(new Point(ve.x, - ve.y));
        } else {
            context.setLocation(new Point(context.getLocation().x, context.getLocation().y + ve.y));
        }
    }
}
