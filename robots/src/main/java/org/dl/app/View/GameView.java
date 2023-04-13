package org.dl.app.View;

import org.dl.app.Model.AllModels;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    RobotView robotView;
    TargetView targetView;
    private final AllModels model;

    public GameView(AllModels model){
        this.model = model;
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
        robotView.drawRobot(g2d, model.getRobot());
        targetView.drawTarget(g2d, model.getTarget());
    }
}
