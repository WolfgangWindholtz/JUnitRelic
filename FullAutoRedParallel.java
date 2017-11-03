package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by wolfie on 9/23/17.
 */
@Autonomous(name = "FullAutoRedParallel" , group ="Concept")

public class FullAutoRedParallel extends Processor{

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        checkCol();
        waitForStart();
        checkCol();

        checkVu();

        knockJewel(true);

        //forward(300);
        // go in front of the cyrptograph

        turn(90);

        gotoColumnLeft();
    }
}
