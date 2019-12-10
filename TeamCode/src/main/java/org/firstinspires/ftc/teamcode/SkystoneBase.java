package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.AutonomousCommon.FlapperServos;
import org.firstinspires.ftc.teamcode.AutonomousCommon.MovementMotors;
import org.firstinspires.ftc.teamcode.AutonomousCommon.PlayfieldSide;

import static org.firstinspires.ftc.teamcode.AutonomousCommon.*;

public class SkystoneBase extends LinearOpMode {

    DcMotor liftMotor;
    DcMotor frontLeft;
    DcMotor rearLeft;
    DcMotor frontRight;
    DcMotor rearRight;
    Servo leftServo;
    Servo rightServo;
    GyroSensor sensorGyro;
    ModernRoboticsI2cGyro mrGyro;
    PlayfieldSide playSide;

    @Override
    public void runOpMode() throws InterruptedException {
    }
    public void initMotors(){
        telemetry.addLine("Begin initMotors " + playSide.toString());
        //liftMotor = hardwareMap.dcMotor.get("liftMotor");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        rearLeft = hardwareMap.dcMotor.get("rearLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        rearRight = hardwareMap.dcMotor.get("rearRight");
        //sensorGyro = hardwareMap.gyroSensor.get("gyro");
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");

        //declare motor directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.FORWARD);
        //liftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.FORWARD);

        //mrGyro = (ModernRoboticsI2cGyro) sensorGyro;
        telemetry.addLine("End moveToPlatform " + playSide.toString());
    }

    /**
     * Moves the robot to the platform depending on the side.
     */

    public void moveToPlatform() {
        telemetry.addLine("Begin moveToPlatform " + playSide.toString());
        int strafeToPosition = AutonomousCommon.convertInchesToPosition(13.0,true);
        int backupPosition = AutonomousCommon.convertInchesToPosition(36.0,false);
        int forwardPosition = AutonomousCommon.convertInchesToPosition(2.0,false);
        double power = 1.0;
        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, forwardPosition, power, opModeIsActive(), telemetry);
        if (playSide == PlayfieldSide.Blue) {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Right, strafeToPosition, power, opModeIsActive(), telemetry);
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, backupPosition, power, opModeIsActive(), telemetry);
        }
        if (playSide == PlayfieldSide.Red) {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Left, strafeToPosition, power, opModeIsActive(), telemetry);
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, backupPosition, power, opModeIsActive(), telemetry);
        }


        telemetry.addLine("End moveToPlatform " + playSide.toString());
    }

    /**
     * Lowers the flappers in position to grab the platform
     */
    public  void lowerFlappers() {
        telemetry.addLine("Begin lowerFlappers " + playSide.toString());

        AutonomousCommon.servoMovement(leftServo, -90);
        AutonomousCommon.servoMovement(rightServo, 90);
        sleep(2000); //wait
        telemetry.addLine("End lowerFlappers " + playSide.toString());
    }

    /**
     * Moves the robot to the platform depending on the side.
     */
    public void moveToSkybridge() {
        telemetry.addLine("Begin moveToSkybridge");
        int forwardPosition = convertInchesToPosition(51,true);
        double power = 1.0;
        if (playSide==PlayfieldSide.Blue) {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Left, forwardPosition, power, opModeIsActive(), telemetry);
        }
        if (playSide==PlayfieldSide.Red) {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Right, forwardPosition, power, opModeIsActive(), telemetry);
        }
        telemetry.addLine("End moveToSkybridge");
    }

    /**
     * Moves the platform to the building site.
     */
    public void movePlatformToBuildingSite(){
        telemetry.addLine("Begin movePlatformToBuildingSite");
        int towToWall = AutonomousCommon.convertInchesToPosition(38.0,false);
        int towForward = AutonomousCommon.convertInchesToPosition(1,false);
        double power = 1.0;

        macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,towToWall,power,opModeIsActive(),telemetry);
        AutonomousCommon.servoMovement(leftServo, 90);
        AutonomousCommon.servoMovement(rightServo, -90);
        sleep(2000); //wait
        macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Backward,towForward,power,opModeIsActive(),telemetry);
        telemetry.addLine("End movePlatformToBuildingSite");
    }

    /**
     * Moves the robot to the Legos.
     */
    public void moveToLegos(){
        telemetry.addLine("Begin moveToLegos");

        telemetry.addLine("End moveToLegos");
    }

    /**
     * Grabs the lego.
     */
    public void grabLego(){
        telemetry.addLine("Begin grabLego");

        telemetry.addLine("End grabLego");
    }

    /**
     * moves the robot to the building zone.
     */
    public void moveToBuildingZone(){
        telemetry.addLine("Begin moveToBuildingZone");
        if(playSide==PlayfieldSide.Blue){
        }
        if(playSide==PlayfieldSide.Red){
        }
        telemetry.addLine("End moveToBuildingZone");
    }

    /**
     * Drops the lego.
     */
    public void dropLego() {
        telemetry.addLine("Begin dropLego");
        telemetry.addLine("End dropLego");
    }
    /**
     * Temporary method.
     */
    int tempMovePosition = convertInchesToPosition(22.0,false);
    int tempStrafeToWall = convertInchesToPosition(32.0, true);

    public void tempSkybridgeParkWall() {
        double power = 1.0;
        telemetry.addLine("Begin tempSkybridgeParkWall");
        if(playSide==PlayfieldSide.Blue){
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Right,AutonomousCommon.convertInchesToPosition(20.0,false),power,opModeIsActive(),telemetry);
        }
        if(playSide==PlayfieldSide.Red){
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,AutonomousCommon.convertInchesToPosition(20.0,false),power,opModeIsActive(),telemetry);
        }
        telemetry.addLine("End tempSkybridgeParkWall");
    }
    /**
     * Temporary method.
     */
    public void tempSkybridgeParkAway() {
        double power = 1.0;
        telemetry.addLine("Begin tempSkybridgeParkAway");
        if(playSide==PlayfieldSide.Blue){
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,tempMovePosition,power,opModeIsActive(),telemetry);
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Right,tempStrafeToWall,power,opModeIsActive(),telemetry);
        }
        if(playSide==PlayfieldSide.Red){
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,tempMovePosition,power,opModeIsActive(),telemetry);
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,tempStrafeToWall,power,opModeIsActive(),telemetry);
        }
        telemetry.addLine("End tempSkybridgeParkAway");
    }
}
