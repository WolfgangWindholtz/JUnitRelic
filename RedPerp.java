package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Sushr on 12/15/2017.
 */
@Autonomous(name = "redPrep", group = "fjfrjkdk")
public class RedPerp extends Processor{

    int count = 0;
    boolean touch = false;
    ElapsedTime time = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        waitForStart();
        //bot.x = angularOffset();
        checkVu();
        checkCol();
        grabGlyph();

        //knocks the correct jewel off according to our alliance color
        knockJewel(true);

        goAngle(20, 0);
        sleep(500);
        align(0);
        turn(-90);
        sleep(500);

        align(-90);
        sleep(500);

        raiseColorServo();

        goRangeRight(13.2);
        align(-90);

        gotoColumnLeft();

        stopBotMotors();

        lowerColorServo();

        align(-90);
        sleep(500);

        //releases the glyph and pushes the glyph into the cryptobox
        score(-90);
        stopBotMotors();
    }
}