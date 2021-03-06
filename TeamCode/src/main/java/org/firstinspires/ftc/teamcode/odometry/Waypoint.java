package org.firstinspires.ftc.teamcode.odometry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Arrays;

public class Waypoint extends Pose {

    private double speed, error, angleAtPoint;

    public Waypoint(double x, double y, double r, double speed, double error) {
        super(x, y, r);
        this.speed = speed;
        this.error = error;
    }

    public double getAngleAtPoint() {
        return angleAtPoint;
    }

    public void setAngleAtPoint(double angleAtPoint) {
        this.angleAtPoint = angleAtPoint;
    }

    public double getSpeed() {
        return speed;
    }

    public double getError() {
        return error;
    }

    public void printWaypoint(Telemetry telemetry){
        double[] pointInfo = {getX(), getY(), getR(), getSpeed(), getError()};
        telemetry.addData("/> WAYPOINT", Arrays.toString(pointInfo));
    }
}
