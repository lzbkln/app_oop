package View;

import GeneticAlgorithm.Parasite;
import Model.AllModels;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    ParasiteView robotView;
    boolean robotIsDead = false;
    boolean cellIsDead = false;
    TargetView targetView;

    CellView cellView;
    private final AllModels model;

    public GameView(AllModels model){
        this.model = model;
        robotView = new ParasiteView();
        targetView = new TargetView();
        cellView = new CellView();
    }
    public void toKillParasite(){robotIsDead = true;}
    public void toKillCell(){cellIsDead = true;}

    public void update(){
        EventQueue.invokeLater(this::repaint);
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        targetView.drawTarget(g2d, model.getTarget());
        //cellView.drawCell(g2d, model.getCell());
        if (!cellIsDead){
            cellView.drawCell(g2d, model.getCell());
        }
        if (!robotIsDead){
            robotView.drawRobot(g2d, model.getRobot());
        }

    }
}
