package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Rotate_Test", group="testOp")
public class Rotate_Test extends SkystoneBase {
    @Override
    public void runOpMode() throws InterruptedException {
        this.playSide = AutonomousCommon.PlayfieldSide.Blue;
        initMotors();
        waitForStart();

        AutonomousCommon.macanumRotate(frontLeft,rearLeft,frontRight,rearRight,90,opModeIsActive(),telemetry, AutonomousCommon.RotateDegree.Positive);
    }

}