package org.dl.app.Model;
import java.awt.*;

public class GameModel {

    Math models;

    GameState gameState;

    public GameModel(){
        this.models = new Math();
        gameState = new GameState();
    }

    public void update(){
        gameState.update();
    }
    //public void getEntity();
    public void change(Point p){
        gameState.changeTarget(p);
    }

    public Math getmodel(){return models;}

    public GameState getGameState(){return gameState;}

    public Robot getRobot(){return gameState.getRobot();}
    public Target getTarget(){return gameState.getTarget();}
}
