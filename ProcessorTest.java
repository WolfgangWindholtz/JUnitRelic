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
        double actual = (new MyProcessor()).turning(90, 1, 20);
        assertEquals(0.6, actual);
    }
    @org.junit.Test
    public void testcheckJewel(){
        int actual = (new MyProcessor()).checkJewel(true,true);
        assertEquals(15, actual);

    }
}