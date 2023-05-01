package GeneticAlgorithm;

import java.util.Timer;
import java.util.TimerTask;

public class Cell implements Positioned{

    private volatile double x;
    private volatile double y;
    private boolean isDead = false;

    private int ttl = 15;//потом сделать рандом

    Timer timer;

    public Cell() {
        this.x = 200;
        this.y = 200;
        timer = new Timer();
        toLiveALife();
        //потом сделать рандом
    }

    public double getPositionX() {
        return x;
    }

    public double getPositionY() {
        return y;
    }

    public int toFeed(int timeToLoose){
        return toDie(timeToLoose);
    }

    private int toDie(int timeToLoose){
        ttl = ttl/2;
        return ttl;
    }

    private void toLiveALife(){
        timer.scheduleAtFixedRate(new CloserToDeath(), 0, 1000);
    }
    class CloserToDeath extends TimerTask {
        public void run() {
            ttl--;
            if (ttl == 0){
                isDead = true;
                //propChangeDispatcher.firePropertyChange("DIE", false, true);
                timer.cancel();
            }

            //timer.cancel(); //Terminate the timer thread
        }
    }
}