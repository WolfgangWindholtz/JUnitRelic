package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by wolfie on 12/15/17.
 */
@Autonomous(name = "TOOCh  " , group ="LOOGE")

public class TOOCH extends Processor{
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
            telemetry.addData("touch",bot.touchSensor.isPressed());
            telemetry.update();

        }

    }
}
