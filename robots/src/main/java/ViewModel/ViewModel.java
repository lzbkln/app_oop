package ViewModel;


import GeneticAlgorithm.Parasite;
import Model.GameModel;
import Model.Robot;
import View.GameView;

import javax.swing.event.AncestorListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

public class ViewModel implements PropertyChangeListener {
    //собираем всё вместе

    private GameModel gameModel;

    private Parasite parasite;
    private GameView gameView;
    private final Timer m_timer = initTimer();

    private static java.util.Timer initTimer() {
        java.util.Timer timer = new java.util.Timer("events generator", true);
        return timer;
    }

    public ViewModel(GameModel gameModel, GameView gameView) {

        this.gameModel = gameModel;
        this.gameView = gameView;
        this.parasite = (Parasite)gameModel.getRobot();

        parasite.addTextChangeListener(this);
        gameModel.getmodel().addTextChangeListener(this);

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

        gameView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameModel.change(e.getPoint());
                gameView.repaint();
            }
        });

        /*gameView.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent event){
                System.out.println("hidden");
            }

        });*/

        /*gameView.addAncestorListener(AncestorListener listener);

        gameView.addVetoableChangeListener();*/

    }
    public GameView getGameView(){return gameView;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!parasite.isAlive()){
            gameView.toKill();
        }
        /*if (evt.getPropertyName().equals("parasite")){
            if (!parasite.isAlive()){
                gameView.toKill();
            }
        }*/
        /*else {
            if (!gameModel.getmodel().isMoved()){

            }
        }*/
    }
}