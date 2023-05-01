package View;

import Model.Robot;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static View.DrawFigure.drawOval;
import static View.DrawFigure.fillOval;

public class RobotView {

    private static int round(double value) {
        return (int) (value + 0.5);
    }

    protected void drawRobot(Graphics2D g, Robot robot)
    {
        int robotCenterX = round(robot.getPositionX());
        int robotCenterY = round(robot.getPositionY());
        AffineTransform t = AffineTransform.getRotateInstance(robot.getDirection(), robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);


    }
}
