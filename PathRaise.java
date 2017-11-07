package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by wolfie on 11/7/17.
 */
@Autonomous(name = "Auto raise" , group ="X")

public class PathRaise extends Processor{

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        waitForStart();
        runtime.reset();
        while(runtime.milliseconds() < 1.5){
            bot.slideMotor.setPower(.3);

            telemetry.addData("port 1 ", bot.slideMotor.getPortNumber());
            telemetry.addData("Controller: ",bot.slideMotor.getController());
            telemetry.addData("CurrentPosition: ",bot.slideMotor.getCurrentPosition());
            telemetry.addData("Mode: ",bot.slideMotor.getMode());
            telemetry.addData("Mode Type: ",bot.slideMotor.getMotorType());
            telemetry.addData("Power Float:",bot.slideMotor.getPowerFloat());
            telemetry.addData("Target Position: ",bot.slideMotor.getTargetPosition());
            telemetry.addData("ZeroPowerBehavior: ",bot.slideMotor.getZeroPowerBehavior());
            telemetry.addData("Power: ",bot.slideMotor.getPower());
            telemetry.addData("ConnectionInfo: ",bot.slideMotor.getConnectionInfo());
            telemetry.addData("DeviceName: ",bot.slideMotor.getDeviceName());
            telemetry.addData("Direction: ",bot.slideMotor.getDirection());
            telemetry.addData("Manufacturer: ",bot.slideMotor.getManufacturer());
            telemetry.addData("Version: ",bot.slideMotor.getVersion());
            telemetry.addData("Class: ",bot.slideMotor.getClass());



        telemetry.update();
    }
        runtime.reset();

        while(runtime.milliseconds() < 1.3) {

            bot.slideMotor.setPower(-.3);

            telemetry.addData("port 1 ", bot.slideMotor.getPortNumber());
            telemetry.addData("Controller: ", bot.slideMotor.getController());
            telemetry.addData("CurrentPosition: ", bot.slideMotor.getCurrentPosition());
            telemetry.addData("Mode: ", bot.slideMotor.getMode());
            telemetry.addData("Mode Type: ", bot.slideMotor.getMotorType());
            telemetry.addData("Power Float:", bot.slideMotor.getPowerFloat());
            telemetry.addData("Target Position: ", bot.slideMotor.getTargetPosition());
            telemetry.addData("ZeroPowerBehavior: ", bot.slideMotor.getZeroPowerBehavior());
            telemetry.addData("Power: ", bot.slideMotor.getPower());
            telemetry.addData("ConnectionInfo: ", bot.slideMotor.getConnectionInfo());
            telemetry.addData("DeviceName: ", bot.slideMotor.getDeviceName());
            telemetry.addData("Direction: ", bot.slideMotor.getDirection());
            telemetry.addData("Manufacturer: ", bot.slideMotor.getManufacturer());
            telemetry.addData("Version: ", bot.slideMotor.getVersion());
            telemetry.addData("Class: ", bot.slideMotor.getClass());



            telemetry.update();
        }

        bot.slideMotor.setPower(0);
    }
}
