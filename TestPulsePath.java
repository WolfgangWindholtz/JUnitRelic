package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by wolfie on 10/30/17.
 */
@Autonomous(name = "Auto Pulse 3  " , group ="Concept")

public class TestPulsePath extends Processor2{
    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        waitForStart();
        //goPulses(3);
    }
}
