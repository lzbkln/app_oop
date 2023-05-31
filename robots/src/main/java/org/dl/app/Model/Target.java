package org.dl.app.Model;

import org.dl.app.TheGame.Positioned;

public class Target implements Entity, Positioned {
    private volatile int x;
    private volatile int y;

    public Target() {
        this.x = 150;
        this.y = 100;
    }

    public Target(int x, int y) {
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

    @Override
    public void update() {

    }
}
