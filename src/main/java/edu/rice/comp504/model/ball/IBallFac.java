package edu.rice.comp504.model.ball;

/**
 * A factory that makes balls.
 */
public interface IBallFac {
    /**
     * Makes a ball.
     * The ball type.
     * @return A Ball
     */
    Ball make();
}
