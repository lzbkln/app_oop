package org.dl.app.ViewModel;


import org.dl.app.Model.EntityStateProvider;
import org.dl.app.Model.GameModel;
import org.dl.app.View.GameView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

public class ViewModel implements PropertyChangeListener {
    private GameModel gameModel;
    private GameView gameView;

    private EntityStateProvider provider;
    private final Timer m_timer = initTimer();

    private static java.util.Timer initTimer() {
        java.util.Timer timer = new java.util.Timer("events generator", true);
        return timer;
    }

    public ViewModel(GameModel gameModel, GameView gameView, EntityStateProvider provider) {

        this.gameModel = gameModel;
        this.gameView = gameView;
        this.provider = provider;
        this.provider.addTextChangeListener(this);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameView.update();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameModel.update();
            }
        }, 0, 10);

        gameView.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent e) {
                gameModel.change(e.getPoint());
            }
        });

    }
    public GameView getGameView(){return gameView;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("parasite")){
            gameView.toKillRobot();
        } else if (evt.getPropertyName().equals("cell")) {
           Integer i = (Integer) evt.getNewValue();
            gameView.toKillCell(i);
        }else if (evt.getPropertyName().equals("ttl")){
            int time = (int) evt.getNewValue();
            gameModel.getCreatorTimer().setTTL(time);
        }else {
            int t = (int)evt.getNewValue();
            gameView.toKillVarietyTarget(t);
        }

    }
}