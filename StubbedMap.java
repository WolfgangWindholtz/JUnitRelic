package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


public class StubbedMap {
    public BNO055IMU imu;
    HardwareMap hwMap = null;
    DcMotor motorLF;
    DcMotor motorLB;
    DcMotor motorRF;
    DcMotor motorRB;



    DistanceSensor sensorDistance;

    int cameraMonitorViewId;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    RelicRecoveryVuMark columnToScore;



    double tX;
    double tY;
    double tZ;

    double rX;
    double rY;
    double rZ;
    RelicRecoveryVuMark vuMark;

    VuforiaLocalizer vuforia;

    public void init () {

        motorLB = new DcMotorStub();
        motorLF = new DcMotorStub();
        motorRF = new DcMotorStub();
        motorRB = new DcMotorStub();

        sensorDistance = null;


    }
}
