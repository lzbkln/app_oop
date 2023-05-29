package org.dl.app.TheGame;

import org.dl.app.Model.Robot;
import org.dl.app.Model.Target;

import java.util.Timer;
import java.util.TimerTask;

public class Parasite extends Robot {
    private float ttl = 18;

    Timer timer;
    public double prevDistance = 0;

    public Condition condition;

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
                provider.changeRobotTtl((int)ttl);
            }
            System.out.println("closer to death ttl -" + ttl);
            if (ttl <= 0){
                isDead = true;
                timer.cancel();
                if (provider != null){
                    provider.changeRobotCondition();
                }
            }
        }
    }

    public void toParasitize(Cell cell){
        timer.cancel();
        timer = new Timer();
        toEat();
        condition = Condition.TO_PARASIRIZE;
        cell.toSlowlyDieCauseParasite(this);
    }

    public void toStarveAgain(){
        timer.cancel();
        System.out.println("to st again, ttl - " + ttl);
        if (ttl == 0){
            isDead = true;
            provider.changeRobotCondition();
        }
        timer = new Timer();
        toLiveALife();
        System.out.println("to live a life");
    }

    class Eating extends TimerTask{

        @Override
        public void run() {
            ttl+=0.5;
            provider.changeRobotTtl((int)ttl);
        }
    }
    private void toEat(){timer.scheduleAtFixedRate(new Eating(), 0, 1000);}

    private void toLiveALife(){
        timer.scheduleAtFixedRate(new CloserToDeath(), 0, 1000);
    }

    public void update(){
        super.update();
        if (interactions != null){
            interactions.toInteractWithCells(target, this);
            interactions.toInteractWithVarietys(target, this);
        }

   }

}