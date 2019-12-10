package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.vision.MasterVision;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;
@Autonomous (name="Prototype", group="Competition Autonomous")
public class Prototype extends LinearOpMode {
    MasterVision vision;
    SampleRandomizedPositions goldPosition;
    DcMotor liftMotor;
    DcMotor frontLeft;
    DcMotor rearLeft;
    DcMotor frontRight;
    DcMotor rearRight;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        //declare motors
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        rearLeft = hardwareMap.dcMotor.get("rearLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        rearRight = hardwareMap.dcMotor.get("rearRight");

        //declare motor directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.FORWARD);

        double turnSpeed = 0.2;
        int target = 0;

        waitForStart();
        AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight, AutonomousCommon.StrafeDirection.Forward,1000,1.0,true, telemetry);
        // AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Right,2600,0.6,true, telemetry);
        // AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight, AutonomousCommon.StrafeDirection.Backward,2600,0.6,true, telemetry);
        // AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Left,2600,0.6,true, telemetry);
        //AutonomousCommon.macanumBox(frontLeft,rearLeft,frontRight,rearRight,1000,.3,true, telemetry);


    }

}