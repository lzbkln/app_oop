package org.dl.app.Model;
import java.awt.*;

public class GameModel {

    Math models;

    public GameModel(){
        this.models = new Math();
    }

    public void update(){
        models.onModelUpdateEvent();
    }
    //public void getEntity();
    public void change(Point p){
        models.setTargetPosition(p);
    }

    public Math getmodel(){return models;}
}
