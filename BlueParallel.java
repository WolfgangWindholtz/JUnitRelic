package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;


//@Autonomous(name = "BlueParallel", group = "fjfrjkdk")
public class BlueParallel extends Processor{

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

        //knocks the correct jewel off according to our alliance colo

        //knocks the correct jewel off according to our alliance color
        knockJewel(false);

        goAngle(20,190);
        sleep(500);
        align(0);

        turn(-180);
        align(177);
        sleep(750);
        goAngle(1,180);
        raiseColorServo();

        goRangeLeft(13.2);

        gotoColumnRight();

        stopBotMotors();

        lowerColorServo();
        align(180);
        score(180);
        stopBotMotors();
    }
}