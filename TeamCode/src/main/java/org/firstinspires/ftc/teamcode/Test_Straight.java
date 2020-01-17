package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Test_Straight", group="testOp")
public class Test_Straight extends SkystoneBase {
    @Override
    public void runOpMode() throws InterruptedException {
        this.playSide = AutonomousCommon.PlayfieldSide.Blue;
        initMotors();
        waitForStart();
        //int calibrationTest = (AutonomousCommon.convertInchesToPosition(10, false));
        //int strafeTest = (AutonomousCommon.convertInchesToPosition(10,true));
        AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Forward,10,.6,opModeIsActive(), telemetry);
        AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,AutonomousCommon.StrafeDirection.Right,10,.6,opModeIsActive(),telemetry);


//        int target = 1000;
//        int startPosition = frontLeft.getCurrentPosition();
//        frontLeft.setPower(.6);
//        frontLeft.setTargetPosition(startPosition+target);
//        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (frontLeft.isBusy() ) {
//            telemetry.addData("Status","Busy");
//        }
//        else{
//            telemetry.addData("Status","Free");
//            sleep(500);
//            target=-target;
//            frontLeft.setTargetPosition(startPosition+target);
//        }
//        telemetry.update();
//        frontLeft.setPower(0);
//
//        target = 1000;
//        startPosition = frontRight.getCurrentPosition();
//        frontRight.setPower(.6);
//        frontRight.setTargetPosition(startPosition+1000);
//        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (frontRight.isBusy() ) {
//            telemetry.addData("Status","Busy");
//        }
//        else{
//            telemetry.addData("Status","Free");
//            sleep(500);
//            target=-target;
//            frontRight.setTargetPosition(startPosition+target);
//        }
//        telemetry.update();
//        frontRight.setPower(0);
//
//        target = 1000;
//        startPosition = rearLeft.getCurrentPosition();
//        rearLeft.setPower(.6);
//        rearLeft.setTargetPosition(startPosition+1000);
//        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (rearLeft.isBusy() ) {
//            telemetry.addData("Status","Busy");
//        }
//        else{
//            telemetry.addData("Status","Free");
//            sleep(500);
//            target=-target;
//            rearLeft.setTargetPosition(startPosition+target);
//        }
//        telemetry.update();
//        rearLeft.setPower(0);
//
//        target = 1000;
//        startPosition = rearRight.getCurrentPosition();
//        rearRight.setPower(.6);
//        rearRight.setTargetPosition(startPosition+1000);
//        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (rearRight.isBusy() ) {
//            telemetry.addData("Status","Busy");
//        }
//        else{
//            telemetry.addData("Status","Free");
//            sleep(500);
//            target=-target;
//            rearRight.setTargetPosition(startPosition+target);
//        }
//        telemetry.update();
//        rearRight.setPower(0);

    }

}