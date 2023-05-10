package org.dl.app.View;

import org.dl.app.Model.EntityStateProvider;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameView extends JPanel {
    RobotView robotView;
    TargetView targetView;

    Map<Integer, CellView> cellView = new HashMap<>();

    CreatorTimer timer;

    private boolean robotIsDead = false;

    //private boolean cellIsDead = false;

    private boolean[] cellIsDead = new boolean[]{false, false, false, false, false};
    private final EntityStateProvider entityState;

    public GameView(EntityStateProvider provider) {
        this.entityState = provider;
        robotView = new RobotView();
        targetView = new TargetView();
        for (int i = 0; i < 5; i++) {
            cellView.put(i + 1, new CellView());
        }
        //CreatorTimer timer = new CreatorTimer();
        timer = provider.getCurrentCreatorTimer();
        timer.progressBar.setString("TTL");
        timer.progressBar.setStringPainted(true);
        this.add(timer.progressBar);
    }

    public void update() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        /*if (!cellIsDead){
            cellView.drawCell(g2d, entityState.getCurrentCell());
        }*/

        for (int i = 0; i < 5; i++) {
            if (!cellIsDead[i]) {
                cellView.get(i + 1).drawCell(g2d, entityState.getCurrentCell().get(i + 1));
            }
        }
        if (!robotIsDead) {
            robotView.drawRobot(g2d, entityState.getCurrentRobot());
        }
        targetView.drawTarget(g2d, entityState.getCurrentTarget());

    }

    public void toKillRobot() {
        robotIsDead = true;
    }

    public void toKillCell(int i) {
        cellIsDead[i - 1] = true;
    }
}
