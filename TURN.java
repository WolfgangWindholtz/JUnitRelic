package org.firstinspires.ftc.teamcode;

/**
 * Created by wolfie on 11/17/17.
 */

public class TURN extends Processor2{
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

        turn(90);
    }
}
