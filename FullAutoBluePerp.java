package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by wolfie on 9/23/17.
 */
@Autonomous(name = "FullAutoBluePerp" , group ="Concept")

public class FullAutoBluePerp extends Processor{

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
        // get off the stone\\
        goAngle(20,90);

        turn(90);

        gotoColumnRight();// need to come at the column from the right for this to work

        score();

    }
}
