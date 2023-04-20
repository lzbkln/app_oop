package org.dl.app.View;

import javax.swing.*;
import java.awt.*;

//рисует маленькое вложенное игровое поле
public class GameWindow extends AbstractWindow {
    public GameWindow(GameView view)
    {
        super("window.game", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(view, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}