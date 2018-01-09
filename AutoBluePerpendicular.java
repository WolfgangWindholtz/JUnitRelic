package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "BluePerpendicular", group = "jfjf")
public class AutoBluePerpendicular extends Processor{
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
        knockJewel(false);

        goAngle(20,180);

        align(0);

        turn(90);
        sleep(500);
        align(90);
        sleep(500);
        align(90);
        raiseColorServo();
        drivingRangeForwardBlue();
        drivingRangeBackBlue();


        goAngle(2,0);
        gotoColumnRight();

        stopBotMotors();


        bot.colorServo.setPosition(0);
        goAngle(0.25,180);
        sleep(500);
        align(90);
        //releases the glyph and pushes the glyph into the cryptobox
        score1(90);
        stopBotMotors();
    }
}