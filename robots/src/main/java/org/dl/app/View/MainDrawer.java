package org.dl.app.View;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import org.dl.app.ViewModel.ViewModel;
import org.dl.app.gui.LogWindow;;
import org.dl.app.log.Logger;

import javax.swing.*;
import java.awt.*;

public class MainDrawer extends JFrame {
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final LogWindow logWindow;

    public MainDrawer(ViewModel viewModel) {
        final int inset = 50;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

        setContentPane(desktopPane);

        logWindow = createLogWindow();
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

        JMenu local = new JMenu("Язык");
        local.setMnemonic(KeyEvent.VK_W);
        local.getAccessibleContext().setAccessibleDescription("Смена языка на доступные");

        JMenu lookAndFeelMenu = menuBar.createMenu(
                "Режим отображения",
                "Управление режимом отображения приложения");

        lookAndFeelMenu.add(menuBar.createLookAndFeelItem(this));
        lookAndFeelMenu.add(menuBar.createCrossplatformLookAndFeelItem(this));

        JMenu testMenu = menuBar.createMenu(
                "Тесты",
                "Тестовые команды");
        addLanguage(local,lookAndFeelMenu,testMenu);
        menuBar.addJMenu(local);
        testMenu.add(menuBar.createTestMenuJMenuItem(this));

        menuBar.addJMenu(lookAndFeelMenu);
        menuBar.addJMenu(testMenu);


        //menuBar.add("Режим отображения", "Управление режимом отображения приложения"););
        return menuBar.getJMenuBar();
    }

    private void addLanguage(JMenu local,JMenu look,JMenu testMenu){
        JMenuItem setLang = new JMenuItem("Английский язык", KeyEvent.VK_N);
        JMenuItem setLangRu = new JMenuItem("Русский язык", KeyEvent.VK_N);
        setLang.addActionListener((event) -> {Locale curr = new Locale("UK");
            changeToLan(look,curr,testMenu,local);this.invalidate();});
        local.add(setLang);
        setLangRu.addActionListener((event) -> {Locale curr = new Locale("RU");
            changeToLan(look,curr,testMenu,local);this.invalidate();});
        local.add(setLangRu);
    }

    private void changeToLan(JMenu lookAndFeelMenu ,Locale curr,JMenu testMenu,JMenu local){
        ResourceBundle rb = ResourceBundle.getBundle("lang",curr);
        logWindow.getMLogContent().setText(rb.getString("work"));
        logWindow.setTitle(rb.getString("protocol"));
        //gameWindow.setTitle(rb.getString("window"));
        local.setText(rb.getString("language"));
        local.getItem(0).setText(rb.getString("english"));
        local.getItem(1).setText(rb.getString("russian"));
        lookAndFeelMenu.setText(rb.getString("display"));
        lookAndFeelMenu.getItem(0).setText(rb.getString("system"));
        lookAndFeelMenu.getItem(1).setText(rb.getString("universal"));
        testMenu.setText(rb.getString("test"));
        testMenu.getItem(0).setText(rb.getString("message"));
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
