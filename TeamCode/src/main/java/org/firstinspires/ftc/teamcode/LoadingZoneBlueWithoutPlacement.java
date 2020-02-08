package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousCommon.PlayfieldSide;


@Autonomous (name="LoadingZoneBlueWithoutPlacement", group="Competition Autonomous")
public class LoadingZoneBlueWithoutPlacement extends SkystoneVisionBase{

    @Override
    public void runOpMode() throws InterruptedException {
        this.playSide = PlayfieldSide.Blue;
        AutonomousCommon.VUPosition position;
        initMotors();
        waitForStart();
        moveToScanPosition();
        position = locateSkystone();
        moveToLegosFromScanPosition(position);
        grabLego();
        moveToBuildingZone(position);
        dropLegoWithoutPlacement();
        parkUnderSkybridgeWithoutPlacement();
    }
}
