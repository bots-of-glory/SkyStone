package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Device;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "DriverControl" , group = "testOp")
//@Disabled

public class DriverControl extends LinearOpMode {
    //DcMotors
    private DcMotor frontLeft;      //1     Hub1 P0
    private DcMotor rearLeft;       //2     Hub1 P2
    private DcMotor frontRight;     //3     Hub1 P1
    private DcMotor rearRight;      //4     Hub1 P3
    private Servo leftServo;
    private Servo rightServo;
    private DcMotor stackMotor1;
    private DcMotor stackMotor2;
    private Servo clawServo;
    // private Servo leftServo;
    // private Servo rightServo;
    //private DcMotor liftMotor;      //5     Hub2 P0
    //private DcMotor flipperMotor;       //6     Hub2 P1
    //private DcMotor pulleyMotor;    //7     Hub2 P3
    //private DcMotor revLift;        //8     Hub2 P2

    //Servos
   // private Servo armServo;
    //private Servo depositServo;     //2     Hub2 P?


    @Override
    public void runOpMode () throws InterruptedException {
//Declare DcMotors
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        rearLeft = hardwareMap.dcMotor.get("rearLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        rearRight = hardwareMap.dcMotor.get("rearRight");
        stackMotor1 = hardwareMap.dcMotor.get("stackMotor1");
        stackMotor2 = hardwareMap.dcMotor.get("stackMotor2");
        clawServo = hardwareMap.servo.get("clawservo");
      //liftMotor = hardwareMap.dcMotor.get("liftMotor");
       //flipperMotor = hardwareMap.dcMotor.get("flipperMotor");
       //pulleyMotor = hardwareMap.dcMotor.get("pulleyMotor");
       //revLift = hardwareMap.dcMotor.get("revLift");
//Declare Servos
      leftServo = hardwareMap.servo.get("leftServo");
      rightServo = hardwareMap.servo.get("rightServo");
//Declare DcMotor Directions
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        stackMotor1.setDirection(DcMotor.Direction.REVERSE);
        stackMotor2.setDirection(DcMotor.Direction.REVERSE);
        stackMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stackMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawServo.setDirection(Servo.Direction.FORWARD);
        //liftMotor.setDirection(DcMotor.Direction.REVERSE);
        //flipperMotor.setDirection(DcMotor.Direction.FORWARD);
        //pulleyMotor.setDirection(DcMotor.Direction.FORWARD);
        //revLift.setDirection(DcMotor.Direction.FORWARD);
//Declare Servo Directions
        leftServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.FORWARD);


        //stackMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //stackMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


//Declare Mecanum Drive Variables
        double drive;
        double strafe;
        double rotate;

        double front_left;
        double rear_left;
        double front_right;
        double rear_right;

//Declare Speed Variables(0 = slow)(1 = fast)
        int speedState = 1;
        double fast = 1.0;
        double slow = 0.6;

//Declare Direction Variable(s)
        int direction = 1;

//Declare Continuous Servo Variables
        int flipperState = 0;
        boolean buttonState = false;
        {
            waitForStart();
            while (opModeIsActive()) {
//Speed
                if(gamepad1.x)
                {
                    speedState = 1;
                }
                else if(gamepad1.a)
                {
                    speedState = 0;
                }

                if(gamepad1.right_bumper)
                {
                    direction = 1;
                }
                else if(gamepad1.left_bumper)
                {
                    direction = -1;
                }

//Declare Values to Mecanum Variables
                drive = gamepad1.right_stick_y * direction;
                strafe = gamepad1.right_stick_x * direction;
                rotate = gamepad1.left_stick_x * direction;
//Mecanum direction calculation
                if(direction == -1) {
                    front_left = drive - strafe + rotate;
                    rear_left = drive + strafe + rotate;
                    front_right = drive + strafe - rotate;
                    rear_right = drive - strafe - rotate;
                }

                else
                {
                    front_left = drive - strafe - rotate;
                    rear_left = drive + strafe - rotate;
                    front_right = drive + strafe + rotate;
                    rear_right = drive - strafe + rotate;
                }
//-----------------------------------Gamepad 1 Start------------------------------------------------
//Mecanum Drive
                if(speedState == 1)
                {
                    frontLeft.setPower(limit(front_left)* fast);
                    rearLeft.setPower(limit(rear_left)* fast);
                    frontRight.setPower(limit(front_right)* fast);
                    rearRight.setPower(limit(rear_right)* fast);
                }
                else
                {
                    frontLeft.setPower(limit(front_left)* slow);
                    rearLeft.setPower(limit(rear_left)* slow);
                    frontRight.setPower(limit(front_right)* slow);
                    rearRight.setPower(limit(rear_right)* slow);
                }

//------------------------------------Gamepad 1 End-------------------------------------------------
// ------------------------------------Gamepad 2 Start-------------------------------------------------
//Flipper up
                if (gamepad2.dpad_up) {
                    AutonomousCommon.servoMovement(leftServo, 90);
                    AutonomousCommon.servoMovement(rightServo, -90);
//Flipper down
                }
                if (gamepad2.dpad_down) {
                    AutonomousCommon.servoMovement(leftServo, -90);
                    AutonomousCommon.servoMovement(rightServo, 90);
                }

                if (gamepad2.dpad_left) {
                    AutonomousCommon.servoMovement(clawServo, 90);
                }
                //Forward
                else if (gamepad2.dpad_right) {
                    AutonomousCommon.servoMovement(clawServo, -90);
                } else {
                }

               /* if (gamepad2.right_bumper){
                    stackMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    stackMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    stackMotor1.setTargetPosition(23);
                    stackMotor2.setTargetPosition(-23);

                    stackMotor1.setPower(.75);
                    stackMotor2.setPower(.75);

                }

                if (gamepad2.left_bumper) {
                    stackMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    stackMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    stackMotor1.setTargetPosition(43);
                    stackMotor2.setTargetPosition(-43);

                    stackMotor1.setPower(.75);
                    stackMotor2.setPower(.75);
                }*/
                //43 bottom
                //23 brick stack
                //start stacking motor
                stackMotor1.setPower( 0.35 * gamepad2.right_stick_y);
                stackMotor2.setPower( -0.35 * gamepad2.right_stick_y);

                int position = stackMotor1.getCurrentPosition();
                telemetry.addData("Encoder Position", position);
                telemetry.update();

            }
//------------------------------------Gamepad 2 End-------------------------------------------------


            idle();
            }
        }
    public double limit(double number)
    {
        if(number < -1.0)
        {
            return -1.0;
        }
        else if(number > 1)
        {
            return 1;
        }
        else
        {
            return number;
        }
    }
}
