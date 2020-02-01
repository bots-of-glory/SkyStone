package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.AutonomousCommon.PlayfieldSide;


@Autonomous (name="LoadingZoneBlue", group="Competition Autonomous")
public class LoadingZoneBlue extends SkystoneVisionBase{

    @Override
    public void runOpMode() throws InterruptedException {
        this.playSide = PlayfieldSide.Blue;
        AutonomousCommon.VUPosition position;

        initMotors();
        waitForStart();
        moveToScanPosition();
        position = locateSkystone();
        //moveToLegosFromScanPositionTimeBased();
        //grabLegoTimeBased(position);
       // moveToBuildingZoneTimeBased();
       // dropLego();
    }
}
