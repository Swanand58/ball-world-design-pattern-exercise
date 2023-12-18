package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;

public class StraightStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;

    private StraightStrategy() {
        dims = BallWorldStore.getCanvasDims();
        //System.out.println(dims);
    }

    /**
     *  Ball responds to the property change support indicating that property has changed.
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new StraightStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "straight";
    }

    @Override
    public void updateState(Ball context) {

        Point lo = context.getLocation();
        Point ve = context.getVelocity();
        int r = RandUtil.getRnd(20,20);
        if (context.getStrategy().getName() == "composite") {
            context.setRadius(r);
        }
        if (lo.x + context.getRadius() + ve.x > dims.x) {
            context.setLocation(new Point(dims.x - context.getRadius(), lo.y));
            context.setVelocity(new Point(- ve.x, ve.y));
        } else if (lo.x - context.getRadius() + ve.x < 0) {
            context.setLocation(new Point(context.getRadius(), lo.y));
            context.setVelocity(new Point(- ve.x, ve.y));
        } else {

            context.setLocation(new Point(context.getLocation().x + ve.x, context.getLocation().y));

        }
    }

}
