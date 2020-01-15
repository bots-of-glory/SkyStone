package org.firstinspires.ftc.teamcode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Test_Straight", group="testOp")
public class Test_Straight extends SkystoneBase {
    @Override
    public void runOpMode() throws InterruptedException {
        this.playSide = AutonomousCommon.PlayfieldSide.Blue;
        initMotors();
        waitForStart();
        int calibrationTest = (AutonomousCommon.convertInchesToPosition(10, false));
        int strafeTest = (AutonomousCommon.convertInchesToPosition(10,true));
        AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Forward,10,.6,opModeIsActive(), telemetry);
        AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Right,10,.6,opModeIsActive(),telemetry);

    }

}