package org.dl.app.View;

import org.dl.app.Model.Target;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static org.dl.app.View.DrawFigure.drawOval;
import static org.dl.app.View.DrawFigure.fillOval;

public class TargetView {
    protected void drawTarget(Graphics2D g, Target target)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, (int)target.getPositionX(), (int)target.getPositionY(), 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, (int)target.getPositionX(), (int)target.getPositionY(), 5, 5);
    }
}
