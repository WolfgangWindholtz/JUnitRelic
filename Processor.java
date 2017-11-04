package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
/**
 * Created by wolfie on 10/20/17.
 */

public abstract class Processor extends LinearOpMode {
    Map bot = new Map();
    ElapsedTime runtime = new ElapsedTime();
    public final static double DEFAULT_POWER = .7;
    public final static int TICKSPERROTATION = 1120;
    static final double P_TURN_COEFF = .2;
    public final static int DIAMETEROFWHEEL = 4;
    static final double TURN_SPEED = 0.4;
    static final double DRIVE_SPEED = 0.6;
    static final double HEADING_THRESHOLD = 2;
    static final double OMNI_WHEEL_CIRCUMFERENCE = 4 * Math.PI;

    static final double COUNTS_PER_MOTOR_REV = 1120;
    static final double DRIVE_GEAR_REDUCTION = 1.286;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = ((COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415));

    public void encoderDrive(double speed,
                             double rightFrontInches, double leftFrontInches,double leftBackInches, double rightBackInches,
                             double timeoutS) {
        int newLeftFrontTarget;
        int newRightBackTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFrontTarget = bot.motorLF.getCurrentPosition() + (int)(leftFrontInches * COUNTS_PER_INCH);
            newRightFrontTarget = bot.motorRF.getCurrentPosition() + (int)(rightFrontInches * COUNTS_PER_INCH);
            newRightBackTarget = bot.motorRB.getCurrentPosition() + (int)(rightBackInches * COUNTS_PER_INCH);
            newLeftBackTarget = bot.motorLB.getCurrentPosition() + (int)(leftBackInches * COUNTS_PER_INCH);
            bot.motorLF.setTargetPosition(newLeftFrontTarget);
            bot.motorRF.setTargetPosition(newRightFrontTarget);
            bot.motorRB.setTargetPosition(newRightBackTarget);
            bot.motorLB.setTargetPosition(newLeftBackTarget);

            // Turn On RUN_TO_POSITION
            bot.motorLF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bot.motorRF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bot.motorLB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bot.motorRB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            bot.motorLF.setPower(Math.abs(speed));
            bot.motorRF.setPower(Math.abs(speed));
            bot.motorLB.setPower(Math.abs(speed));
            bot.motorRB.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (bot.motorLB.isBusy() && bot.motorRB.isBusy()&&bot.motorRF.isBusy()&&bot.motorLF.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftBackTarget,  newLeftFrontTarget,newRightBackTarget,newRightFrontTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        bot.motorLB.getCurrentPosition(),
                        bot.motorLF.getCurrentPosition(),
                        bot.motorRB.getCurrentPosition(),
                        bot.motorRF.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            bot.motorLB.setPower(0);
            bot.motorLF.setPower(0);
            bot.motorRB.setPower(0);
            bot.motorRF.setPower(0);


            // Turn off RUN_TO_POSITION
            bot.motorLF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bot.motorRF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bot.motorRB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bot.motorLB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move
        }
    }

    public void checkVu() {

        /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
        * it is perhaps unlikely that you will actually need to act on this pose information, but
        * we illustrate it nevertheless, for completeness. */
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) bot.relicTemplate.getListener()).getPose();
        telemetry.addData("Pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
        if (pose != null) {
            VectorF trans = pose.getTranslation();
            Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

            // Extract the X, Y, and Z components of the offset of the target relative to the robot
            bot.tX = trans.get(0);
            bot.tY = trans.get(1);
            bot.tZ = trans.get(2);

            // X = vertical axis
            // Y = horizonatal Axis
            // Z = Depth Axis
            // Extract the rotational components of the target relative to the robot
            bot.rX = rot.firstAngle;
            bot.rY = rot.secondAngle;
            bot.rZ = rot.thirdAngle;
        } else {
            telemetry.addData("VuMark", "not visible");
        }
        bot.vuMark = RelicRecoveryVuMark.from(bot.relicTemplate);
        telemetry.update();
    }

    public void checkCol() {
        checkVu();
        while(bot.columnToScore == null) {
            if (bot.vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", bot.vuMark);

                bot.columnToScore = bot.vuMark;
            } else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
        }
    }


    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }


    // SEPERATE STATE LATER IS SCORE STATE
    // PASS IN PARAMETERS THAT WILL TELL HOW TO SCORE

    

    public void getOffStone() {
        //accesses the gyro values
        //drive based on Vuforia in
    }

    public void turn(double target) {
        Orientation ref = bot.imu.getAngularOrientation();

        double heading = ref.firstAngle;
        double correction;
        double error;

        double angleWanted = target + heading;

        ref = bot.imu.getAngularOrientation();
        double speed = turning(ref.firstAngle, angleWanted);
        while(speed != 0 ){
            ref = bot.imu.getAngularOrientation();
            speed = turning(ref.firstAngle, angleWanted);
            accelerate(speed);
            recordTelemetry(target, angleWanted, ref, speed);
        }
        accelerate(0);
    }

    double turning(double firstAngle, double angleWanted) {
        double error;
        double correction;
        double speed;
        error = angleWanted - firstAngle;

        correction = Range.clip( error * P_TURN_COEFF,-1,1);

        if(Math.abs(error) <= HEADING_THRESHOLD){
            return 0;
        }
        else{
            speed = TURN_SPEED * correction;
        }
        return speed;
    }

    public void recordTelemetry(double target, double angleWanted, Orientation ref, double speed) {
        telemetry.addData("first angle",ref.firstAngle);
        telemetry.addData("second angle",ref.secondAngle);
        telemetry.addData("third angle",ref.thirdAngle);
        telemetry.addData("target",target);
        telemetry.addData("speed ",speed);
        telemetry.addData("error", angleWanted - ref.firstAngle);
        telemetry.addData("angleWanted", angleWanted);

        telemetry.update();
    }

    private void accelerate(double speed) {
        double clip_speed = Range.clip(speed, -1, 1);
        bot.motorLF.setPower(clip_speed);
        bot.motorRF.setPower(clip_speed);
        bot.motorRB.setPower(clip_speed);
        bot.motorLB.setPower(clip_speed);
    }

    public void go(double angle, double time){

        bot.imu.getPosition();
        while(runtime.milliseconds() < time){

            bot.motorLF.setPower(-DRIVE_SPEED);
            bot.motorRF.setPower(DRIVE_SPEED);
            bot.motorRB.setPower(DRIVE_SPEED);
            bot.motorLB.setPower(-DRIVE_SPEED);

        }
    }

    public void forward(double millisec){

        runtime.reset();
        while(runtime.milliseconds() < millisec){

            bot.motorLF.setPower(-DRIVE_SPEED);
            bot.motorRF.setPower(DRIVE_SPEED);
            bot.motorRB.setPower(DRIVE_SPEED);
            bot.motorLB.setPower(-DRIVE_SPEED);

        }
    }

    public void strafeForward(int distance) {
        double temp = COUNTS_PER_MOTOR_REV * (distance / OMNI_WHEEL_CIRCUMFERENCE);
        bot.motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while ((bot.motorLB.getCurrentPosition() < temp + 100) && (bot.motorRF.getCurrentPosition() < temp + 100) && (bot.motorLF.getCurrentPosition() < temp + 100) && (bot.motorRB.getCurrentPosition() < temp + 100)) {
            bot.motorLB.setPower(-0.5);
            bot.motorRF.setPower(0.5);
            bot.motorLF.setPower(-0.5);
            bot.motorRB.setPower(0.5);
        }

    }

    public void strafeBack(int distance) {
        double temp = COUNTS_PER_MOTOR_REV * (distance / OMNI_WHEEL_CIRCUMFERENCE);
        bot.motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while ((bot.motorLB.getCurrentPosition() < temp + 100) && (bot.motorRF.getCurrentPosition() < temp + 100) && (bot.motorLF.getCurrentPosition() < temp + 100) && (bot.motorRB.getCurrentPosition() < temp + 100)) {
            bot.motorLB.setPower(0.5);
            bot.motorRF.setPower(-0.5);
            bot.motorLF.setPower(0.5);
            bot.motorRB.setPower(-0.5);
        }

    }

    public void strafeLeft(int distance) {
        double temp = COUNTS_PER_MOTOR_REV * (distance / OMNI_WHEEL_CIRCUMFERENCE);
        bot.motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while ((bot.motorLB.getCurrentPosition() < temp + 100) && (bot.motorRF.getCurrentPosition() < temp + 100) && (bot.motorLF.getCurrentPosition() < temp + 100) && (bot.motorRB.getCurrentPosition() < temp + 100)) {
            bot.motorLB.setPower(-0.5);
            bot.motorRF.setPower(0.5);
            bot.motorLF.setPower(0.5);
            bot.motorRB.setPower(-0.5);
        }

    }

    public void strafeRight(int distance) {
        double temp = COUNTS_PER_MOTOR_REV * (distance / OMNI_WHEEL_CIRCUMFERENCE);
        bot.motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while ((bot.motorLB.getCurrentPosition() < temp + 100) && (bot.motorRF.getCurrentPosition() < temp + 100) && (bot.motorLF.getCurrentPosition() < temp + 100) && (bot.motorRB.getCurrentPosition() < temp + 100)) {
            bot.motorLB.setPower(0.5);
            bot.motorRF.setPower(-0.5);
            bot.motorLF.setPower(-0.5);
            bot.motorRB.setPower(0.5);
        }

    }

    public void knockJewel(boolean isTeamRed){
        bot.jewelServo.setPosition(90);
        int toTurn = checkJewel(isTeamRed,isSensorRed());
        turn(toTurn);
        turn(-toTurn);
    }

    public  int checkJewel(boolean isTeamRed, boolean isSensorRed){
        if(isTeamRed){
            if( isTeamRed != isSensorRed){
                return 15;
            }
            else/*isTeamRed != isSensorRed*/{
                return -15;
            }
        }
        else{
            if(isTeamRed == isSensorRed){
                return 15;
            }
            else/*isTeamRed != isSensorRed*/{
                return -15;
            }
        }
    }

    public boolean isSensorRed(){
        return  bot.jewelSensor.red() > bot.jewelSensor.blue();
    }

    public void gotoColumnRight() {// the direction approating the cyrpoto box changes depending on the side


        if (bot.columnToScore == RelicRecoveryVuMark.RIGHT) {
            goPulses(1);
        }
        if (bot.columnToScore == RelicRecoveryVuMark.CENTER) {
            goPulses(2);
        }
        if (bot.columnToScore == RelicRecoveryVuMark.LEFT) {
            goPulses(3);
        }
        runtime.reset();
        while(runtime.milliseconds() < 1000){
            bot.motorLF.setPower(DRIVE_SPEED);
            bot.motorRF.setPower(DRIVE_SPEED);
            bot.motorRB.setPower(-DRIVE_SPEED);
            bot.motorLB.setPower(-DRIVE_SPEED);
        }
        runtime.reset();


        while(bot.distanceSensor.getDistance(DistanceUnit.CM)>25){
            bot.motorLF.setPower(-DRIVE_SPEED);
            bot.motorRF.setPower(-DRIVE_SPEED);
            bot.motorRB.setPower(DRIVE_SPEED);
            bot.motorLB.setPower(DRIVE_SPEED);
        }
    }

    public void gotoColumnBluePerp() {// the direction approating the cyrpoto box changes depending on the side


        if (bot.columnToScore == RelicRecoveryVuMark.LEFT) {
            goPulsesPrep(1);
        }
        if (bot.columnToScore == RelicRecoveryVuMark.CENTER) {
            goPulsesPrep(2);
        }
        if (bot.columnToScore == RelicRecoveryVuMark.RIGHT) {
            goPulsesPrep(3);
        }
        while(runtime.milliseconds() < 1000){
            bot.motorLF.setPower(-DRIVE_SPEED);
            bot.motorRF.setPower(-DRIVE_SPEED);
            bot.motorRB.setPower(DRIVE_SPEED);
            bot.motorLB.setPower(DRIVE_SPEED);
        }
        runtime.reset();

        while(bot.distanceSensor.getDistance(DistanceUnit.CM)>25){
            bot.motorLF.setPower(DRIVE_SPEED);
            bot.motorRF.setPower(DRIVE_SPEED);
            bot.motorRB.setPower(-DRIVE_SPEED);
            bot.motorLB.setPower(-DRIVE_SPEED);
        }
    }
    public void gotoColumnLeft() {// the direction approating the cyrpoto box changes depending on the side


        if (bot.columnToScore == RelicRecoveryVuMark.LEFT) {
            goPulses(1);
        }
        if (bot.columnToScore == RelicRecoveryVuMark.CENTER) {
            goPulses(2);
        }
        if (bot.columnToScore == RelicRecoveryVuMark.RIGHT) {
            goPulses(3);
        }
        while(runtime.milliseconds() < 1000){
            bot.motorLF.setPower(DRIVE_SPEED);
            bot.motorRF.setPower(DRIVE_SPEED);
            bot.motorRB.setPower(-DRIVE_SPEED);
            bot.motorLB.setPower(-DRIVE_SPEED);
        }
        runtime.reset();

        while(bot.distanceSensor.getDistance(DistanceUnit.CM)>25){
            bot.motorLF.setPower(-DRIVE_SPEED);
            bot.motorRF.setPower(-DRIVE_SPEED);
            bot.motorRB.setPower(DRIVE_SPEED);
            bot.motorLB.setPower(DRIVE_SPEED);
        }
    }
    public void goPulses(int numOfCol) {
        int count = 0;
        while(count < numOfCol){

            bot.motorLF.setPower(DRIVE_SPEED);
            bot.motorRF.setPower(DRIVE_SPEED);
            bot.motorRB.setPower(-DRIVE_SPEED);
            bot.motorLB.setPower(-DRIVE_SPEED);

            if (bot.distanceSensor.getDistance(DistanceUnit.CM)<25) {
                count++;
                while(runtime.milliseconds() < 1000){
                    bot.motorLF.setPower(DRIVE_SPEED);
                    bot.motorRF.setPower(DRIVE_SPEED);
                    bot.motorRB.setPower(-DRIVE_SPEED);
                    bot.motorLB.setPower(-DRIVE_SPEED);
                }
                runtime.reset();
                // clear the column so the same colmn is not counted three time
            }
            telemetry.addData("count",count );
            telemetry.update();
        }
    }
    public void goPulsesPrep(int numOfCol) {
        int count = 0;
        while(count < numOfCol){

            bot.motorLF.setPower(-DRIVE_SPEED);
            bot.motorRF.setPower(-DRIVE_SPEED);
            bot.motorRB.setPower(DRIVE_SPEED);
            bot.motorLB.setPower(DRIVE_SPEED);

            if (bot.distanceSensor.getDistance(DistanceUnit.CM)<25) {
                count++;
                while(runtime.milliseconds() < 1000){
                    bot.motorLF.setPower(-DRIVE_SPEED);
                    bot.motorRF.setPower(-DRIVE_SPEED);
                    bot.motorRB.setPower(DRIVE_SPEED);
                    bot.motorLB.setPower(DRIVE_SPEED);
                }
                runtime.reset();
                 // clear the column so the same colmn is not counted three time
            }
            telemetry.addData("count",count );
            telemetry.update();
        }
    }

    public void score(){

    }




}