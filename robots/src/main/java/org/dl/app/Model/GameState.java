package org.dl.app.Model;

import java.awt.*;
import org.dl.app.Model.Math;



public class GameState {

    private Robot robot;
    private Target target;

    public GameState() {
        this.target = new Target();
        this.robot = new Robot(target);

        //this.math = new Math();
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
        /*double distance = Math.distance(target.getPositionX(), target.getPositionY(),
                robot.getPositionX(), robot.getPositionY());
        if (distance < 0.5){
            return;
        }
        double velocity = Robot.maxVelocity;
        double angleToTarget = Math.angleTo(robot.getPositionX(), robot.getPositionY(),
                target.getPositionX(), target.getPositionY());
        double angularVelocity = 0;
        if (java.lang.Math.abs(robot.getDirection() - angleToTarget) < 10e-7) {
            angularVelocity = robot.getDirection();
        } else if (robot.getDirection() >= java.lang.Math.PI) {
            if (robot.getDirection() - java.lang.Math.PI < angleToTarget && angleToTarget < robot.getDirection())
                angularVelocity = -Robot.maxAngularVelocity;
            else
                angularVelocity = Robot.maxAngularVelocity;
        } else {
            if (robot.getDirection() < angleToTarget && angleToTarget < robot.getDirection() + java.lang.Math.PI)
                angularVelocity = Robot.maxAngularVelocity;
            else
                angularVelocity = -Robot.maxAngularVelocity;
        }*/
        robot.update();
    }
}
