package org.firstinspires.ftc.teamcode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Calibration_Strafe", group="testOp")
public class Calibration_Strafe extends SkystoneBase {
    @Override
    public void runOpMode() throws InterruptedException {
        this.playSide = AutonomousCommon.PlayfieldSide.Blue;
        initMotors();
        waitForStart();
        AutonomousCommon.macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Right,10,0.6,true, telemetry);

    }

}