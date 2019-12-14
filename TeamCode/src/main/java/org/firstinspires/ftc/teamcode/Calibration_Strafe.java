package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Calibration_Strafe", group="testOp")
public class Calibration_Strafe extends SkystoneBase {
    @Override
    public void runOpMode() throws InterruptedException {
        AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Right,1000,0.6,true, telemetry);

    }

}