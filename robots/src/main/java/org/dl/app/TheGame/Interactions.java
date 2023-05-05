package org.dl.app.TheGame;

import org.dl.app.Model.Target;

public class Interactions {

    private Cell cell;

    public Interactions(Cell cell){
        this.cell = cell;
    }

    public void toInteract(Target target, Parasite robot){

        if (closeTo(target, robot)){
            //System.out.println("EQ");
            if (robot.condition.equals(Condition.TO_MOVE)){
                //System.out.println("TO_MOVE");
                //System.out.println(cell.getPositionX()+ " " + robot.getPositionX() + " " + cell.getPositionY()+ " " + robot.getPositionY());
                if (closeTo(cell, robot) && !cell.isDead()){

                    System.out.println("CELL");
                    System.out.println(cell.getPositionX()+ " " + robot.getPositionX() + " " + cell.getPositionY()+ " " + robot.getPositionY());
                    System.out.println("start parasitizing");
                    robot.condition = Condition.TO_PARASIRIZE;
                    robot.toParasitize(cell);
                }
            }
            else {
                System.out.println("TO_PARASITIZE");
                if (cell.isDead()){
                    System.out.println("TO_STARVEAGAIN");
                    robot.toStarveAgain();
                    robot.condition = Condition.TO_MOVE;
                }
            }
        }
        else {
            //System.out.println("NO");
            if (robot.condition.equals(Condition.TO_PARASIRIZE)){
                System.out.println("stop parasitizing");
                robot.toStarveAgain();
                cell.toRecover();

            }
            robot.condition = Condition.TO_MOVE;
        }
        robot.prevDistance = robot.distance;

    }

    private boolean closeTo(Positioned positioned, Parasite robot){
        double dis = 1.1;
        if (positioned instanceof Cell){
            dis = 14;
            if (Math.abs(positioned.getPositionX() - robot.getPositionX()/2) < dis && Math.abs(positioned.getPositionY() - robot.getPositionY()/2) < dis ){
                return true;
            }
        }
        if (Math.abs(positioned.getPositionX() - robot.getPositionX()) < dis && Math.abs(positioned.getPositionY() - robot.getPositionY()) < dis ){
            return true;
        }
        return false;
    }
}