package gui;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import log.Logger;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    public MainApplicationFrame() {
        final int inset = 50;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

        setContentPane(desktopPane);
        
        
        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(400,  400);
        addWindow(gameWindow);

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //в MainDrawer
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

    //в MainDrawer
    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private JMenuBar generateMenuBar(){
        MenuBar menuBar = new MenuBar();

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
