package org.firstinspires.ftc.teamcode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Calibration_Straight", group="testOp")
public class Calibration_Straight extends SkystoneBase {
    @Override
    public void runOpMode() throws InterruptedException {
        AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Forward,1000,0.6,true, telemetry);

    }

}