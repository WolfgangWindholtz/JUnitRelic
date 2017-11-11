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
    Map bot = new Map();
    double xpow;
    double ypow;
    double zpow;
    double rightx;

    @Override
    public void init() {
        bot.init(hardwareMap);
        bot.glyphServo1.setPosition(0.75);
        bot.glyphServo2.setPosition(0.15);
    }

    public void readGamePad() {
        zpow = gamepad1.right_stick_x;//direction not actually
        ypow = gamepad1.left_stick_y;// variable names are incoorect
        xpow = gamepad1.left_stick_x;
    }

    @Override
    public void loop() {

        readGamePad();
        double mag = Math.sqrt(ypow * ypow + xpow * xpow);
        double theta = Math.atan2(ypow, xpow);
        double aPair = mag * Math.cos(theta - Math.PI/4);
        double bPair = mag * Math.sin(theta - Math.PI/4);

        bot.motorLF.setPower(0.6*(bPair-zpow));
        bot.motorRF.setPower(0.6*(-aPair-zpow));
        bot.motorRB.setPower(0.6*(-bPair-zpow));
        bot.motorLB.setPower(0.6*(aPair-zpow));

        double slidePower = gamepad2.left_stick_y;
        if(slidePower>0)
        {
            slidePower /= 4;
        }

        bot.slideMotor.setPower(slidePower);

        if(gamepad2.a)  // gripGlyphs
        {
            gripGlyph();
        }
        if(gamepad2.b)  // openLeft
        {
            openLeft();
        }
        if(gamepad2.x)  // openRight
        {
            openRight();
        }
        if(gamepad2.y) // releaseGlyphs
        {
            realeaseGlyph();
        }
    }

    public void gripGlyph() {
        bot.glyphServo1.setPosition(0.47);
        bot.glyphServo2.setPosition(0.429);
    }

    public void openLeft() {
        bot.glyphServo1.setPosition(0.55);
    }

    public void openRight() {
        bot.glyphServo2.setPosition(0.32);
    }

    public void realeaseGlyph() {
        openLeft();
        openRight();
    }


}