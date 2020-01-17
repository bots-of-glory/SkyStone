package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutonomousCommon.PlayfieldSide;

import static org.firstinspires.ftc.teamcode.AutonomousCommon.*;

public class SkystoneBase extends LinearOpMode {

    DcMotor lift1;
    DcMotor lift2;
    DcMotor frontLeft;
    DcMotor rearLeft;
    DcMotor frontRight;
    DcMotor rearRight;
    Servo leftServo;
    Servo rightServo;
    PlayfieldSide playSide;
    private CRServo clawServo;


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
        lift1 = hardwareMap.dcMotor.get("lift1");
        lift2 = hardwareMap.dcMotor.get("lift2");
        //sensorGyro = hardwareMap.gyroSensor.get("gyro");
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
        clawServo = hardwareMap.crservo.get("clawservo");

        //declare motor directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.FORWARD);
        lift1.setDirection(DcMotor.Direction.FORWARD);
        lift2.setDirection(DcMotor.Direction.REVERSE);
        //liftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.FORWARD);
        clawServo.setDirection(CRServo.Direction.FORWARD);

        //mrGyro = (ModernRoboticsI2cGyro) sensorGyro;
        telemetry.addLine("End moveToPlatform " + playSide.toString());
    }

    /**
     * Moves the robot to the platform depending on the side.
     */

    public void moveToPlatform() {
        telemetry.addLine("Begin moveToPlatform " + playSide.toString());
        //int strafeToPosition = AutonomousCommon.convertInchesToPosition(13.0,true);
        //int backupPosition = AutonomousCommon.convertInchesToPosition(36.0,false);
        //int forwardPosition = AutonomousCommon.convertInchesToPosition(2.0,false);
        double power = 0.6;

        macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, 2, power, opModeIsActive(), telemetry);
        if (playSide == PlayfieldSide.Blue) {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Right, 13, power, opModeIsActive(), telemetry);
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, 30, power, opModeIsActive(), telemetry);
            AutonomousCommon.servoMovement(leftServo, 90);
            AutonomousCommon.servoMovement(rightServo, -90);
        }
        if (playSide == PlayfieldSide.Red) {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Left, 12, power, opModeIsActive(), telemetry);
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, 30, power, opModeIsActive(), telemetry);
            AutonomousCommon.servoMovement(leftServo, 90);
            AutonomousCommon.servoMovement(rightServo, -90);
        }

        telemetry.addLine("End moveToPlatform " + playSide.toString());
    }
    public void moveToPlatformTimeBased() {
        telemetry.addLine("Begin moveToPlatform " + playSide.toString());
        //int strafeToPosition = AutonomousCommon.convertInchesToPosition(13.0,true);
        //int backupPosition = AutonomousCommon.convertInchesToPosition(36.0,false);
        //int forwardPosition = AutonomousCommon.convertInchesToPosition(2.0,false);
        double power = 0.6;
        try {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, 2, power, opModeIsActive(), telemetry);
            if (playSide == PlayfieldSide.Blue) {
                macanumMovementTimeBased(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Right, 13, power, opModeIsActive(), telemetry);
                macanumMovementTimeBased(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, 30, power, opModeIsActive(), telemetry);
                AutonomousCommon.servoMovement(leftServo, 90);
                AutonomousCommon.servoMovement(rightServo, -90);
            }
            if (playSide == PlayfieldSide.Red) {
                macanumMovementTimeBased(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Left, 12, power, opModeIsActive(), telemetry);
                macanumMovementTimeBased(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Backward, 30, power, opModeIsActive(), telemetry);
                AutonomousCommon.servoMovement(leftServo, 90);
                AutonomousCommon.servoMovement(rightServo, -90);
            }
        }catch (InterruptedException ex){

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
        //int forwardPosition = convertInchesToPosition(51,true);
        double power = 0.6;
        if (playSide==PlayfieldSide.Blue) {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 60, power, opModeIsActive(), telemetry);
        }
        if (playSide==PlayfieldSide.Red) {
            macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 60, power, opModeIsActive(), telemetry);
        }
        telemetry.addLine("End moveToSkybridge");
    }
    public void moveToSkybridgeTimeBased() {
        telemetry.addLine("Begin moveToSkybridge");
        //int forwardPosition = convertInchesToPosition(51,true);
        double power = 0.6;
        try{
            if (playSide==PlayfieldSide.Blue) {
                macanumMovementTimeBased(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 60, power, opModeIsActive(), telemetry);
            }
            if (playSide==PlayfieldSide.Red) {
                macanumMovementTimeBased(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 60, power, opModeIsActive(), telemetry);
            }
        }catch (InterruptedException ex){

        }
        telemetry.addLine("End moveToSkybridge");
    }
    /**
     * Moves the platform to the building site.
     */
    public void movePlatformToBuildingSite(){
        telemetry.addLine("Begin movePlatformToBuildingSite");
        double power = 0.6;
        try{
            macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,35,0.6,opModeIsActive(),telemetry);
            if (playSide==PlayfieldSide.Blue) {
                macanumRotate(frontLeft,rearLeft,frontRight,rearRight, 100,opModeIsActive(),telemetry,RotateDegree.Negative);
            }
            if (playSide==PlayfieldSide.Red) {
                macanumRotate(frontLeft,rearLeft,frontRight,rearRight, 100,opModeIsActive(),telemetry,RotateDegree.Positive);
            }
            AutonomousCommon.servoMovement(leftServo, 90);
            AutonomousCommon.servoMovement(rightServo, -90);
            sleep(1000); //wait
            macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,2,0.6,opModeIsActive(),telemetry);
            AutonomousCommon.servoMovement(leftServo, -90);
            AutonomousCommon.servoMovement(rightServo, 90);
            sleep(1000); //wait
            macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Backward,30,0.6,opModeIsActive(),telemetry);


            if (playSide==PlayfieldSide.Blue) {
                macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Right,20,0.6,opModeIsActive(),telemetry);
            }
            if (playSide==PlayfieldSide.Red){
                macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,20,0.6,opModeIsActive(),telemetry);
            }
            sleep(1000); //wait
            telemetry.addLine("End movePlatformToBuildingSite");
        } catch (InterruptedException ex){

        }
    }
    public void movePlatformToBuildingSiteTimeBased(){
        telemetry.addLine("Begin movePlatformToBuildingSite");
        double power = 0.6;
        try{
            macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,35,0.6,opModeIsActive(),telemetry);
            if (playSide==PlayfieldSide.Blue) {
                macanumRotate(frontLeft,rearLeft,frontRight,rearRight, 100,opModeIsActive(),telemetry,RotateDegree.Negative);
            }
            if (playSide==PlayfieldSide.Red) {
                macanumRotate(frontLeft,rearLeft,frontRight,rearRight, 100,opModeIsActive(),telemetry,RotateDegree.Positive);
            }
            servoMovement(leftServo, 90);
            servoMovement(rightServo, -90);
            sleep(1000); //wait
            macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,2,0.6,opModeIsActive(),telemetry);
            servoMovement(leftServo, -90);
            servoMovement(rightServo, 90);
            sleep(1000); //wait
            macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Backward,30,0.6,opModeIsActive(),telemetry);


            if (playSide==PlayfieldSide.Blue) {
                macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Right,20,0.6,opModeIsActive(),telemetry);
            }
            if (playSide==PlayfieldSide.Red){
                macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,20,0.6,opModeIsActive(),telemetry);
            }
            sleep(1000); //wait
            telemetry.addLine("End movePlatformToBuildingSite");
        } catch (InterruptedException ex){

        }
    }
    /**
     * Moves the robot to the Legos.
     */
    public void moveToLegos() throws InterruptedException {
        telemetry.addLine("Begin moveToLegos");
        if (playSide==PlayfieldSide.Red) {
            AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Right, 5, 0.6, opModeIsActive(), telemetry);
            AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 25, 0.6, opModeIsActive(), telemetry);
        }
        if (playSide==PlayfieldSide.Blue) {
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,5,0.6,opModeIsActive(),telemetry);
            AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 25, 0.6, opModeIsActive(), telemetry);


        }
        telemetry.addLine("End moveToLegos");
    }
    /**
     * Moves the robot to the Legos.
     */
    public void moveToScanPosition() throws InterruptedException {
        telemetry.addLine("Begin moveToLegos");
        if (playSide==PlayfieldSide.Red) {
            //AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Right, 5, 0.6, opModeIsActive(), telemetry);
            AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 13, 0.6, opModeIsActive(), telemetry);
        }
        if (playSide==PlayfieldSide.Blue) {
            //AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,5,0.6,opModeIsActive(),telemetry);
            AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 13, 0.6, opModeIsActive(), telemetry);


        }
        telemetry.addLine("End moveToLegos");
    }
    public void moveToLegosFromScanPosition() throws InterruptedException {
        telemetry.addLine("Begin moveToLegos");
        if (playSide==PlayfieldSide.Red) {
            //AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Right, 5, 0.6, opModeIsActive(), telemetry);
            AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 12, 0.6, opModeIsActive(), telemetry);
        }
        if (playSide==PlayfieldSide.Blue) {
            //AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,5,0.6,opModeIsActive(),telemetry);
            AutonomousCommon.macanumMovement(frontLeft, rearLeft, frontRight, rearRight, StrafeDirection.Forward, 12, 0.6, opModeIsActive(), telemetry);


        }
        telemetry.addLine("End moveToLegos");
    }

    /**
     * Grabs the lego.
     */
    public void grabLego(VUPosition position) throws InterruptedException{
        telemetry.addLine("Begin grabLego");

        if (playSide ==PlayfieldSide.Red) {
            if(position.direction==VUPoistionDirection.Left){
                AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight, StrafeDirection.Left,3,.6,opModeIsActive(),telemetry);
            }
            if(position.direction==VUPoistionDirection.Right){
                AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight, StrafeDirection.Right,3,.6,opModeIsActive(),telemetry);
            }
            if(position.direction==VUPoistionDirection.Center){
                AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight, StrafeDirection.Forward,3,.6,opModeIsActive(),telemetry);
            }
                clawServo.setPower(-1.0);
        }
        if (playSide == PlayfieldSide.Blue) {
                clawServo.setPower(-1.0);
        }
        Thread.sleep(2000);
        telemetry.addLine("End grabLego");
    }

    /**
     * moves the robot to the building zone.
     */
    public void moveToBuildingZone() throws InterruptedException{
        telemetry.addLine("Begin moveToBuildingZone");
        if(playSide==PlayfieldSide.Blue){
            clawServo.setPower(-1.0);
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Backward,15,0.6,opModeIsActive(),telemetry);
            AutonomousCommon.macanumRotate(frontLeft,rearLeft,frontRight,rearRight,90,opModeIsActive(),telemetry,RotateDegree.Positive);
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,60,0.6,opModeIsActive(),telemetry);
        }
        if(playSide==PlayfieldSide.Red){
            clawServo.setPower(-1.0);
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Backward,15,0.6,opModeIsActive(),telemetry);
            AutonomousCommon.macanumRotate(frontLeft,rearLeft,frontRight,rearRight,90,opModeIsActive(),telemetry,RotateDegree.Negative);
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,125,0.6,opModeIsActive(),telemetry);
        }
        telemetry.addLine("End moveToBuildingZone");
    }

    /**
     * Drops the lego.
     */
    public void dropLego() throws InterruptedException {
        telemetry.addLine("Begin dropLego");
        if(playSide==PlayfieldSide.Blue){
            AutonomousCommon.macanumRotate(frontLeft,rearLeft,frontRight,rearRight,90,opModeIsActive(),telemetry,RotateDegree.Negative);
            AutonomousCommon.motorLift(lift1,lift2,opModeIsActive(),telemetry);
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,25,0.6,opModeIsActive(),telemetry);
            clawServo.setPower(1.0);

        }
        if (playSide==PlayfieldSide.Red){
            AutonomousCommon.macanumRotate(frontLeft,rearLeft,frontRight,rearRight,90,opModeIsActive(),telemetry,RotateDegree.Positive);
            AutonomousCommon.motorLift(lift1,lift2,opModeIsActive(),telemetry);
            AutonomousCommon.macanumMovement(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,25,0.6,opModeIsActive(),telemetry);
            clawServo.setPower(1.0);

        }
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

    public void tempSkybridgeParkWallTimeBased() {
        double power = 1.0;
        telemetry.addLine("Begin tempSkybridgeParkWall");
        try{
            if(playSide==PlayfieldSide.Blue){
                AutonomousCommon.macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Right,AutonomousCommon.convertInchesToPosition(20.0,false),power,opModeIsActive(),telemetry);
            }
            if(playSide==PlayfieldSide.Red){
                AutonomousCommon.macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,AutonomousCommon.convertInchesToPosition(20.0,false),power,opModeIsActive(),telemetry);
            }
        }catch (InterruptedException ex){

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
    public void tempSkybridgeParkAwayTimeBased() {
        double power = 1.0;
        telemetry.addLine("Begin tempSkybridgeParkAway");
        try{
            if(playSide==PlayfieldSide.Blue){
                AutonomousCommon.macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,tempMovePosition,power,opModeIsActive(),telemetry);
                AutonomousCommon.macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Right,tempStrafeToWall,power,opModeIsActive(),telemetry);
            }
            if(playSide==PlayfieldSide.Red){
                AutonomousCommon.macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Forward,tempMovePosition,power,opModeIsActive(),telemetry);
                AutonomousCommon.macanumMovementTimeBased(frontLeft,rearLeft,frontRight,rearRight,StrafeDirection.Left,tempStrafeToWall,power,opModeIsActive(),telemetry);
            }
        }catch (InterruptedException ex){

        }
        telemetry.addLine("End tempSkybridgeParkAway");
    }
}
