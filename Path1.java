package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by wolfie on 9/23/17.
 */
@Autonomous(name = "Auto 1" , group ="Concept")

public class Path1 extends Processor{

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        waitForStart();

        isSensorRed();
        telemetry.update();
        bot.jewelServo.setPosition( 1 );
        sleep(1000);

        isSensorRed();
        telemetry.update();
        bot.jewelServo.setPosition( .8 );
        sleep(1000);

        isSensorRed();
        telemetry.update();
        bot.jewelServo.setPosition( .6 );
        sleep(1000);

        isSensorRed();
        telemetry.update();
        bot.jewelServo.setPosition( .4 );
        sleep(1000);

        isSensorRed();
        telemetry.update();
        bot.jewelServo.setPosition(.2);
        sleep(1000);

        isSensorRed();
        telemetry.update();
        bot.jewelServo.setPosition(0);
        sleep(1000);

    }
}
