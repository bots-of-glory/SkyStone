package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Intake_Test", group="testOp")
public class Intake_Test extends LinearOpMode {
    DcMotor intake1;
    DcMotor intake2;
    @Override
    public void runOpMode() throws InterruptedException {

        //declare motors
        intake1 = hardwareMap.dcMotor.get("intake1");
        intake2 = hardwareMap.dcMotor.get("intake2");

        //declare motor directions
        intake1.setDirection(DcMotor.Direction.REVERSE);
        intake2.setDirection(DcMotor.Direction.FORWARD);

        intake1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()) {
            intake1.setPower(1 * gamepad1.right_stick_y*0.3);
            intake2.setPower(1 * gamepad1.right_stick_y*0.3);

        }
        // AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Right,2600,0.6,true, telemetry);
        // AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight, AutonomousCommon.StrafeDirection.Backward,2600,0.6,true, telemetry);
        // AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Left,2600,0.6,true, telemetry);
        //AutonomousCommon.macanumBox(frontLeft,rearLeft,frontRight,rearRight,1000,.3,true, telemetry);


    }

}