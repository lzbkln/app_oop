package org.dl.app.View;

import org.dl.app.TheGame.VarietyTargets;

import java.awt.*;

import static org.dl.app.View.DrawFigure.drawOval;
import static org.dl.app.View.DrawFigure.fillOval;

public class VarietyTargetsView {
    protected void drawTargets(Graphics2D g, VarietyTargets targets)
    {
        g.setColor(Color.blue);
        fillOval(g, (int)targets.getPositionX(), (int)targets.getPositionY(), 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, (int)targets.getPositionX(), (int)targets.getPositionY(), 5, 5);
    }
}