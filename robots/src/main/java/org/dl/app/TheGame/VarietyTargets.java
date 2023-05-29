package org.dl.app.TheGame;

import org.dl.app.Model.EntityStateProvider;
import org.dl.app.Model.Robot;
import org.dl.app.Model.Target;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class VarietyTargets implements Positioned{
    Random random;
    private volatile double x;
    private volatile double y;

    private int ttl;

    Timer timer;

    private EntityStateProvider provider;

    class CloserToDeath extends TimerTask {
        public void run() {
            ttl--;
            if (ttl < 0){
                timer.cancel();
                setFeatures();
            }

        }
    }




    public VarietyTargets() {
        random = new Random();
        setFeatures();
    }

    private void toLiveALife(){
        timer.scheduleAtFixedRate(new CloserToDeath(), 0, 1000);
    }

    private void setFeatures(){
        timer = new Timer();
        ttl = random.nextInt(8, 20);
        this.x = random.nextInt(30, 350);
        this.y = random.nextInt(30, 350);
        toLiveALife();
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
        timer.cancel();
        setFeatures();
    }

    public void toChangeRobotState(Robot robot){
        robot.toChangeVelocity();
    }

}
