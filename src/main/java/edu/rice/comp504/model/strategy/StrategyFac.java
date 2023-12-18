package edu.rice.comp504.model.strategy;

public class StrategyFac implements IStrategyFac {

    /**
     *  Ball responds to the property change support indicating that property has changed.
     */
    public IUpdateStrategy make(String type) {
        switch (type) {
            case "straight":
                return StraightStrategy.make();
            case "composite":
                return CompositeStrategy.make();
            case "stop":
                return StopStrategy.make();
            case "rotation":
                return RotationStrategy.make();
            case "teleport":
                return TeleportStrategy.make();
            case "zigzag":
                return ZigZagStrategy.make();
            case "straightstop":
                return StraightStopStrategy.make();
            case "rotatestop":
                return RotatingStopStrategy.make();
            case "zigzagstop":
                return ZigZagStopStrategy.make();
            case "drunkrotate":
                return DrunkRotateStrategy.make();
            default:
                return VerticalStrategy.make();
        }
    }
}
