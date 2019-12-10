package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import org.firstinspires.ftc.teamcode.vision.*;
/**
 * Base Class for Skystone operations that require vision (Vuforia)
 */
public class SkystoneVisionBase extends SkystoneBase {
    MasterVision vision;
    SampleRandomizedPositions goldPosition;

    @Override
    public void initMotors(){
        super.initMotors();

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "AbVGrK7/////AAABmV5qNYRo8EpalbdT9iVSnmNR6wynVnTYxdfuU0jrIQJY3/bNzMRAOB9ew/OVmuVwRluGP3sUUHaNIgpXOii6OX5JQHTGyOeDMkVtqPdvynUdw7hRhLL2a8L8nQzJdH4jrKTCB6hAykKflqR4dykoml54fOnuTuXzGgwydwHCkcwt3UnDy/kCMrmSSx/9hBW21N4m6vhqzM9cdhUAGvvQAJPEE7WjrfT14Z4onzZXM185HCLKIEXcaJx10MaGO/xHchVtbvMGB2zDzFJ57uG2+AJopJtI+Qh1anzqoPnolZMUwJHRBhQnxis+QGpoL1RiJ6HqTRQr5mAEuP3q4wX5I1WXydNah5JoLgekylpWKANr\n";

        vision = new MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_LEFT);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time
    }

    /**
     * Attempts to locate the Skystone.
     */
    public  void locateSkystone(){
        telemetry.addLine("Begin locateSkystone");

        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.
        goldPosition = vision.getTfLite().getLastKnownSampleOrder();
        telemetry.addData("goldPosition was", goldPosition);
        telemetry.update();

        if(playSide== AutonomousCommon.PlayfieldSide.Blue){
        }
        if(playSide== AutonomousCommon.PlayfieldSide.Red){
        }
        telemetry.addLine("End locateSkystone");
    }
}
