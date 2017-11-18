package org.firstinspires.ftc.teamcode;

import junit.framework.TestCase;

import static org.junit.Assert.*;

/**
 * Created by wolfie on 10/21/17.
 */


public class ProcessorTest extends TestCase {
    MyProcessor unit = new MyProcessor();
    @org.junit.Test
    public void testTrue() {
        assertEquals(true, true);
    }

    @org.junit.Test
    public void testTurning() {
        double actual = (new MyProcessor()).turning(0, -90);
        assertEquals(-0.4, actual);
    }

    @org.junit.Test
    public void testTurning90() {
        double actual = (new MyProcessor()).turning(0, 90);
        assertEquals(0.4, actual);
    }

    @org.junit.Test
    public void testTurning888() {
        double actual = (new MyProcessor()).turning(0, 90);
        assertEquals(0.4, actual);
    }

    @org.junit.Test
    public void testcheckJewel() {
        int actual = (new MyProcessor()).checkJewel(true, true);
        assertEquals(-15, actual);

    }
    @org.junit.Test
    public void testcheckJewel2() {
        int actual = (new MyProcessor()).checkJewel(true, false);
        assertEquals(15, actual);

    }
    @org.junit.Test
    public void testcheckJewel3() {
        int actual = (new MyProcessor()).checkJewel(false, true);
        assertEquals(-15, actual);

    }
    @org.junit.Test
    public void testGoAngle() {
        unit.stubIT();
        unit.goAngle(2,20);

        Map expected = new Map();
        expected.stubbedInit();
        expected.motorRB.setPower(1);
        expected.motorLB.setPower(1);

    }
}