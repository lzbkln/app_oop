package Model;

import GeneticAlgorithm.Cell;
import GeneticAlgorithm.Condition;
import GeneticAlgorithm.Parasite;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AllModels {

    private boolean isMoved = false;

    private double prevDistance = 0;
    private Parasite robot;
    private Target target;
    private Cell cell;
    private Dimension dimension;

    private PropertyChangeSupport propChangeDispatcher = new PropertyChangeSupport(this);

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

    public void addTextChangeListener(PropertyChangeListener listener)
    {
        propChangeDispatcher.addPropertyChangeListener("model", listener);
    }


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

        System.out.println(prevDistance + " " + distance);
        if (prevDistance == distance){
            System.out.println("EQ");
            System.out.println("EQ");
            System.out.println("EQ");
            if (robot.condition.equals(Condition.TO_MOVE)){
                //isMoved = false;
                if (checkPosition(cell)){
                    System.out.println("start parasitizing");
                    robot.condition = Condition.TO_PARASIRIZE;
                    robot.toParasitize(cell);
                }
            }
        }
        else {
            if (robot.condition.equals(Condition.TO_PARASIRIZE)){
                System.out.println("stop parasitizing");

            }
            robot.condition = Condition.TO_MOVE;
        }
        prevDistance = distance;
    }

    private boolean checkPosition(Cell cell){
        if (Math.abs(cell.getPositionX() - robot.getPositionX()) < 0.5 && Math.abs(cell.getPositionY() - robot.getPositionY()) <0.5 ){
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

    public boolean isMoved() {
        return isMoved;
    }
}
