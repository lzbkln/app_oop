package org.dl.app.Model;

import org.dl.app.TheGame.Cell;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EntityStateProvider implements GameStateProvider{
    private GameState gameState;

    private PropertyChangeSupport propChangeDispatcher = new PropertyChangeSupport(this);

    public EntityStateProvider(GameState gameState){
        this.gameState = gameState;
        this.getCurrentRobot().setProvider(this);
        this.getCurrentCell().setProvoder(this);
    }

    public void addTextChangeListener(PropertyChangeListener listener)
    {
        propChangeDispatcher.addPropertyChangeListener(listener);
    }

    public void changeRobotCondition(){
        propChangeDispatcher.firePropertyChange("parasite", false, true);
    }

    public void changeCellCondition(){
        propChangeDispatcher.firePropertyChange("cell", false, true);
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
    public Cell getCurrentCell() {
        return gameState.getCell();
    }
}
