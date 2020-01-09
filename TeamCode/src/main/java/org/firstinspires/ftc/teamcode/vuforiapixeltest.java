package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.vuforia.Frame;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
        import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
        import org.firstinspires.ftc.robotcore.external.ClassFactory;
        import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
        import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
        import org.firstinspires.ftc.robotcore.external.Telemetry;
        import java.util.Arrays;
        import java.lang.reflect.Array;
        import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import org.firstinspires.ftc.robotcore.external.Telemetry;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.vuforia.*;
        import java.nio.ByteBuffer;
        import java.util.List;
        import org.firstinspires.ftc.robotcore.external.ClassFactory;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.*;
        import org.firstinspires.ftc.robotcore.external.navigation.*;
        import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
        import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
        import org.firstinspires.ftc.robotcore.external.ClassFactory;
        import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
        import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
        import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
        import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
        import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
        import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
        import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
        import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;

@Autonomous
public class vuforiapixeltest extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    // TODO: Enter your own Vuforia Key
    private static final String VUFORIA_KEY = "AZLRyGv/////AAABmUBYMKfdK0Aomzp0Nt6VEkguSViSeAoA+D5usIvm/kqre4G73/hhstqirGs3OdwbEScv9C37DcPjYjTLiH/InhJiNOZ2sCHrUQ/pjXVvB+yUgmXFKdyUsiZNDNJgnyc7CnBFQ6FyrVRsLEv7vg17WNPdh7mgumtlb2LwD0N0m1D9ntZg90cLxto9GTfr+V6xS0k/NEsBPuljDPx2Hql3GAD39HZ3Ls62r306oUm9g+UVFNNUdKuvGc3xxtrTQAZHSzjufW7N232cZTPWyE508Bh8sKWUCJMB7ZuQ7TPZqS+VWPMdxUo9N//J5iHFoDidnjHb5Z6d2qKmqDk3/rIa5pdzc5bY+fhkr27+SegoN7WK\"";


    Frame frame = null;
    Image image = null;

    boolean lastA = false;

    private int stoneLeft = 0;
    private int stoneRight = 0;
    private int stoneBottom = 0;
    private int stoneTop = 0;
    private double stoneHeading = 0;
    private int[] sampleStrafe = {-4,0,8};
    private int TRAVEL = 76;
    private double SAMPLE_HEADING = 25.5;

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    ElapsedTime timer = new ElapsedTime();
    WebcamName webcamName = null;

    int stones = 2;

    @Override
    public void runOpMode() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        // while(!isStopRequested()) {

        // }



        telemetry.update();

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        //waitForStart();
        int pos = 1;
        boolean detected = false;
        while (!isStopRequested()) {
            if (getSkystone()) {
                if (stoneHeading < -10) pos = 0;
                else if (stoneHeading > 10) pos = 2;
                else pos = 1;
            }
            if (gamepad1.y) stones = 2;
            if (gamepad1.a) stones = 1;
            telemetry.addData("pos",pos);
            telemetry.addData("angle estimate", stoneHeading);
            telemetry.addData("stones", stones);
            try {
                frame = vuforia.getFrameQueue().take();
            }
            catch (InterruptedException e) {

            }
            if (tfod != null) {
                tfod.activate();
            }
            if(frame != null) {
                for(int i = 0; i < frame.getNumImages(); i++) {
                    image = frame.getImage(0);
                    // telemetry.log().add("Picture type = %d %d x %d",image.getFormat(),image.getWidth(),image.getHeight());
                    // telemetry.log().add("Num Bytes = %d",image.getPixels().remaining());
                    ByteBuffer pixels = image.getPixels();
                    byte[] pixelArray = new byte[pixels.remaining()];
                    pixels.get(pixelArray, 0, pixelArray.length);
                    telemetry.addData("pixelArray", (pixelArray[0] & 0xFF));
                    telemetry.addData("stoneLeft", stoneLeft);
                    telemetry.addData("stoneRight", stoneRight);
                    boolean a = gamepad1.a;
                    if(a) {
                        // int[] skyStoneArr = findSkyStone3(pixelArray, (stoneTop + stoneBottom) / 2);
                        displayStoneInfo(telemetry);
                        // telemetry.addData("findSkyStone", skyStoneArr[0] + " " + skyStoneArr[1]);
                        // int position = 0;
                        if(detected()) detected = true;
                        // if(detected) {
                        //     if(skyStoneArr[3] < 250) {
                        //         position = 1;
                        //     }
                        //     else if(skyStoneArr[3] < 450) {
                        //         position = 2;
                        //     }
                        //     else if(skyStoneArr[3] < 650) {
                        //         position = 3;
                        //     }
                        // }
                        // telemetry.addData("position", position);
                        if(detected)
                            telemetry.addData("position", findSkyStoneAvg(pixelArray, (stoneBottom + stoneTop) / 2));
                    }

                    lastA = a;
                    // telemetry.addData("pixelArray length", pixelArray.length);
                    telemetry.update();
                }
            }
            telemetry.update();


        }
        tfod.shutdown();

        if (tfod != null) {
            tfod.shutdown();
        }
    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam");
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        // Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        Vuforia.setFrameFormat(PIXEL_FORMAT.GRAYSCALE, true);
        vuforia.setFrameQueueCapacity(1);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    private boolean detected() {
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if(updatedRecognitions != null) {
                for (Recognition recognition : updatedRecognitions) {
                    stoneLeft = (int) (recognition.getLeft());
                    stoneRight = (int) recognition.getRight();
                    stoneBottom = (int) recognition.getBottom();
                    stoneTop = (int) recognition.getTop();
                    stoneHeading = recognition.estimateAngleToObject(AngleUnit.DEGREES);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean getSkystone() {
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                int i = 0;
                for (Recognition recognition : updatedRecognitions) {
                    if(recognition.getLabel().equals(LABEL_FIRST_ELEMENT)) continue;
                    stoneLeft = (int) (recognition.getLeft());
                    stoneRight = (int) recognition.getRight();
                    stoneBottom = (int) recognition.getBottom();
                    stoneTop = (int) recognition.getTop();
                    stoneHeading = recognition.estimateAngleToObject(AngleUnit.DEGREES);
                    return true;
                }
            }
        }
        return false;
    }

    //returns the column
    private int findSkyStone(byte[] pixelArray, int left, int right, int top, int bot) {
        int minC = 0;
        int row = (top + bot) / 2;
        int l = row * 640 + left;
        int r = row * 640 + right;
        byte min = pixelArray[l];
        for(int i = l; i < r; i++) {
            if(min < pixelArray[i]) {
                min = pixelArray[i];
                minC = i;
            }
        }
        return minC - (row * 640);
    }

    private int[] findSkyStone2(byte[] pixelArray, int left, int right, int top, int bot) {
        int row = (top + bot) / 2;
        int l = row * 640 + left;
        int r = row * 640 + right;
        byte[] vals = new byte[r - l + 1];
        int min = pixelArray[l];
        int avg = 0;
        int minC = l;
        for(int i = l; i < r; i++) {
            vals[i - l] = pixelArray[i];
            avg += pixelArray[i];
            if(min < pixelArray[i]) {
                minC = i;
                min = pixelArray[i];
            }
        }
        if(r - l != 0) avg /= (r - l);
        // dark values will be positive for the purposes of the algorithm
        int[] diff = new int[r - l + 1];
        for(int i = l; i <= r; i++) {
            diff[i - l] = -(pixelArray[i] - avg);
        }

        int max = diff[0];
        int[] sum = new int[diff.length];
        int beginMax = 0, endMax = 0, begin = 0, end = 0;
        for(int i = 0; i < diff.length; i++) {
            if(diff[i] < 0) {
                sum[i] = 0;
                begin = i + 1;
                continue;
            }
            if(i > 0)
                sum[i] = sum[i - 1] + diff[i];
            end = i;
            if(max < sum[i]) {
                max = sum[i];
                beginMax = begin;
                endMax = i;
            }
            else if(max == sum[i]) {
                if((endMax - beginMax) < (end - begin)) {
                    beginMax = begin;
                    endMax = end;
                }
                // else if((beginMax - endMax) == (begin - end))
            }
        }
        int[] arr = new int[4];
        arr[0] = beginMax + left;
        arr[1] = endMax + left;
        arr[2] = minC;
        arr[3] = (arr[0] + arr[1]) / 2;
        return arr;
    }

    private int[] findSkyStone3(byte[] pixelArray, int row) {
        // row = (top + bot) / 2;
        int l = row * 640;
        int r = row * 640 + 640;
        byte[] vals = new byte[r - l + 1];
        int min = pixelArray[l];
        int avg = 0;
        int minC = l;
        for(int i = l; i < r; i++) {
            vals[i - l] = pixelArray[i];
            avg += pixelArray[i];
            if(min < pixelArray[i]) {
                minC = i;
                min = pixelArray[i];
            }
        }
        if(r - l != 0) avg /= (r - l);
        // dark values will be positive for the purposes of the algorithm
        int[] diff = new int[r - l + 1];
        for(int i = l; i <= r; i++) {
            diff[i - l] = -(pixelArray[i] - avg);
        }

        int max = diff[0];
        int[] sum = new int[diff.length];
        int beginMax = 0, endMax = 0, begin = 0, end = 0;
        for(int i = 0; i < diff.length; i++) {
            if(diff[i] < 0) {
                sum[i] = 0;
                begin = i + 1;
                continue;
            }
            if(i > 0)
                sum[i] = sum[i - 1] + diff[i];
            end = i;
            if(max < sum[i]) {
                max = sum[i];
                beginMax = begin;
                endMax = i;
            }
            else if(max == sum[i]) {
                if((endMax - beginMax) < (end - begin)) {
                    beginMax = begin;
                    endMax = end;
                }
                // else if((beginMax - endMax) == (begin - end))
            }
        }
        int[] arr = new int[4];
        arr[0] = beginMax;
        arr[1] = endMax;
        arr[2] = minC;
        arr[3] = (arr[0] + arr[1]) / 2;
        return arr;
    }

    private int findSkyStoneAvg(byte[] pixelArray, int row) {
        int l = row * 640;
        int r = (row + 1) * 640;
        int avg1 = 0, avg2 = 0, avg3 = 0;
        int n1 = 0, n2 = 0, n3 = 0;
        for(int i = l; i < r; i++) {
            if(i < l + 213) {
                avg1 += pixelArray[i] & 0xFF;
                n1++;
            }
            else if(i < l + 427) {
                avg2 += pixelArray[i] & 0xFF;
                n2++;
            }
            else {
                avg3 += pixelArray[i] & 0xFF;
                n3++;
            }
        }
        avg1 /= n1;
        avg2 /= n2;
        avg3 /= n3;
        telemetry.addData("row", row);
        telemetry.addData("avg1", avg1);
        telemetry.addData("avg2", avg2);
        telemetry.addData("avg3", avg3);
        // telemetry.update();
        int min = Math.min(Math.min(avg1, avg2), avg3);
        if(min == avg1) return 1;
        if(min == avg2) return 2;
        return 3;
    }

    private void displayStoneInfo(Telemetry telemetry) {
        telemetry.addData("stoneLeft", stoneLeft);
        telemetry.addData("stoneRight", stoneRight);
        telemetry.addData("stoneTop", stoneTop);
        telemetry.addData("stoneBottom", stoneBottom);
    }
}
