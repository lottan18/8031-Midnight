package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by andrew on 10/17/15.
 */
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class teleop extends OpMode {
    DcMotor motorRightFront;
    DcMotor motorRightBack;
    DcMotor motorLeftFront;
    DcMotor motorLeftBack;

    public teleop() {

    }


    public void init() {
        motorRightBack = hardwareMap.dcMotor.get("rightBack");
        motorRightFront = hardwareMap.dcMotor.get("rightFront");
        motorLeftBack = hardwareMap.dcMotor.get("leftBack");
        motorLeftFront = hardwareMap.dcMotor.get("leftFront");
    }


    public void loop() {
        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;

        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        right = (float) scaleInput(right);
        left = (float) scaleInput(left);

        motorRightFront.setPower(right);
        motorRightBack.setPower(right);
        motorLeftFront.setPower(left);
        motorLeftBack.setPower(left);

        telemetry.addData("Text", "*** Robot Data***");

    }


    public void stop() {

    }

    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};


        int index = (int) (dVal * 16.0);


        if (index < 0) {
            index = -index;
        }


        if (index > 16) {
            index = 16;
        }


        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }


        return dScale;
    }
}

