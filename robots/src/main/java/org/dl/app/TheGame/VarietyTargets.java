package org.dl.app.TheGame;

import org.dl.app.Model.EntityStateProvider;
import org.dl.app.Model.Robot;

import java.util.Random;

public class VarietyTargets implements Positioned{
    Random random;
    private volatile double x;
    private volatile double y;

    private EntityStateProvider provider;

    public VarietyTargets() {
        random = new Random();
        setFeatures();
    }

    private void setFeatures(){
        this.x = random.nextInt(30, 350);
        this.y = random.nextInt(30, 350);
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

    public void toChangeItsState(){
        provider.changeVarietyTargerCondition(this);
    }

    public void toChangeRobotState(Robot robot){
        robot.toChangeVelocity();
    }

}
