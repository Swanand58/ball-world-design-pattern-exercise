package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

public class CompositeStrategy implements IUpdateStrategy {

    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;

    private CompositeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     *  Ball responds to the property change support indicating that property has changed.
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = StraightStrategy.make();
            children[1] = VerticalStrategy.make();
            ONLY = new CompositeStrategy(children);
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "composite";
    }

    @Override
    public void updateState(Ball context) {
        for (IUpdateStrategy child: children) {
            child.updateState(context);
        }
    }
}
