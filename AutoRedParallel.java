package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "redParallel", group = "fjfrjkdk")
public class AutoRedParallel extends Processor{

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
        sleep(500);
        align(0);
        turn(-180);
        align(180);
        sleep(500);
        raiseColorServo();
        align(180);
        //adjust(33.75);
        //adjust(33.75);
        //adjust(33.75);
        drivingRangeForwardRed();
        drivingRangeBackRed();
        drivingRangeForwardRed();
        align(180);
        align(180);
        align(180);
        sleep(500);
        gotoColumnLeft();



        bot.colorServo.setPosition(0);

        sleep(500);
        align(180);
        //driveToDistance();
        score();
        stopBotMotors();
    }
}