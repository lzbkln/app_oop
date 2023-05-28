package org.dl.app.View;

import org.dl.app.Model.EntityStateProvider;
import org.dl.app.Model.GameModel;
import org.dl.app.ViewModel.ViewModel;

import javax.swing.*;
import java.awt.*;

//рисует маленькое вложенное игровое поле
public class GameWindow extends AbstractWindow {
    private GameView view;
    public GameWindow(GameView view) {
        super("window.game", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        this.view = view;
        panel.add(view, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public void restart() {
        getContentPane().removeAll();
        GameModel gameModel = new GameModel();
        EntityStateProvider provider = new EntityStateProvider(gameModel.getGameState());
        GameView newView = new GameView(provider);
        ViewModel viewModel = new ViewModel(gameModel, newView, provider);
        this.view = newView;
        view.setPreferredSize(new Dimension(400,400));
        getContentPane().add(view);
        pack();
    }

}