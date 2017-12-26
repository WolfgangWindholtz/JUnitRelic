package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Sushr on 12/15/2017.
 */
@Autonomous(name = "FullAutoRedParallel", group = "fjfrjkdk")
public class FullAutoRedParallel extends Processor{

    int count = 0;
    boolean touch = false;
    ElapsedTime time = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);

        waitForStart();
        checkVu();
        checkCol();
        grabGlyph();


        //knocks the correct jewel off according to our alliance color
        knockJewel(true);

        goAngle(20,0);

        turnHeading(-180);
        turnHeading(180);

        raiseColorServo();

        gotoColumnLeft();


        turnHeading(180);

        score();
        stopBotMotors();
    }
}