package org.dl.app.Model;

import java.awt.*;
import org.dl.app.Model.Math;
import org.dl.app.TheGame.Cell;
import org.dl.app.TheGame.Parasite;

public class GameState {

    private Parasite robot;
    private Target target;

    private Cell cell;

    public GameState() {
        this.target = new Target();
        this.robot = new Parasite(target);
        this.cell = new Cell();

    }

    public void changeTarget(Point p) {
        target.setPositionX(p.x);
        target.setPositionY(p.y);
    }

    public Robot getRobot() {
        return robot;
    }

    public Target getTarget() {
        return target;
    }

    public Cell getCell(){return cell;}

    public void update(){
        robot.update();
    }
}
