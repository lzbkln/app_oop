package org.dl.app.TheGame;

import org.dl.app.Model.EntityStateProvider;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Cell implements Positioned {

    private volatile double x;

    private EntityStateProvider provider;

    private boolean isSeek = false;

    public boolean isDead = false;

    private Parasite parasite;
    private volatile double y;

    Random random;

    private int ttl;

    Timer timer;

    public Cell() {
        random = new Random();
        setFeatures();
    }

    private void setFeatures(){
        ttl = random.nextInt(10, 40);
        this.x = random.nextInt(20, 300);
        this.y = random.nextInt(20, 300);
        timer = new Timer();
        toLiveALife(1000);
    }

    public double getPositionX() {
        return x;
    }

    public double getPositionY() {
        return y;
    }

    private void toLiveALife(int period) {
        timer.scheduleAtFixedRate(new CloserToDeath(), 0, period);
    }

    public void setProvider(EntityStateProvider provider) {
        this.provider = provider;
    }

    class CloserToDeath extends TimerTask {
        public void run() {
            ttl--;
            if (ttl == 0) {
                timer.cancel();
                if (isSeek){parasite.toStarveAgain();}
                setFeatures();
            }

        }
    }

    public void toSlowlyDieCauseParasite(Parasite _parasite) {
        timer.cancel();
        timer = new Timer();
        toLiveALife(500);
        parasite = _parasite;
        isSeek = true;
    }

    public void toRecover() {
        timer.cancel();
        isSeek = false;
        timer = new Timer();
        toLiveALife(1000);
    }

    public int getTtl(){
        return ttl;
    }

    public boolean isDead(){
        return isDead;
    }
}