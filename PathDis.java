package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by wolfie on 9/23/17.
 */
@Autonomous(name = "Auto Dis" , group ="Concept")

public class PathDis extends Processor{

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            recordTelemetry(1,1,bot.imu.getAngularOrientation(),1);
            //.addData("Cm",bot.sensorDistance.getDistance(DistanceUnit.CM));
            //telemetry.addData("Mm",bot.sensorDistance.getDistance(DistanceUnit.MM));
        }
    }
}
