package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by wolfie on 9/23/17.
 */
@Autonomous(name = "FullAutoBlueParallel" , group ="Concept")

public class FullAutoBlueParallel extends Processor{

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        checkCol();
        waitForStart();
        checkCol();

        checkVu();


        bot.glyphServo1.setPosition(0.69);
        bot.glyphServo2.setPosition(0.35);
        sleep(2000);

        runtime.reset();
        while(runtime.milliseconds()<1000) {
            bot.slideMotor.setPower(-.8);
        }
        bot.slideMotor.setPower(0);

        knockJewel(false);

        //forward(300);
        // go in front of the cyrptograph
        //opposite direction of blue

        goAngle(30, 180);

        sleep(1000);
        turn(180);

        goAngle(13.5, 180);

        sleep(1000);
        gotoColumnRightEnc();

    }
}