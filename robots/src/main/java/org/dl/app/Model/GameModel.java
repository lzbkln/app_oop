package org.dl.app.Model;


import java.awt.*;

public class GameModel {

    AllModels models;

    public GameModel(){
        this.models = new AllModels();
    }

    public void update(){
        models.onModelUpdateEvent();
    }
    //public void getEntity();
    public void change(Point p){
        models.setTargetPosition(p);
    }

    public AllModels getmodel(){return models;}
}
