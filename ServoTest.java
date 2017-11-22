package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by wolfie on 11/22/17.
 */
@Autonomous(name = "Serco te  " , group ="Concept")

public class ServoTest extends Processor{
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

        bot.jewelServo.setPosition(.2);
        sleep(2000);
        bot.jewelServo.setPosition(.4);
        sleep(2000);
        bot.jewelServo.setPosition(.6);
        sleep(2000);
        bot.jewelServo.setPosition(.8);
        sleep(2000);

        bot.jewelServo.setPosition(1);
        sleep(2000);

        bot.jewelServo.setPosition(0);
        sleep(2000);





    }
}
