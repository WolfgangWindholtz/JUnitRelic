package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by wolfie on 11/17/17.
 */
@Autonomous(name = "Auto ODS" , group ="Concept")

public class Ods1 extends Processor{

    /**
     * Override this method and place your code here.
     * <p>
     * Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {

        bot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("ods 1",bot.disSensor1.getDistance(DistanceUnit.CM));
            telemetry.addData("ods 2",bot.disSensor2.getDistance(DistanceUnit.CM));
            telemetry.update();

        }
    }
}
