package org.dl.app.TheGame;

import org.dl.app.Model.Target;

import java.util.Map;

public class Interactions {

    private Map<Integer, Cell> cellList;

    private Map<Integer, VarietyTargets> targetsList;

    public Interactions(Map<Integer, Cell> cellList, Map<Integer, VarietyTargets> targetsList){
        this.cellList = cellList;
        this.targetsList = targetsList;
    }

    public void toInteractWithCells(Target target, Parasite robot){

        Cell current = new Cell();
        if (closeTo(target, robot) && !robot.isDead){
            if (robot.condition.equals(Condition.TO_MOVE)){
                for (Map.Entry<Integer,Cell> entry : cellList.entrySet()){
                    Cell cell = entry.getValue();
                    if (closeTo(cell, robot) && !cell.isDead()){
                        robot.condition = Condition.TO_PARASIRIZE;
                        robot.toParasitize(cell);
                        current = cell;
                    }
                }
            }
            else {
                if (current.isDead()){
                    robot.toStarveAgain();
                    robot.condition = Condition.TO_MOVE;
                }
            }
        }
        else {
            if (robot.condition.equals(Condition.TO_PARASIRIZE) && !robot.isDead){
                robot.toStarveAgain();
                current.toRecover();

            }
            robot.condition = Condition.TO_MOVE;
        }
        robot.prevDistance = robot.distance;

    }

    public void toInteractWithVarietys(Target target, Parasite robot){
        if (closeTo(target, robot) && !robot.isDead){
            if (robot.condition.equals(Condition.TO_MOVE)){
                for (Map.Entry<Integer,VarietyTargets> entry : targetsList.entrySet()){
                    VarietyTargets varietyTarget = entry.getValue();
                    if (closeTo(varietyTarget, robot)){
                        varietyTarget.toChangeItsState();
                    }
                }

            }
        }

    }

    private boolean closeTo(Positioned positioned, Parasite robot){
        double dis = 1.1;
        if (positioned instanceof Cell || positioned instanceof  VarietyTargets){
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
