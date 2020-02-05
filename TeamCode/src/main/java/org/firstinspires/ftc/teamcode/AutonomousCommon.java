package org.firstinspires.ftc.teamcode;
//test


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
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
    public static void macanumRotate(DcMotor frontLeft, DcMotor rearLeft,
                                     DcMotor frontRight, DcMotor rearRight, int degrees, boolean opModeIsActive,
                                     Telemetry telemetry, RotateDegree rotateDegree) throws InterruptedException {
        telemetry.addLine("Begin macanumRotate");
        telemetry.update();
        ElapsedTime runtime = new ElapsedTime();
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        degrees = (int) Range.clip(degrees,0,360);
        //change targetPosition
        int targetPosition = degrees * 710/90;
        double power = 1.0;
        switch (rotateDegree) {
            case Negative:
            frontLeft.setTargetPosition(targetPosition);
            rearLeft.setTargetPosition(targetPosition);
            frontRight.setTargetPosition(-targetPosition);
            rearRight.setTargetPosition(-targetPosition);
                frontLeft.setPower(power);
                rearLeft.setPower(power);
                frontRight.setPower(power);
                rearRight.setPower(power);
                break;
            case Positive:
                frontLeft.setTargetPosition(-targetPosition);
                rearLeft.setTargetPosition(-targetPosition);
                frontRight.setTargetPosition(targetPosition);
                rearRight.setTargetPosition(targetPosition);
                frontLeft.setPower(power);
                rearLeft.setPower(power);
                frontRight.setPower(power);
                rearRight.setPower(power);
                break;
        }
        int timeout;
        timeout = targetPosition * (2/1000) + 1;
        runtime.reset();
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while ((runtime.seconds() < timeout) && rearRight.isBusy() && rearLeft.isBusy() && frontLeft.isBusy() && frontRight.isBusy() && opModeIsActive) {
        }
        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);
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
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double rearPower = power;
        //double frontPower =  power + 0.045;
        double frontPower =  power;
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

        ElapsedTime runtime = new ElapsedTime();
        telemetry.addLine("Begin macanumMovement");
        telemetry.update();
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int targetPosition = AutonomousCommon.convertInchesToPosition(inches,strafeDirection==StrafeDirection.Left||strafeDirection==StrafeDirection.Right);
        switch (strafeDirection) {
            case Left:
                telemetry.addLine("strafing left");
                telemetry.update();
                rearLeft.setTargetPosition(targetPosition);
                rearRight.setTargetPosition(-targetPosition);
                frontLeft.setTargetPosition(-targetPosition);
                frontRight.setTargetPosition(targetPosition);
                rearLeft.setPower(power);
                rearRight.setPower(power);
                frontLeft.setPower(power);
                frontRight.setPower(power);
                break;
            case Right:
                telemetry.addLine("strafing right");
                telemetry.update();
                rearLeft.setTargetPosition(-targetPosition);
                rearRight.setTargetPosition(targetPosition);
                frontLeft.setTargetPosition(targetPosition);
                frontRight.setTargetPosition(-targetPosition);
                rearLeft.setPower(power);
                rearRight.setPower(power);
                frontLeft.setPower(power);
                frontRight.setPower(power);

                break;
            case Forward:
                telemetry.addLine("moving forward");
                telemetry.update();
                rearLeft.setTargetPosition(targetPosition);
                rearRight.setTargetPosition(targetPosition);
                frontLeft.setTargetPosition(targetPosition);
                frontRight.setTargetPosition(targetPosition);
                rearLeft.setPower(power);
                rearRight.setPower(power);
                frontLeft.setPower(power);
                frontRight.setPower(power);

                break;
            case Backward:
                telemetry.addLine("moving backward");
                telemetry.update();
                rearLeft.setTargetPosition(-targetPosition);
                rearRight.setTargetPosition(-targetPosition);
                frontLeft.setTargetPosition(-targetPosition);
                frontRight.setTargetPosition(-targetPosition);
                rearLeft.setPower(power);
                rearRight.setPower(power);
                frontLeft.setPower(power);
                frontRight.setPower(power);
                break;
        }

        // correction and timeout
        int timeout;
        int correction = targetPosition / 8;
        timeout = targetPosition * (2/1000) + 1;
        if (targetPosition > 20000) {
            timeout = targetPosition * (2/1000) + 3;
        } else {
            timeout = targetPosition * (2/1000) + 1;
        }
        int correctionTimeout = correction * (2/1000) + 1;

        runtime.reset();
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while ((runtime.seconds() < timeout) && rearRight.isBusy() && rearLeft.isBusy() && frontLeft.isBusy() && frontRight.isBusy() && opModeIsActive) {
        }
//        while (rearLeft.isBusy() && opModeIsActive) {
//        }
//        while (rearRight.isBusy() && opModeIsActive) {
//        }
//        while (frontLeft.isBusy() && opModeIsActive) {
//        }
//        while (frontRight.isBusy() && opModeIsActive) {
//        }

       if (strafeDirection == StrafeDirection.Right) {
            if (targetPosition - rearLeft.getCurrentPosition() > correction) {
                rearLeft.setTargetPosition(correction);
                rearLeft.setPower(power);
            }
            if (targetPosition - rearRight.getCurrentPosition() > correction) {
                rearRight.setTargetPosition(correction);
                rearRight.setPower(power);
            }
            if (targetPosition - frontLeft.getCurrentPosition() > correction) {
                frontLeft.setTargetPosition(correction);
                frontLeft.setPower(power);
            }
            if (targetPosition - frontRight.getCurrentPosition() > correction) {
                frontRight.setTargetPosition(correction);
                frontRight.setPower(power);
            }
        }
        else if (strafeDirection == StrafeDirection.Left){
            if (targetPosition - rearLeft.getCurrentPosition() > correction) {
                rearLeft.setTargetPosition(correction);
                rearLeft.setPower(power);
            }
            if (targetPosition - rearRight.getCurrentPosition() > correction) {
                rearRight.setTargetPosition(-correction);
                rearRight.setPower(power);
            }
            if (targetPosition - frontLeft.getCurrentPosition() > correction) {
                frontLeft.setTargetPosition(-correction);
                frontLeft.setPower(power);
            }
            if (targetPosition - frontRight.getCurrentPosition() > correction) {
                frontRight.setTargetPosition(correction);
                frontRight.setPower(power);
            }
        }
        else {
            if (targetPosition - rearLeft.getCurrentPosition() > correction) {
                rearLeft.setTargetPosition(0);
                rearLeft.setPower(0);
            }
            if (targetPosition - rearRight.getCurrentPosition() > correction) {
                rearRight.setTargetPosition(0);
                rearRight.setPower(0);
            }
            if (targetPosition - frontLeft.getCurrentPosition() > correction) {
                frontLeft.setTargetPosition(0);
                frontLeft.setPower(0);
            }
            if (targetPosition - frontRight.getCurrentPosition() > correction) {
                frontRight.setTargetPosition(0);
                frontRight.setPower(0);
            }
        }
        runtime.reset();
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while ((runtime.seconds() < correctionTimeout) && rearRight.isBusy() && rearLeft.isBusy() && frontLeft.isBusy() && frontRight.isBusy() && opModeIsActive) {
        }
        rearLeft.setPower(0);
        rearRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        telemetry.addLine("End macanumMovement");
        telemetry.update();
    }
    public static void liftMovement(DcMotor lift1, DcMotor lift2,
                                       int encoderticks, double power, boolean opModeIsActive,
                                       Telemetry telemetry) {


        telemetry.addLine("Begin liftMovement");
        telemetry.update();
        lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //double speed = .045;

        int timeout;
        int targetPosition = encoderticks;
                telemetry.addLine("moving up");
                telemetry.update();
                lift1.setTargetPosition(targetPosition);
                lift2.setTargetPosition(targetPosition);
                lift1.setPower(power);
                lift2.setPower(power);


        lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (lift1.isBusy() && lift2.isBusy() && opModeIsActive) {

        }

        lift1.setPower(0);
        lift2.setPower(0);
        telemetry.addLine("End liftMovement");
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
            returnValue = (int) Math.round(inches * (1000/13));
        }
        else
        {
            //non strafe factor
            returnValue = (int) Math.round(inches * (1000/26));
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
        public double y;
        public VUPositionDirection direction;

    }
    public enum VUPositionDirection {
        Left, Center, Right
    }
    public enum RotateDegree {
        Positive,
        Negative
    }
    public enum CorrectionDirection {
        Forward,
        Backward,
        Left,
        Right
    }
}
