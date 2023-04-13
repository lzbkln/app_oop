package org.dl.app.View;

import main.java.ViewModel.ViewModel;
import org.dl.app.gui.LogWindow;;
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

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private JMenuBar generateMenuBar(){
        MenuBarView menuBar = new MenuBarView();

        JMenu lookAndFeelMenu = menuBar.createMenu(
                "Режим отображения",
                "Управление режимом отображения приложения");

        lookAndFeelMenu.add(menuBar.createLookAndFeelItem(this));
        lookAndFeelMenu.add(menuBar.createCrossplatformLookAndFeelItem(this));

        JMenu testMenu = menuBar.createMenu(
                "Тесты",
                "Тестовые команды");

        testMenu.add(menuBar.createTestMenuJMenuItem(this));

        menuBar.addJMenu(lookAndFeelMenu);
        menuBar.addJMenu(testMenu);


        //menuBar.add("Режим отображения", "Управление режимом отображения приложения"););
        return menuBar.getJMenuBar();
    }
    public void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }
}
