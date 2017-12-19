package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by wolfie on 12/15/17.
 */
@Autonomous(name = "Color sensor Output  " , group ="LOOGE")

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
        int counttrue = 0;
        while(opModeIsActive()){
           /* if(!bot.touchSensor.getState()){
                counttrue++;
            }

            telemetry.addData("touch",bot.touchSensor.getState());
            telemetry.addData("count",counttrue);

            telemetry.addLine("true is not pressed");
            telemetry.addLine("false is pressed");

            telemetry.update();*/

        }

    }
}
