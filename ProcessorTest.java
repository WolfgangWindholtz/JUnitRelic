package org.firstinspires.ftc.teamcode;

import junit.framework.TestCase;

import static org.junit.Assert.*;

/**
 * Created by wolfie on 10/21/17.
 */


public class ProcessorTest extends TestCase {
    @org.junit.Test
    public void testTrue() {
        assertEquals(true,true);
    }

    @org.junit.Test
    public void testTurning() {
        double actual = (new MyProcessor()).turning(0, 0, 0);
        assertEquals(0.0, actual);
    }
}