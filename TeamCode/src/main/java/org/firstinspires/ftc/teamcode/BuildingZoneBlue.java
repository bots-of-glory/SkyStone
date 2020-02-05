package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.AutonomousCommon.PlayfieldSide;

@Autonomous (name="BuildingZoneBlue", group="Competition Autonomous")
public class BuildingZoneBlue extends  SkystoneBase {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        this.playSide = PlayfieldSide.Blue;
        initMotors();
        waitForStart();
        moveToPlatform();
        lowerFlappers();
        movePlatformToBuildingSite();
        moveToSkybridge();
    }
}
