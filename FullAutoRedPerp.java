package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by wolfie on 9/23/17.
 */
@Autonomous(name = "FullAutoRedPerp" , group ="Concept")

public class FullAutoRedPerp extends Processor{

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        checkCol();
        waitForStart();
        checkCol();

        checkVu();

        bot.glyphServo1.setPosition(0.69);
        bot.glyphServo2.setPosition(0.35);
        sleep(1000);

        knockJewel(true);

        //forward(300);
        // get off the stone
        goAngle(35, 0 );

        turn(180);

        goAngle(5,90);

        gotoColumnLeft();
        score();

    }
}
