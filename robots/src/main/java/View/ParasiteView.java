package View;

import GeneticAlgorithm.Parasite;
import Model.Robot;

import java.awt.*;

public class ParasiteView extends RobotView{
    @Override
    protected void drawRobot(Graphics2D g, Robot robot) {
        if (robot instanceof Parasite){
            if (((Parasite) robot).isAlive()){
                super.drawRobot(g, robot);
            }
        }
    }

    /*protected void drawParasite(Graphics2D g, Parasite parasite) {
        if (parasite.isAlive()){
            this.drawRobot(g, parasite);
        }

    }*/

    /*@Override
    protected void drawRobot(Graphics2D g, Robot robot) {
        //super.drawRobot(g, robot);
        if (robot instanceof Parasite){
            if (((Parasite) robot).isAlive()){
                this.drawRobot(g, robot);
            }
        }

    }*/
}
