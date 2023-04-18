package org.dl.app.Model;

import java.awt.*;

public class Math {

    private Robot robot;
    private Target target;
    private Dimension dimension;

    public Math() {
        this.robot = new Robot();
        this.target = new Target();
    }

    public void setTargetPosition(Point p) {
        target.setPositionX(p.x);
        target.setPositionY(p.y);
    }

    public Robot getRobot() {
        return robot;
    }

    public Target getTarget() {
        return target;
    }


    private static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return java.lang.Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;
        return asNormalizedRadians(java.lang.Math.atan2(diffY, diffX));
    }

    protected void onModelUpdateEvent() {
        double distance = distance(target.getPositionX(), target.getPositionY(),
                robot.getPositionX(), robot.getPositionY());
        if (distance < 0.5){
            return;
        }
        double velocity = Robot.maxVelocity;
        double angleToTarget = angleTo(robot.getPositionX(), robot.getPositionY(),
                target.getPositionX(), target.getPositionY());
        double angularVelocity = 0;
        if (java.lang.Math.abs(robot.getDirection() - angleToTarget) < 10e-7) {
            angularVelocity = robot.getDirection();
        } else if (robot.getDirection() >= java.lang.Math.PI) {
            if (robot.getDirection() - java.lang.Math.PI < angleToTarget && angleToTarget < robot.getDirection())
                angularVelocity = -Robot.maxAngularVelocity;
            else
                angularVelocity = Robot.maxAngularVelocity;
        } else {
            if (robot.getDirection() < angleToTarget && angleToTarget < robot.getDirection() + java.lang.Math.PI)
                angularVelocity = Robot.maxAngularVelocity;
            else
                angularVelocity = -Robot.maxAngularVelocity;
        }
        robot.moveRobot(velocity, angularVelocity, 10.0);
    }

    public static double applyLimits(double value, double min, double max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    public static double asNormalizedRadians(double angle) {
        double newAngle = angle;
        while (newAngle <= -180) newAngle += 360;
        while (newAngle > 180) newAngle -= 360;
        return newAngle;
    }

}
