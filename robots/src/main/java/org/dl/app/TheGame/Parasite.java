package org.dl.app.TheGame;

import org.dl.app.Model.Robot;
import org.dl.app.Model.Target;

import java.util.Timer;
import java.util.TimerTask;

public class Parasite extends Robot {
    private int ttl = 14;

    public double prevDistance = 0;

    public Condition condition;
    Timer timer;


    public boolean isDead = false;

    public Parasite(Target target){
        super(target);
        timer = new Timer();
        toLiveALife();
        condition = Condition.TO_MOVE;
    }

    class CloserToDeath extends TimerTask {
        public void run() {
            ttl--;
            if (ttl < 13){
                provider.changeRobotTtl(ttl);
            }
            System.out.println(ttl + "left");
            //if (ttl<0){timer.cancel();}
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
        timer.cancel();
        cell.toSlowlyDieCauseParasite(this);

    }

    public void toStarveAgain(){
        /*if (timer == null){
            System.out.println("null");
        }*/
        timer = new Timer();
        toLiveALife();
    }


    private void toLiveALife(){
        timer.scheduleAtFixedRate(new CloserToDeath(), 0, 1000);
    }

    public int getTTL(){
        return ttl;
    }

    public void update(){
        super.update();
        if (interactions != null){
            interactions.toInteractWithCells(target, this);
            interactions.toInteractWithVarietys(target, this);
        }

   }

}