package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

@TeleOp(name = "XTele", group = "X")
public class Tele extends OpMode{
    TeleMap bot = new TeleMap();
    double xpow;
    double ypow;
    double zpow;
    double rightx;

    @Override
    public void init() {
        bot.init(hardwareMap);

    }

    public void readGamePad() {
        zpow = gamepad1.right_stick_x;//direction not actually
        ypow = gamepad1.left_stick_y;// variable names are incoorect
        xpow = gamepad1.left_stick_x;
        if(Math.abs(ypow)<.05){
            ypow = 0;

        }
        if(Math.abs(xpow)<.05){
            xpow = 0;

        }
    }

    @Override
    public void loop() {

        readGamePad();
        double mag = Math.sqrt(ypow * ypow + xpow * xpow);
        double theta = Math.atan2(ypow, xpow);
        double aPair = mag * Math.cos(theta - Math.PI/4);
        double bPair = mag * Math.sin(theta - Math.PI/4);



        bot.motorLF.setPower(0.7*(bPair-zpow));
        bot.motorRF.setPower(0.7*(-aPair-zpow));
        bot.motorRB.setPower(0.7*(-bPair-zpow));
        bot.motorLB.setPower(0.7*(aPair-zpow));

        double slidePower = -gamepad2.left_stick_y;
        if(slidePower>0)
        {
            slidePower /= 4;
        }
        bot.slideMotor.setPower(slidePower);



        double relicPower = gamepad2.right_stick_y;
        bot.relicMotor.setPower(relicPower);

        if(gamepad2.a)  // gripGlyphs
        {
            gripGlyph();
        }
        if(gamepad2.x)  // openLeft
        {
            openRight();
        }
        if(gamepad2.b)  // openRight
        {
            openLeft();
        }
        if(gamepad2.y) // releaseGlyphs
        {
            realeaseGlyph();
        }
        if(gamepad2.dpad_left){
            fingersClose();  // fingers closed for relic
        }
        if(gamepad2.dpad_right){
            fingersOpen(); // opens finger servo for relic
        }
        if(gamepad2.dpad_up){
            wristUp();   // brings wrist up for relic
        }
        if(gamepad2.dpad_down){
            wristDown(); // bring wrist down for relic
        }


    }

    public void fingersOpen(){
        bot.relicFingers.setPosition(.6);
    }

    public void fingersClose(){
        bot.relicFingers.setPosition(.95);
    }

    public void wristUp() {
        bot.relicWrist.setPosition(.7);
    }

    public void wristDown() {
        bot.relicWrist.setPosition(0);
    }
    public void gripGlyph() {
        bot.glyphServo1.setPosition(0.69);
        bot.glyphServo2.setPosition(0.35);
    }

    public void openRight() {
        bot.glyphServo1.setPosition(0.53);
    }

    public void openLeft() {
        bot.glyphServo2.setPosition(0.5);
    }

    public void realeaseGlyph() {
        openLeft();
        openRight();
    }


}