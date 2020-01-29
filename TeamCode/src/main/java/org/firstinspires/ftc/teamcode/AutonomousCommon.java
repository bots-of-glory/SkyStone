package org.firstinspires.ftc.teamcode;
//test


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutonomousCommon {


    public static void macanumBox(DcMotor frontLeft, DcMotor rearLeft,
                                  DcMotor frontRight, DcMotor rearRight,
                                  int targetPosition, double power, boolean opModeIsActive,
                                  Telemetry telemetry) throws InterruptedException{
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, targetPosition, power, opModeIsActive, telemetry);
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Right, targetPosition, power, opModeIsActive, telemetry);
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, targetPosition, power, opModeIsActive, telemetry);
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Left, targetPosition, power, opModeIsActive, telemetry);

    }
    public static void motorLift(DcMotor lift1,DcMotor lift2,boolean opModeIsActive, Telemetry telemetry) throws InterruptedException {
        telemetry.addLine("Begin motorLift");
        telemetry.update();

        lift1.setPower(1);
        lift2.setPower(1);
        sleep( 500);
        lift1.setPower(-1);
        lift2.setPower(-1);
        sleep(500);

    }
    public static void macanumRotate(DcMotor frontLeft, DcMotor rearLeft,
                                     DcMotor frontRight, DcMotor rearRight, int degrees, boolean opModeIsActive,
                                     Telemetry telemetry, RotateDegree rotateDegree) throws InterruptedException {
        telemetry.addLine("Begin macanumRotate");
        telemetry.update();
        degrees = (int) Range.clip(degrees,0,360);


        switch (rotateDegree) {
            case Positive:
                frontLeft.setPower(-1);
                rearLeft.setPower(-1);
                frontRight.setPower(1);
                rearRight.setPower(1);
                break;
            case Negative:
                frontLeft.setPower(1);
                rearLeft.setPower(1);
                frontRight.setPower(-1);
                rearRight.setPower(-1);
                break;
        }
        sleep( 13*degrees);

        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);
        Thread.sleep(500);
        telemetry.addLine("End macanumRotate");
        telemetry.update();
    }
    public static void macanumMovementTimeBased(DcMotor frontLeft, DcMotor rearLeft,
                                       DcMotor frontRight, DcMotor rearRight,
                                       StrafeDirection strafeDirection,
                                       int inches, double power, boolean opModeIsActive,
                                       Telemetry telemetry) throws InterruptedException{

        telemetry.addLine("Begin macanumMovement");
        telemetry.update();
        //rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double rearPower = power + 0.0;
        double frontPower =  power + 0.045;
        switch (strafeDirection) {
            case Left:
                telemetry.addLine("strafing left");
                telemetry.update();
                rearLeft.setPower(rearPower);
                rearRight.setPower(-rearPower);
                frontLeft.setPower(-frontPower);
                frontRight.setPower(frontPower);
                break;
            case Right:
                telemetry.addLine("strafing right");
                telemetry.update();
                rearLeft.setPower(-rearPower);
                rearRight.setPower(rearPower);
                frontLeft.setPower(frontPower);
                frontRight.setPower(-frontPower);
                break;
            case Forward:
                telemetry.addLine("moving forward");
                telemetry.update();
                rearLeft.setPower(rearPower);
                rearRight.setPower(rearPower);
                frontLeft.setPower(frontPower);
                frontRight.setPower(frontPower);
                break;
            case Backward:
                telemetry.addLine("moving backward");
                telemetry.update();
                rearLeft.setPower(-rearPower);
                rearRight.setPower(-rearPower);
                frontLeft.setPower(-frontPower);
                frontRight.setPower(-frontPower);
                break;
        }

        if(strafeDirection == StrafeDirection.Right || strafeDirection == StrafeDirection.Left)
        {
            //1 sec = 51 inches
            Thread.sleep((1000*inches)/12);
        }
        else{
            //1 sec = 51 inches
            Thread.sleep((1000*inches)/65);
        }


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
        Thread.sleep(500);
        telemetry.addLine("End macanumMovement");
        telemetry.update();
    }
    public static void macanumMovement(DcMotor frontLeft, DcMotor rearLeft,
                                       DcMotor frontRight, DcMotor rearRight,
                                       StrafeDirection strafeDirection,
                                       int inches, double power, boolean opModeIsActive,
                                       Telemetry telemetry) {

        telemetry.addLine("Begin macanumMovement");
        telemetry.update();
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int targetPosition = AutonomousCommon.convertInchesToPosition(inches,strafeDirection==StrafeDirection.Left||strafeDirection==StrafeDirection.Right);

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
        while (rearLeft.isBusy() && rearLeft.isBusy() && frontLeft.isBusy() && frontRight.isBusy() && opModeIsActive) {
        }
//        while (rearLeft.isBusy() && opModeIsActive) {
//        }
//        while (rearRight.isBusy() && opModeIsActive) {
//        }
//        while (frontLeft.isBusy() && opModeIsActive) {
//        }
//        while (frontRight.isBusy() && opModeIsActive) {
//        }
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
            returnValue = (int) Math.round(inches * (1000/17));
        }
        else
        {
            //non strafe factor
            returnValue = (int) Math.round(inches * (1000/28));
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
    public static class VUPosition {
        public double x;
        public double z;
        public VUPoistionDirection direction;

    }
    public enum VUPoistionDirection{
        Left, Center, Right
    }
    public enum RotateDegree {
        Positive,
        Negative
    }
}
