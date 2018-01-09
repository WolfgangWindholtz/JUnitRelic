package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "BlueParallel", group = "fjfrjkdk")
public class AutoBlueParallel extends Processor{

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

        goAngle(24,160);
        sleep(500);
        align(0);
        sleep(500);
        turn(180);
        align(177);
        sleep(750);
        goAngle(1.8,180);
        raiseColorServo();
        drivingRangeForwardBlue();
        drivingRangeBackBlue();
        drivingRangeForwardBlue();
        align(180);
        align(180);
        align(180);


        gotoColumnRight();

        stopBotMotors();

        bot.colorServo.setPosition(0);


        sleep(500);
        align(180);
        //driveToDistance();
        score();
        stopBotMotors();    }
}