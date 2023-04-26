package GeneticAlgorithm;

import Model.Robot;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class Parasite extends Robot {
    private int ttl = 10;
    private int healthPoints = 5;
    Timer timer;
    private PropertyChangeSupport propChangeDispatcher = new PropertyChangeSupport(this);
    private boolean isDead = false;

    public Parasite(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new CloserToDeath(), 0, 1000);
    }

    class CloserToDeath extends TimerTask {
        public void run() {
            ttl--;
            if (ttl == 0){
                isDead = true;
                propChangeDispatcher.firePropertyChange("DIE", false, true);
            }
            //timer.cancel(); //Terminate the timer thread
        }
    }

    /*public void toDie()
    {
        propChangeDispatcher.firePropertyChange();
    }*/

    /*public void toDie(String text)
    {
        propChangeDispatcher.firePropertyChange(PROPERTY_NAME, oldValue, text);

    }
*/
    public void addTextChangeListener(PropertyChangeListener listener)
    {
        propChangeDispatcher.addPropertyChangeListener(listener);
    }

    public boolean isAlive(){return !isDead;}

    public int getFitness(){return ttl;}
}
