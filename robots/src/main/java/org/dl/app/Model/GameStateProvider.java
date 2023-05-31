package org.dl.app.Model;

import org.dl.app.TheGame.Cell;
import org.dl.app.TheGame.VarietyTargets;

import java.util.Map;

public interface GameStateProvider {
    Target getCurrentTarget();
    Robot getCurrentRobot();

    Map<Integer, Cell> getCurrentCell();
    Map<Integer, VarietyTargets> getCurrentTargets();
}