package org.dl.app.TheGame;

import org.dl.app.Model.Robot;
import org.dl.app.Model.Target;

import java.util.Timer;
import java.util.TimerTask;

public class Parasite extends Robot {
    private int ttl = 10;

    public Condition condition;
    Timer timer;

    private boolean isDead = false;

    public Parasite(Target target){
        super(target);
        timer = new Timer();
        toLiveALife();
        condition = Condition.TO_MOVE;
    }

    class CloserToDeath extends TimerTask {
        public void run() {
            ttl--;
            System.out.println(ttl + "left");
            if (ttl == 0){
                isDead = true;
                timer.cancel();
                if (provider != null){
                    provider.changeRobotCondition();
                }
            }

            //timer.cancel(); //Terminate the timer thread
        }
    }


    public void toParasitize(Cell cell){
        cell.toSlowlyDieCauseParasite(this);
        timer.cancel();
    }

    public void toStarveAgain(){
        timer = new Timer();
        toLiveALife();
    }


    private void toLiveALife(){
        timer.scheduleAtFixedRate(new CloserToDeath(), 0, 1000);
    }

    public boolean isAlive(){return !isDead;}

}