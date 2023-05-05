package org.dl.app.View;

import org.dl.app.Model.EntityStateProvider;
import org.dl.app.Model.GameState;
import org.dl.app.Model.Math;
import org.dl.app.TheGame.Cell;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    RobotView robotView;
    TargetView targetView;

    CellView cellView;

    private boolean robotIsDead = false;

    private boolean cellIsDead = false;
    private final EntityStateProvider entityState;

    public GameView(EntityStateProvider provider){
        this.entityState = provider;
        robotView = new RobotView();
        targetView = new TargetView();
        this.cellView = new CellView();
    }

    public void update(){
        EventQueue.invokeLater(this::repaint);
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        if (!cellIsDead){
            cellView.drawCell(g2d, entityState.getCurrentCell());
        }
        if (!robotIsDead){
            robotView.drawRobot(g2d, entityState.getCurrentRobot());
        }

        targetView.drawTarget(g2d, entityState.getCurrentTarget());
    }

    public void toKillRobot() {
        robotIsDead = true;
    }

    public void toKillCell() {
        cellIsDead = true;
    }
}
