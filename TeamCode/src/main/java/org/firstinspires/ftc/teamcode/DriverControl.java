package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "DriverControl" , group = "testOp")
//@Disabled

public class DriverControl extends LinearOpMode {
    //DcMotors
    private DcMotor frontLeft;      //1     Hub1 P0
    private DcMotor rearLeft;       //2     Hub1 P2
    private DcMotor frontRight;     //3     Hub1 P1
    private DcMotor rearRight;      //4     Hub1 P3

    private DcMotor intake1;
    private DcMotor intake2;

    private DcMotor lift1;
    private DcMotor lift2;

    private Servo leftServo;
    private Servo rightServo;
    private Servo clawServo;
    private CRServo clawExtendServo;


    @Override
    public void runOpMode () throws InterruptedException {
        //Declare DcMotors
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        rearLeft = hardwareMap.dcMotor.get("rearLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        rearRight = hardwareMap.dcMotor.get("rearRight");

        intake1 = hardwareMap.dcMotor.get("intake1");
        intake2 = hardwareMap.dcMotor.get("intake2");

        lift1 = hardwareMap.dcMotor.get("lift1");
        lift2 = hardwareMap.dcMotor.get("lift2");

        //Declare Servos
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");

        clawServo = hardwareMap.servo.get("clawservo");
        clawExtendServo = hardwareMap.crservo.get("clawExtendServo");
        //Declare DcMotor Directions
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        intake1.setDirection(DcMotor.Direction.REVERSE);
        intake2.setDirection(DcMotor.Direction.FORWARD);

        lift1.setDirection(DcMotor.Direction.FORWARD);
        lift2.setDirection(DcMotor.Direction.REVERSE);

        //Declare Servo Directions
        leftServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.FORWARD);
        clawServo.setDirection(Servo.Direction.FORWARD);
        clawExtendServo.setDirection(CRServo.Direction.FORWARD);

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
        double intakeOffset = 0.3;
        float   leftPower, rightPower, xValue, yValue;
        //Declare Direction Variable(s)
        int direction = 1;

        //Declare Continuous Servo Variables
        int flipperState = 0;
        boolean buttonState = false;
        {
            waitForStart();
            while (opModeIsActive()) {
//-----------------------------------Gamepad 1 Start------------------------------------------------
                //Speed
                if(gamepad1.x)
                {
                    speedState = 1;
                }
                else if(gamepad1.a)
                {
                    speedState = 0;
                }
                //Intake On/Off
                if (gamepad1.b){
                    intake1.setPower(0);
                    intake2.setPower(0);
                }

                if (gamepad1.y) {
                    intake1.setPower(1*intakeOffset);
                    intake2.setPower(1*intakeOffset);
                }
                //Movement Direction
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

                lift1.setPower(Range.clip(gamepad1.right_stick_y*slow, -1.0, 1.0));
                lift2.setPower(Range.clip(gamepad1.right_stick_y*slow, -1.0, 1.0));

                //Flipper up
                if (gamepad2.dpad_up) {
                    AutonomousCommon.servoMovement(leftServo, 1);
                    AutonomousCommon.servoMovement(rightServo, 0);
                //Flipper down
                }

                if (gamepad2.dpad_down) {
                    AutonomousCommon.servoMovement(leftServo, 0);
                    AutonomousCommon.servoMovement(rightServo, 1);
                }

                if (gamepad2.dpad_left) {
                    AutonomousCommon.servoMovement(clawServo, 1);
                }
                if (gamepad2.dpad_right) {
                    AutonomousCommon.servoMovement(clawServo, 0);
                }

                if(gamepad2.x)
                {
                    clawExtendServo.setPower(1.0);
                }
                else if(gamepad2.a)
                {
                    clawExtendServo.setPower(-1.0);
                }
                else {
                    clawExtendServo.setPower(0);
                }


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
