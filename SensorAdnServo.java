package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by wolfie on 12/16/17.
 */
@Autonomous(name = "sensorAndServo " , group ="FROOg")

public class SensorAdnServo extends Processor{


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







        bot.colorServo.setPosition(.5);
        sleep(2000);


        runtime.reset();

        //raises the Rev slides to pick the glyph off the ground to prevent dragging the glyph

        while(opModeIsActive()){
           /*telemetry.addData("colorsß blue", bot.colorSensor2.blue());
            telemetry.addData("colorsß red", bot.colorSensor2.red());
            telemetry.addData("colorsß alpha?", bot.colorSensor2.alpha());
            telemetry.addData("colorsß arbg?", bot.colorSensor2.argb());
            telemetry.addData("colorsß green?", bot.colorSensor2.green());*/
            telemetry.addData("distance",bot.colorSensor2.getDistance(DistanceUnit.CM));


            telemetry.update();
        }

    }
}