package GeneticAlgorithm;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class Cell implements Positioned {

    private volatile double x;

    private boolean isSeek = false;

    private Parasite parasite;
    private volatile double y;
    private boolean isDead = false;

    private int ttl = 20;//потом сделать рандом

    private PropertyChangeSupport propChangeDispatcher = new PropertyChangeSupport(this);

    Timer timer;

    public Cell() {
        this.x = 200;
        this.y = 200;
        timer = new Timer();
        toLiveALife(1000);
        //потом сделать рандом
    }

    public void addTextChangeListener(PropertyChangeListener listener) {
        propChangeDispatcher.addPropertyChangeListener(listener);
    }

    public double getPositionX() {
        return x;
    }

    public double getPositionY() {
        return y;
    }

    public int toFeed(int timeToLoose) {
        return toDie(timeToLoose);
    }

    private int toDie(int timeToLoose) {
        ttl = ttl / 2;
        return ttl;
    }

    private void toLiveALife(int period) {
        timer.scheduleAtFixedRate(new CloserToDeath(), 0, period);
    }

    class CloserToDeath extends TimerTask {
        public void run() {
            ttl--;
            if (ttl == 0) {
                isDead = true;
                if (isSeek){parasite.toStarveAgain();}
                timer.cancel();
                propChangeDispatcher.firePropertyChange("cell", false, true);
            }

            //timer.cancel(); //Terminate the timer thread
        }
    }

    public void toSlowlyDieCauseParasite(Parasite _parasite) {
        timer.cancel();
        timer = new Timer();
        toLiveALife(500);
        parasite = _parasite;
        isSeek = true;
        /*if (isDead) {
            parasite.toStarveAgain();
        }*/

    }

    public boolean isDead() {
        return isDead;
    }
}