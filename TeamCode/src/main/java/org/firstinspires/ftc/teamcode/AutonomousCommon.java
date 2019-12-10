package org.firstinspires.ftc.teamcode;
//test


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public class AutonomousCommon {


    public static void macanumBox(DcMotor frontLeft, DcMotor rearLeft,
                                  DcMotor frontRight, DcMotor rearRight,
                                  int targetPosition, double power, boolean opModeIsActive,
                                  Telemetry telemetry) {
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, AutonomousCommon.StrafeDirection.Forward, targetPosition, power, opModeIsActive, telemetry);
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, AutonomousCommon.StrafeDirection.Right, targetPosition, power, opModeIsActive, telemetry);
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, AutonomousCommon.StrafeDirection.Backward, targetPosition, power, opModeIsActive, telemetry);
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, AutonomousCommon.StrafeDirection.Left, targetPosition, power, opModeIsActive, telemetry);

    }

    public static void macanumMovement(DcMotor frontLeft, DcMotor rearLeft,
                                       DcMotor frontRight, DcMotor rearRight,
                                       StrafeDirection strafeDirection,
                                       int targetPosition, double power, boolean opModeIsActive,
                                       Telemetry telemetry) {

        telemetry.addLine("Begin macanumMovement");
        telemetry.update();
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        switch (strafeDirection) {
            case Left:
                telemetry.addLine("strafing left");
                telemetry.update();
                rearLeft.setTargetPosition(targetPosition);
                rearRight.setTargetPosition(-targetPosition);
                frontLeft.setTargetPosition(-targetPosition);
                frontRight.setTargetPosition(targetPosition);
                break;
            case Right:
                telemetry.addLine("strafing right");
                telemetry.update();
                rearLeft.setTargetPosition(-targetPosition);
                rearRight.setTargetPosition(targetPosition);
                frontLeft.setTargetPosition(targetPosition);
                frontRight.setTargetPosition(-targetPosition);
                break;
            case Forward:
                telemetry.addLine("moving forward");
                telemetry.update();
                rearLeft.setTargetPosition(targetPosition);
                rearRight.setTargetPosition(targetPosition);
                frontLeft.setTargetPosition(targetPosition);
                frontRight.setTargetPosition(targetPosition);
                break;
            case Backward:
                telemetry.addLine("moving backward");
                telemetry.update();
                rearLeft.setTargetPosition(-targetPosition);
                rearRight.setTargetPosition(-targetPosition);
                frontLeft.setTargetPosition(-targetPosition);
                frontRight.setTargetPosition(-targetPosition);
                break;
        }


        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rearLeft.setPower(power);
        rearRight.setPower(power);
        frontLeft.setPower(power);
        frontRight.setPower(power);

        while (rearLeft.isBusy() && opModeIsActive) {
        }
        while (rearRight.isBusy() && opModeIsActive) {
        }
        while (frontLeft.isBusy() && opModeIsActive) {
        }
        while (frontRight.isBusy() && opModeIsActive) {
        }
        rearLeft.setPower(0);
        rearRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);

        telemetry.addLine("End macanumMovement");
        telemetry.update();
    }

    public static void servoMovement(Servo servo, double position) {
        servo.setPosition(position);
    }

    //sleep from LinearOpMode class
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static int convertInchesToPosition(double inches, boolean isStrafe) {
        int returnValue = 0;

        if(isStrafe){
            //strafe factor
            returnValue = (int) Math.round(inches * (1000/9));
        }
        else
        {
            //non strafe factor
            returnValue = (int) Math.round(inches * (1000/11));
        }

        return returnValue;
    }


    public enum StrafeDirection{
        Left,
        Right,
        Forward,
        Backward
    }
    public enum PlayfieldSide{
        Blue,
        Red
    }
    public enum FlapperServos{
        Left(0),
        Right(1);
        private final int value;

        private FlapperServos(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public enum MovementMotors {
        FrontLeft(0),
        FrontRight(1),
        RearRight(2),
        RearLeft(3);
        private final int value;

        private MovementMotors(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
