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
        checkVu();
        checkCol();
        grabGlyph();
        //knocks the correct jewel off according to our alliance color
        knockJewel(true);

        goAngle(20,0);
        sleep(1000);
        turnHeading(-90);

        sleep(1000);


        gotoColumnLeft();

        stopBotMotors();
        sleep(1000);

        //releases the glyph and pushes the glyph into the cryptobox
        score();
    }
}