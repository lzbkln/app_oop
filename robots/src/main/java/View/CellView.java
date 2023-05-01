package View;

import GeneticAlgorithm.Cell;
import Model.Robot;

import java.awt.*;

import static View.DrawFigure.drawOval;
import static View.DrawFigure.fillOval;

public class CellView {




    protected void drawCell(Graphics2D g, Cell cell){
        g.setColor(Color.green.darker());
        fillOval(g, (int)cell.getPositionX(), (int)cell.getPositionY(), 30, 30);
        g.setColor(Color.BLACK);
        drawOval(g, (int)cell.getPositionX(), (int)cell.getPositionY(), 30, 30);

    }
}
