package org.dl.app.Model;
import org.dl.app.View.CreatorTimer;

import java.awt.*;

public class GameModel {

    GameState gameState;

    public GameModel(){
        gameState = new GameState();
    }

    public void update(){
        gameState.update();
    }

    public void change(Point p){
        gameState.changeTarget(p);
    }

    public GameState getGameState(){return gameState;}

    public Robot getRobot(){return gameState.getRobot();}
    public Target getTarget(){return gameState.getTarget();}

    public CreatorTimer getCreatorTimer(){
        return gameState.getCreatorTimer();
    }
}
