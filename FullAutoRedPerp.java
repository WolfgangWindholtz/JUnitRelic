package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by wolfie on 9/23/17.
 */
@Autonomous(name = "FullAutoRedPrep" , group ="Concept")

public class FullAutoRedPerp extends Processor{

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

        knockJewel(true);

        //forward(300);
        // get off the stone
        goAngle(20, 90 );


        gotoColumnLeft();
        score();

    }
}
