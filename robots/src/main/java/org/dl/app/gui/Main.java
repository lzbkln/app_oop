package org.dl.app.gui;

import org.dl.app.Model.EntityStateProvider;
import org.dl.app.Model.GameModel;
import org.dl.app.View.GameView;
import org.dl.app.View.MainDrawer;
import org.dl.app.ViewModel.ViewModel;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
      GameModel gameModel = new GameModel();
      EntityStateProvider provider = new EntityStateProvider(gameModel.getGameState());
      GameView gameView = new GameView(provider);
      ViewModel viewModel = new ViewModel(gameModel, gameView, provider);

      try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      } catch (Exception e) {
        e.printStackTrace();
      }
      SwingUtilities.invokeLater(() -> {
        MainDrawer frame = new MainDrawer(viewModel);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }
}