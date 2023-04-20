package org.dl.app.View;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.dl.app.ViewModel.ViewModel;
import org.dl.app.gui.LogWindow;
import org.dl.app.localisation.Localisation;
import org.dl.app.log.Logger;

import javax.swing.*;
import java.awt.*;

public class MainDrawer extends JFrame {
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainDrawer(ViewModel viewModel) {
        final int inset = 50;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
        setContentPane(desktopPane);

        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow(viewModel.getGameView());
        gameWindow.setSize(400,  400);
        addWindow(gameWindow);
        ArrayList<AbstractWindow> windows = new ArrayList<>();
        windows.add(logWindow);
        windows.add(gameWindow);

        MenuBarView menuBar = new MenuBarView(this, windows);
        setJMenuBar(menuBar.generateMenu());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addClosingEvent();
    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10, 10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug(Localisation.getString("message.work"));
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void addClosingEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {Localisation.getString("exit.yes"), Localisation.getString("exit.no")};
                int n = JOptionPane.showOptionDialog(e.getWindow(),
                        Localisation.getString("exit.question"),
                        Localisation.getString("exit.title"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
                if (n == 0) {
                    e.getWindow().setVisible(false);
                    System.exit(0);
                }
            }
        });
    }

}
