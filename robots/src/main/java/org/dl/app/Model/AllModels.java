package org.dl.app.Model;

import java.awt.*;

public class AllModels {

    private Robot robot;
    private Target target;
    private Dimension dimension;

    public AllModels() {
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
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
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
        if (angleToTarget > robot.getDirection()) {
            angularVelocity = Robot.maxAngularVelocity;
        }
        if (angleToTarget < robot.getDirection()) {
            angularVelocity = -Robot.maxAngularVelocity;
        }
        moveRobot(velocity, angularVelocity, 10);
    }

    public static double applyLimits(double value, double min, double max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    private void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = applyLimits(velocity, 0, Robot.maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -Robot.maxAngularVelocity, Robot.maxAngularVelocity);
        double newX = robot.getPositionX() + velocity / angularVelocity *
                (Math.sin(robot.getDirection() + angularVelocity * duration) -
                        Math.sin(robot.getDirection()));
        if (!Double.isFinite(newX)) {
            newX = robot.getPositionX() + velocity * duration * Math.cos(robot.getDirection());
        }
        double newY = robot.getPositionY() - velocity / angularVelocity *
                (Math.cos(robot.getDirection() + angularVelocity * duration) -
                        Math.cos(robot.getDirection()));
        if (!Double.isFinite(newY)) {
            newY = robot.getPositionY() + velocity * duration * Math.sin(robot.getDirection());
        }
        robot.setPositionX(newX);
        robot.setPositionY(newY);
        double newDirection = asNormalizedRadians(robot.getDirection() + angularVelocity * duration);
        robot.setDirection(newDirection);
    }

    public static double asNormalizedRadians(double angle) {
        double newAngle = angle;
        while (newAngle <= -180) newAngle += 360;
        while (newAngle > 180) newAngle -= 360;
        return newAngle;
    }

}
