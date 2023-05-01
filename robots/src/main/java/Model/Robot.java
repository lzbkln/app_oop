package Model;

import GeneticAlgorithm.Positioned;

public class Robot implements Positioned {
    private double positionX = 100;
    private double positionY = 100;

    public static final double maxVelocity = 0.05;
    public static final double maxAngularVelocity = 0.005;

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

    /*@Override
    public void update() {

    }*/
}
