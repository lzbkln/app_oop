package org.dl.app.Model;

import org.dl.app.TheGame.Cell;
import org.dl.app.TheGame.Interactions;
import org.dl.app.View.CreatorTimer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

public class EntityStateProvider implements GameStateProvider{
    private GameState gameState;

    private Interactions interactions;
    private PropertyChangeSupport propChangeDispatcher = new PropertyChangeSupport(this);

    public EntityStateProvider(GameState gameState){
        this.gameState = gameState;
        this.interactions = new Interactions(this.getCurrentCell());
        this.getCurrentRobot().setProvider(this);
        for (Map.Entry<Integer, Cell> entry : this.getCurrentCell().entrySet()){
            entry.getValue().setProvoder(this);


        }
        //this.getCurrentCell().setProvoder(this);

    }

    public void addTextChangeListener(PropertyChangeListener listener)
    {
        propChangeDispatcher.addPropertyChangeListener(listener);
    }

    public void changeRobotCondition(){
        propChangeDispatcher.firePropertyChange("parasite", false, true);
    }

    public void changeRobotTtl(int ttl){
        propChangeDispatcher.firePropertyChange("ttl", ttl+1, ttl);
    }
    public void changeCellCondition(Cell cell){
        Map<Integer, Cell> map = this.getCurrentCell();
        for (Integer i : map.keySet()){
            if (map.get(i).equals(cell)){
                propChangeDispatcher.firePropertyChange("cell", false, i);
                break;
            }
        }

        //propChangeDispatcher.firePropertyChange("cell", false, );
    }

    @Override
    public Target getCurrentTarget() {
        return gameState.getTarget();
    }

    @Override
    public Robot getCurrentRobot() {
        return gameState.getRobot();
    }

    @Override
    public Map<Integer, Cell> getCurrentCell() {
        return gameState.getCell();
    }

    public CreatorTimer getCurrentCreatorTimer(){
        return gameState.getCreatorTimer();
    }
    public Interactions getInteractions(){return interactions;}
}
