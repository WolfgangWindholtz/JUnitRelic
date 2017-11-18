package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by wolfie on 9/23/17.
 */
@Autonomous(name = "column Test" , group ="Concept")

public class ColumnTest extends Processor2{

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        checkCol();
        waitForStart();

        bot.columnToScore = RelicRecoveryVuMark.CENTER;

        //gotoColumn();
    }
}
