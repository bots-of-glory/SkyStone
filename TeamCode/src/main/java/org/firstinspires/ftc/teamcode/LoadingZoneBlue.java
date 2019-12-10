package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.AutonomousCommon.PlayfieldSide;


@Autonomous (name="LoadingZoneBlue", group="Competition Autonomous")
public class LoadingZoneBlue extends SkystoneVisionBase{


    @Override
    public void runOpMode() throws InterruptedException {
        this.playSide = PlayfieldSide.Blue;
        initMotors();
        waitForStart();
        tempSkybridgeParkAway();
        //1st Skystone
        //moveToLegos();
        //locateSkystone();
        //grabLego();
        //moveToBuildingZone();
        //dropLego();

        //2nd Skystone
        //moveToLegos();
        //locateSkystone();
        //grabLego();
        //moveToBuildingZone();
        //dropLego();

    }
}
