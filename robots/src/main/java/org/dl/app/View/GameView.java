package org.dl.app.View;

import org.dl.app.Model.EntityStateProvider;
import org.dl.app.Model.GameState;
import org.dl.app.Model.Math;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    RobotView robotView;
    TargetView targetView;

    private boolean robotIsDead = false;
    private final EntityStateProvider entityState;

    public GameView(EntityStateProvider provider){
        this.entityState = provider;
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
        if (!robotIsDead){
            robotView.drawRobot(g2d, entityState.getCurrentRobot());
        }

        targetView.drawTarget(g2d, entityState.getCurrentTarget());
    }

    public void toKillRobot() {
        robotIsDead = true;
    }
}
