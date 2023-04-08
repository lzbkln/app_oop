package View;

import javax.swing.*;
import java.awt.*;

//рисует маленькое вложенное игровое поле
public class GameWindow extends JInternalFrame {
    public GameWindow(GameView view)
    {
        super("Игровое поле", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(view, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}