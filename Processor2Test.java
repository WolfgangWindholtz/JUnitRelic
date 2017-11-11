package org.firstinspires.ftc.teamcode;

import junit.framework.TestCase;

public class Processor2Test extends TestCase {
    @org.junit.Test
    public void testTrue(){
        assertEquals(true,true);
    }

    @org.junit.Test
    public void testForward(){
        // create a Processor2
        // cal: forward()
        // see if motor power has been set.

        Processor2 target = new MyProcessor2();
        target.init();
        target.forward(3000);

        assertEquals(true,true);
    }


}
