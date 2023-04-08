package gui;

import Model.GameModel;
import View.GameView;
import View.MainDrawer;
import ViewModel.ViewModel;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
      GameModel gameModel = new GameModel();
      GameView gameView = new GameView(gameModel.getmodel());
      ViewModel viewModel = new ViewModel(gameModel, gameView);

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