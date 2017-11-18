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


        bot.glyphServo1.setPosition(0.47);
        bot.glyphServo2.setPosition(0.429);
        sleep(500);

        knockJewel(false);

        //forward(300);
        // go in front of the cyrptograph
        //opposite direction of blue

        encoderDrive(DRIVE_SPEED,-11,11,11,-11,10);

        turn(90);

        encoderDrive(.3,4,-4,-4,4,3);

        gotoColumnRight();
        score();
    }
}