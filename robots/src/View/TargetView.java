package View;

import Model.Target;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static View.DrawFigure.drawOval;
import static View.DrawFigure.fillOval;

public class TargetView {
    protected void drawTarget(Graphics2D g, Target target)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, target.getPositionX(), target.getPositionY(), 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, target.getPositionX(), target.getPositionY(), 5, 5);
    }
}
