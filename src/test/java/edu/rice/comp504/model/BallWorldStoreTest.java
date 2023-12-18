package edu.rice.comp504.model;

import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.model.strategy.*;
import edu.rice.comp504.util.RandUtil;
import junit.framework.TestCase;

import java.awt.*;

public class BallWorldStoreTest extends TestCase {

    public void testUpdateBallWorld() {

        BallWorldStore.setCanvasDims(new Point(800, 800));

        Point loc = new Point(100, 100);
        Point velocity = new Point(10, 10);

        Ball ball1 = new Ball(loc, 10, velocity, "blue");
        ball1.setStrategy(StraightStrategy.make());
        ball1.getStrategy().updateState(ball1);

        Ball ball2 = new Ball(new Point(100, 100), 10, velocity, "blue");
        ball2.setStrategy(VerticalStrategy.make());
        ball2.getStrategy().updateState(ball2);


        assertEquals(new Point(loc.x + 10, loc.y), ball1.getLocation());
        assertEquals(new Point(loc.x, loc.y + 10), ball2.getLocation());

        //rotation
        Point center = new Point(400, 400);
        Ball ball3 = new Ball(new Point(100, 100), 10, velocity, "blue");
        ball3.setStrategy(RotationStrategy.make());
        ball3.getStrategy().updateState(ball3);

        double theta = Math.PI / 7;
        double x = center.x + Math.cos(theta) * (loc.x - center.x) - Math.sin(theta) * (loc.y - center.y);
        double y = center.y + Math.sin(theta) * (loc.x - center.x) + Math.cos(theta) * (loc.y - center.y);

        assertEquals(new Point((int) x, (int) y), ball3.getLocation());

        //Teleport
        int radius = 10;
        Ball ball4 = new Ball(new Point(100, 100), radius, new Point(10, 10), "blue");
        ball4.setStrategy(TeleportStrategy.make());
        ball4.getStrategy().updateState(ball1);

        Point dims = BallWorldStore.getCanvasDims();
        Point loc4 = ball4.getLocation();

        assertTrue(loc4.x >= radius && loc4.x <= dims.x - radius);
        assertTrue(loc4.y >= radius && loc4.y <= dims.y - radius);

        //ZigZag
        Point loc5 = new Point(100, 100);
        Point velocity5 = new Point(10, 10);
        Ball ball5 = new Ball(loc5, 10, velocity5, "blue");
        ball5.setStrategy(ZigZagStrategy.make());
        ball5.getStrategy().updateState(ball5);

        Point expectedLocation = new Point(loc.x, loc.y+10);
        assertEquals(expectedLocation, ball5.getLocation());

        //Composite Strategy
        Ball ball6 = new Ball(loc, 10, velocity, "blue");
        ball6.setStrategy(CompositeStrategy.make());
        ball6.getStrategy().updateState(ball6);

        assertEquals(new Point(loc.x + 10, loc.y + 10), ball6.getLocation());

        //Stop Strategy
        Ball ball7 = new Ball(loc, 10, velocity, "blue");
        ball7.setStrategy(StopStrategy.make());
        ball7.getStrategy().updateState(ball7);

        assertEquals(loc, ball4.getLocation());


        //ZigZagStop
        Point loc9 = new Point(100, 100);
        Point velocity9 = new Point(10, 10);
        Ball ball9 = new Ball(loc9, 10, velocity9, "blue");
        ball9.setStrategy(ZigZagStopStrategy.make());
        ball9.getStrategy().updateState(ball9);

        Point expectedLocation9 = new Point(100, 100);

        assertEquals(expectedLocation9, ball9.getLocation());

        //StraightStop

        Point loc10 = new Point(100, 100);
        Point velocity10 = new Point(10, 10);
        Ball ball10 = new Ball(loc10, 10, velocity10, "blue");
        ball10.setStrategy(StraightStopStrategy.make());
        ball10.getStrategy().updateState(ball10);

        Point expectedLocation10 = new Point(100, 100);

        assertEquals(expectedLocation10, ball10.getLocation());


        //Rotation Stop

        Point loc11 = new Point(100, 100);
        Point velocity11 = new Point(10, 10);
        Ball ball11 = new Ball(loc11, 10, velocity11, "blue");
        ball11.setStrategy(RotatingStopStrategy.make());
        ball11.getStrategy().updateState(ball11);

        Point expectedLocation11 = new Point(100, 100);

        assertEquals(expectedLocation11, ball1.getLocation());

        // Drunk Rotate

        Point loc8 = new Point(100, 100);
        Point velocity8 = new Point(10, 10);

        Ball ball8 = new Ball(loc8, 10, velocity8, "blue");
        ball8.setStrategy(DrunkRotateStrategy.make());
        ball8.getStrategy().updateState(ball8);

        Point expectedLocation2 = new Point(110, 100);

        assertTrue(expectedLocation2.equals(ball8.getLocation()));


    }

    public void testLoadBall() {
        BallWorldStore ballWorldStore = new BallWorldStore();

        Ball ball1 = ballWorldStore.loadBall("straight", "ball");
        assertSame("straight", ball1.getStrategy().getName());

        Ball ball2 = ballWorldStore.loadBall("rotation", "ball");
        assertSame("rotation", ball2.getStrategy().getName());

        Ball ball3 = ballWorldStore.loadBall("stop", "ball");
        assertSame("stop", ball3.getStrategy().getName());

        Ball ball4 = ballWorldStore.loadBall("teleport", "ball");
        assertSame("teleport", ball4.getStrategy().getName());

        Ball ball5 = ballWorldStore.loadBall("zigzag", "ball");
        assertSame("zigzag", ball5.getStrategy().getName());

        Ball ball6 = ballWorldStore.loadBall("composite", "ball");
        assertSame("composite", ball6.getStrategy().getName());

        Ball ball7 = ballWorldStore.loadBall("straightstop", "ball");
        assertSame("straightstop", ball7.getStrategy().getName());

        Ball ball8 = ballWorldStore.loadBall("rotatestop", "ball");
        assertSame("rotatestop", ball8.getStrategy().getName());

        Ball ball9 = ballWorldStore.loadBall("zigzagstop", "ball");
        assertSame("zigzagstop", ball9.getStrategy().getName());

        Ball ball10 = ballWorldStore.loadBall("drunkrotate", "ball");
        assertSame("drunkrotate", ball10.getStrategy().getName());

        Ball ball11 = ballWorldStore.loadBall("vertical", "ball");
        assertSame("vertical", ball11.getStrategy().getName());

    }
}