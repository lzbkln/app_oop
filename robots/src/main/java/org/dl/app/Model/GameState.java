package org.dl.app.Model;

import java.awt.*;
import org.dl.app.Model.Math;

public class GameState {

    private Robot robot;
    private Target target;

    public GameState() {
        this.target = new Target();
        this.robot = new Robot(target);

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

    public void update(){
        robot.update();
    }
}
