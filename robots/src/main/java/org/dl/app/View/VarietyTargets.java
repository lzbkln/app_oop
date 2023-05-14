package org.dl.app.View;

import org.dl.app.Model.EntityStateProvider;

import java.util.Random;

public class VarietyTargets {
    Random random;
    private volatile double x;
    private volatile double y;
    private EntityStateProvider provider;

    public VarietyTargets() {
        random = new Random();
        this.x = random.nextInt(50, 350);
        this.y = random.nextInt(50, 350);
    }

    public double getPositionX() {
        return x;
    }

    public double getPositionY() {
        return y;
    }
    public void setProvider(EntityStateProvider provider) {
        this.provider = provider;
    }

}
