package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AutonomousCommon.PlayfieldSide;


@Autonomous (name="LoadingZoneRedWithoutPlacement", group="Competition Autonomous")
public class LoadingZoneRedWithoutPlacement extends SkystoneVisionBase {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        playSide = PlayfieldSide.Red;
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
