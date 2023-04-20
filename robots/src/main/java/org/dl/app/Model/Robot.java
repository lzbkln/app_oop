package org.dl.app.Model;

public class Robot{
    private double positionX = 100;
    private double positionY = 100;

    public static final double maxVelocity = 0.07;
    public static final double maxAngularVelocity = 0.007;

    private volatile double robotDirection = 0;

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void setDirection(double robotDirection) {
        this.robotDirection = robotDirection;
    }

    public double getDirection() {
        return robotDirection;
    }

    public void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = Math.applyLimits(velocity, 0, maxVelocity);
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

    /*@Override
    public void update() {

    }*/
}
