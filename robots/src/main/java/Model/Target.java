package Model;

import GeneticAlgorithm.Positioned;

public class Target implements Positioned {
    private volatile double x;
    private volatile double y;

    public Target() {
        this.x = 150;
        this.y = 100;
    }

    public Target(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setPositionX(int x) {
        this.x = x;
    }

    public void setPositionY(int y) {
        this.y = y;
    }

    public double getPositionX() {
        return x;
    }

    public double getPositionY() {
        return y;
    }

    /*@Override
    public void update() {

    }*/
}
