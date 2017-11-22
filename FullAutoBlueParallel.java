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
        sleep(500);

        knockJewel(false);

        //forward(300);
        // go in front of the cyrptograph
        //opposite direction of blue

        goAngle(20, 180);
        turn(180);

        goAngle(5,90);
        //gotoColumnRight();


        //score();
    }
}