package org.dl.app.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dl.app.TheGame.Cell;
import org.dl.app.TheGame.Parasite;
import org.dl.app.View.CreatorTimer;

public class GameState {

    private Parasite robot;
    private Target target;

    private Map<Integer, Cell> cellList = new HashMap<>();

    private CreatorTimer creatorTimer = new CreatorTimer();

    public GameState() {
        this.target = new Target();
        this.robot = new Parasite(target);
        for (int i = 0; i < 5; i++){
            cellList.put(i+1, new Cell());
        }
        //this.cell = new Cell();

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

    public Map<Integer, Cell> getCell(){return cellList;}

    public CreatorTimer getCreatorTimer(){return creatorTimer;}

    public void update(){
        robot.update();
    }
}
