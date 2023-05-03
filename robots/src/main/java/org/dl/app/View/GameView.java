package org.dl.app.View;

import org.dl.app.Model.GameState;
import org.dl.app.Model.Math;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    RobotView robotView;
    TargetView targetView;
    //через что-то, интерфейс entityCopied
    //private final Math model;
    private final GameState gameState;

    public GameView(GameState gameState){
        this.gameState = gameState;
        robotView = new RobotView();
        targetView = new TargetView();
    }

    public void update(){
        EventQueue.invokeLater(this::repaint);
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        robotView.drawRobot(g2d, gameState.getRobot());
        targetView.drawTarget(g2d, gameState.getTarget());
    }
}
