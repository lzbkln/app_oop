package Model;

import GeneticAlgorithm.Cell;
import GeneticAlgorithm.Condition;
import GeneticAlgorithm.Parasite;
import GeneticAlgorithm.Positioned;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AllModels {
    private double prevDistance = 0;
    private Parasite robot;
    private Target target;
    private Cell cell;
    private Dimension dimension;

    //private PropertyChangeSupport propChangeDispatcher = new PropertyChangeSupport(this);

    public AllModels() {
        this.robot = new Parasite();
        this.target = new Target();
        this.cell = new Cell();
    }

    public void setTargetPosition(Point p) {
        target.setPositionX(p.x);
        target.setPositionY(p.y);
    }

    public Robot getRobot() {
        return robot;
    }

    public Target getTarget() {
        return target;
    }

    public Cell  getCell(){return cell;}

    /*public void addTextChangeListener(PropertyChangeListener listener)
    {
        propChangeDispatcher.addPropertyChangeListener( listener);
    }*/


    private static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    protected void onModelUpdateEvent() {
        double distance = distance(target.getPositionX(), target.getPositionY(),
                robot.getPositionX(), robot.getPositionY());
        if (distance < 0.5){
            return;
        }
        double velocity = Robot.maxVelocity;
        double angleToTarget = angleTo(robot.getPositionX(), robot.getPositionY(),
                target.getPositionX(), target.getPositionY());
        double angularVelocity = 0;
        if (angleToTarget > robot.getDirection()) {
            angularVelocity = Robot.maxAngularVelocity;
        }
        if (angleToTarget < robot.getDirection()) {
            angularVelocity = -Robot.maxAngularVelocity;
        }
        moveRobot(velocity, angularVelocity, 10);

        //System.out.println(prevDistance + " " + distance);
        /*if (cell.isDead()){
            propChangeDispatcher.firePropertyChange("cell", false, true);
            System.out.println("KILLLL");
        }*/
        if (closeTo(target)){
            System.out.println("EQ");
            if (robot.condition.equals(Condition.TO_MOVE)){
                System.out.println("TO_MOVE");
                System.out.println(cell.getPositionX()+ " " + robot.getPositionX() + " " + cell.getPositionY()+ " " + robot.getPositionY());
                if (closeTo(cell)){
                    System.out.println("CELL");
                    System.out.println("start parasitizing");
                    robot.condition = Condition.TO_PARASIRIZE;
                    robot.toParasitize(cell);
                }
            }
            else {
                System.out.println("TO_PARASITIZE");
                if (cell.isDead()){
                    robot.toStarveAgain();
                    robot.condition = Condition.TO_MOVE;
                }
            }
        }
        else {
            //System.out.println("NO");
            if (robot.condition.equals(Condition.TO_PARASIRIZE)){
                System.out.println("stop parasitizing");

            }
            robot.condition = Condition.TO_MOVE;
        }
        prevDistance = distance;
    }

    private boolean closeTo(Positioned positioned){
        double dis = 1.1;
        if (positioned instanceof Cell){
            dis = 14;
        }
        if (Math.abs(positioned.getPositionX() - robot.getPositionX()) < dis && Math.abs(positioned.getPositionY() - robot.getPositionY()) < dis ){
            return true;
        }
        return false;
    }


    private static double applyLimits(double value, double min, double max)
    {
        if (value < min)
            return min;
        return Math.min(value, max);
    }

    private void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = applyLimits(velocity, 0, Robot.maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -Robot.maxAngularVelocity, Robot.maxAngularVelocity);
        double newX = robot.getPositionX() + velocity / angularVelocity *
                (Math.sin(robot.getDirection() + angularVelocity * duration) -
                        Math.sin(robot.getDirection()));
        if (!Double.isFinite(newX)) {
            newX = robot.getPositionX() + velocity * duration * Math.cos(robot.getDirection());
        }
        double newY = robot.getPositionY() - velocity / angularVelocity *
                (Math.cos(robot.getDirection() + angularVelocity * duration) -
                        Math.cos(robot.getDirection()));
        if (!Double.isFinite(newY)) {
            newY = robot.getPositionY() + velocity * duration * Math.sin(robot.getDirection());
        }
        robot.setPositionX(newX);
        robot.setPositionY(newY);
        double newDirection = asNormalizedRadians(robot.getDirection() + angularVelocity * duration);
        robot.setDirection(newDirection);
    }

    private static double asNormalizedRadians(double angle)
    {
        while (angle < 0)
        {
            angle += 2*Math.PI;
        }
        while (angle >= 2*Math.PI)
        {
            angle -= 2*Math.PI;
        }
        return angle;
    }

}
