package View;

import GeneticAlgorithm.Parasite;
import Model.AllModels;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    ParasiteView robotView;
    boolean isDead = false;
    TargetView targetView;
    private final AllModels model;

    public GameView(AllModels model){
        this.model = model;
        robotView = new ParasiteView();
        targetView = new TargetView();
    }
    public void toKill(){isDead = true;}

    public void update(){
        EventQueue.invokeLater(this::repaint);
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if (!isDead){
            robotView.drawRobot(g2d, model.getRobot());
        }

        targetView.drawTarget(g2d, model.getTarget());
    }
}
