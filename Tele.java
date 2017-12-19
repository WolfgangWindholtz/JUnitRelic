package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "XTele", group = "X")
public class Tele extends OpMode{
    TeleMap bot = new TeleMap();
    double xpow;
    double ypow;
    double zpow;
    double rightx;
    boolean toggle = false;
    double v;
    double v1;
    double v2;
    double v3;

    diverModes mode = diverModes.OMNI;



    @Override
    public void init() {
        //initalizes hardware map
        bot.init(hardwareMap);
    }

    public void readGamePad() {
        //assigns joystick values to variables
        zpow = gamepad1.right_stick_x;
        ypow = gamepad1.left_stick_y;
        xpow = gamepad1.left_stick_x;

        //creates a deadzone for left stick y
        if(Math.abs(ypow)<.05){
            ypow = 0;

        }
        //creates a deadzone for left stick x
        if(Math.abs(xpow)<.05){
            xpow = 0;

        }
    }

    @Override
    public void loop() {

        //takes the joystick values and converts to motor speeds through holonomic calculations
        readGamePad();
        double mag = Math.sqrt(ypow * ypow + xpow * xpow);
        double theta = Math.atan2(ypow, xpow);
        double aPair = mag * Math.cos(theta - Math.PI/4);
        double bPair = mag * Math.sin(theta - Math.PI/4);

        switch (mode){
            case OMNI:
                v = .8 * (bPair - toggle(toggle, zpow));
                v1 = .8 * (-aPair - toggle(toggle, zpow));
                v2 = .8 * (-bPair - toggle(toggle, zpow));
                v3 = .8 * (aPair - toggle(toggle, zpow));
                break;
            case TANK1:
                v = gamepad1.left_stick_y;
                v1 = gamepad1.right_stick_y;
                v2 = gamepad1.right_stick_y;
                v3 = gamepad1.left_stick_y;
                break;
            case TANK2:
                v = gamepad1.right_stick_y;
                v1 = gamepad1.right_stick_y;
                v2 = gamepad1.left_stick_y;
                v3 = gamepad1.left_stick_y;
                break;
            case REARTURN:
                v2 = gamepad1.right_stick_y;
                v3 = gamepad1.left_stick_y;
                break;
            default:
                v = .8 * (bPair - toggle(toggle, zpow));
                v1 = .8 * (-aPair - toggle(toggle, zpow));
                v2 = .8 * (-bPair - toggle(toggle, zpow));
                v3 = .8 * (aPair - toggle(toggle, zpow));
                break;
        }
        //sets movement speeds for motors to move correctly based on joystick input
        //runs at .8 speed to provide driver assisting controls
        bot.motorLF.setPower(v);
        bot.motorRF.setPower(v1);
        bot.motorRB.setPower(v2);
        bot.motorLB.setPower(v3);

        //assings the joystick value to another variable
        double slidePower = -gamepad2.left_stick_y;

        if(slidePower>0)
        {
            //scales the slidepower to move at a quarter speed
            slidePower /= 4;
        }
        bot.slideMotor.setPower(slidePower);

        if(gamepad1.a){
            if(!toggle){
                toggle = true;
            }
            else {
                toggle = false;
            }

        }


        //assigns the value of the joystick to a variable
        double relicPower = gamepad2.right_stick_y;

        //sets the variable value to move the motor at the specified speed
        bot.relicMotor.setPower(relicPower);

        if(gamepad2.a)  //closes the servos to hold the glyph
        {
            gripGlyph();
        }
        if(gamepad2.x)  //opens the right servo
        {
            openRight();
            bot.glyphServo2.setPosition(.42);
        }
        if(gamepad2.b)  // openRight
        {
            bot.glyphServo2.setPosition(.8);

            bot.glyphServo1.setPosition(.2);
        }
        if(gamepad2.y) //releases the glyph from the servos
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
        //DRIVECHANGE
        if(gamepad1.dpad_left){
            mode = diverModes.OMNI;  // fingers closed for relic
        }
        if(gamepad1.dpad_right){
            mode = diverModes.TANK1; // opens finger servo for relic
        }
        if(gamepad1.dpad_up){
            mode = diverModes.REARTURN;   // brings wrist up for relic
        }
        if(gamepad1.dpad_down){
            mode = diverModes.TANK2; // bring wrist down for relic
        }


    }

    public double toggle(boolean toggle, double power){
        if(toggle){
            return power * .4;
        }
        else{
            return power;
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

    enum diverModes{
        OMNI,TANK2, TANK1,REARTURN
    }

}