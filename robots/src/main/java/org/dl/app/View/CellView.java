package org.dl.app.View;

import org.dl.app.TheGame.Cell;

import java.awt.*;

import static org.dl.app.View.DrawFigure.drawOval;
import static org.dl.app.View.DrawFigure.fillOval;

public class CellView {


    protected void drawCell(Graphics2D g, Cell cell){
        g.setColor(Color.green.darker());
        fillOval(g, (int)cell.getPositionX(), (int)cell.getPositionY(), 20, 20);
        g.setColor(Color.BLACK);
        drawOval(g, (int)cell.getPositionX(), (int)cell.getPositionY(), 20, 20);

    }
}