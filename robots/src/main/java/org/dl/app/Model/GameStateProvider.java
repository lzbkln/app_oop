package org.dl.app.Model;

import org.dl.app.TheGame.Cell;

public interface GameStateProvider {
    Target getCurrentTarget();
    Robot getCurrentRobot();

    Cell getCurrentCell();
}