package edu.rice.comp504.adapter;

import junit.framework.TestCase;


public class DispatchAdapterTest extends TestCase {


    public void testLoadBall() {
        DispatchAdapter dis = new DispatchAdapter();
        int length1 = dis.balls.size();
        dis.loadBall("straight", "ball");
        int length2 = dis.balls.size();
        assertEquals(length2 - length1, 1);

    }

    public void testremoveAll() {
        DispatchAdapter dis = new DispatchAdapter();
        dis.loadBall("straight", "ball");
        dis.loadBall("rotation", "ball");
        dis.removeAll();
        assertEquals(dis.balls.size(), 0);
    }
}