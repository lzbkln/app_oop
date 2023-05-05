package org.dl.app.Model;

public class Robot implements Entity{
    private double positionX = 100;
    private double positionY = 100;
    private Target target;

    protected EntityStateProvider provider;

    public Robot(Target target){
        this.target = target;
    }

    private double velocity;
    public static final double maxVelocity = 0.05;
    public static final double maxAngularVelocity = 0.001;

    private double duration = 10.0;

    private volatile double robotDirection = 0;

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getDirection() {
        return robotDirection;
    }

    @Override
    public void update() {//moveRobot
        double distance = Math.distance(target.getPositionX(), target.getPositionY(),
                positionX, positionY);
        if (distance < 0.5){
            return;
        }
        double angleToTarget = Math.angleTo(positionX, positionY,
                target.getPositionX(), target.getPositionY());
        double angularVelocity = 0;
        if (java.lang.Math.abs(robotDirection - angleToTarget) < 10e-7) {
            angularVelocity = robotDirection;
        } else if (robotDirection >= java.lang.Math.PI) {
            if (robotDirection - java.lang.Math.PI < angleToTarget && angleToTarget < robotDirection)
                angularVelocity = -Robot.maxAngularVelocity;
            else
                angularVelocity = Robot.maxAngularVelocity;
        } else {
            if (robotDirection < angleToTarget && angleToTarget < robotDirection + java.lang.Math.PI)
                angularVelocity = Robot.maxAngularVelocity;
            else
                angularVelocity = -Robot.maxAngularVelocity;
        }
        move(angularVelocity);
    }

    private void move(double angularVelocity){
        velocity = Math.applyLimits(maxVelocity, 0, maxVelocity);
        angularVelocity = Math.applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = positionX + velocity / angularVelocity *
                (java.lang.Math.sin(robotDirection  + angularVelocity * duration) -
                        java.lang.Math.sin(robotDirection));
        if (!Double.isFinite(newX)) {
            newX = positionX + velocity * duration * java.lang.Math.cos(robotDirection);
        }
        double newY = positionY - velocity / angularVelocity *
                (java.lang.Math.cos(robotDirection  + angularVelocity * duration) -
                        java.lang.Math.cos(robotDirection));
        if (!Double.isFinite(newY)) {
            newY = positionY + velocity * duration * java.lang.Math.sin(robotDirection);
        }
        positionX = newX;
        positionY = newY;
        double newDirection = Math.asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
        if (positionX < 0) {newDirection = -540; positionX = 10;}
        else if (positionY > 400) {newDirection = -8; positionY = 390;}
        else if (positionX > 500) {newDirection = 8; positionX = 395;}
        else if (positionY < 0) {newDirection = 8; positionY = 10;}
        else newDirection = Math.asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
    }

    public void setProvider(EntityStateProvider provider){
        this.provider = provider;
    }

}
