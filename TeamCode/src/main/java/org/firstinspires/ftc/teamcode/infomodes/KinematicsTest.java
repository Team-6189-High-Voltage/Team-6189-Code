package org.firstinspires.ftc.teamcode.infomodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Locations;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.odometry.Kinematics;

@TeleOp(group = "Info OpModes")
public class KinematicsTest extends OpMode {
    Robot robot = new Robot(telemetry);
    Kinematics kinematics = new Kinematics(robot);

    double x, y, r, p, fireAngle = 0;

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.resetEncoders();
        robot.noEncoders();
        kinematics.start();
    }

    @Override
    public void loop() {
        controlMovement();
        robot.launcher.setSpinSpeed(90, .7);
        if(gamepad1.x){
            fireAngle = Math.toDegrees(Math.atan((Locations.PowerShotHeight - Locations.robotLaunchHeight) / (Locations.fieldLength - kinematics.getGLOBAL_Y())));
        } else if (gamepad1.y) {
            fireAngle = Math.toDegrees(Math.atan((Locations.HighGoalHeight - Locations.robotLaunchHeight) / Locations.fieldLength - kinematics.getGLOBAL_Y()));
        } else if(gamepad1.dpad_up){
            robot.launcher.setSpinSpeed(500, .8);
        }

        kinematics.getPose().printPose(telemetry); // x, y, r
        telemetry.addData("/> GLOBAL X", kinematics.getGLOBAL_X());
        telemetry.addData("/> GLOBAL Y", kinematics.getGLOBAL_Y());
        telemetry.addData("/> ROTATION", robot.getRotation());
        telemetry.addData("/> ROTATION DEG", Math.toDegrees(robot.getRotation()));
        telemetry.addData("/> GLOBAL VELOCITY X", kinematics.gVelocityX);
        telemetry.addData("/> GLOBAL VELOCITY Y", kinematics.gVelocityY);
        telemetry.addData("/> AT POINT", kinematics.atPoint);
        telemetry.addData("/> SPIN SPEED", robot.launcher.getSpinSpeed());
        telemetry.addData("/> DESIRED FIRE ANGLE", fireAngle);
        telemetry.update();
    }

    void controlMovement() {
        p = gamepad1.right_trigger;
        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        r = gamepad1.right_stick_x;
        if (p < 0.25)
            p = 0.25;
        robot.move(x, y, r, p);
    }

    @Override
    public void stop() {
        kinematics.isRunning = false;
        robot.shutdown();
    }
}