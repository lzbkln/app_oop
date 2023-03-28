package gui;

import log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static javax.swing.UIManager.setLookAndFeel;

public class MenuBar {

    private JMenuBar menuBar;

    public MenuBar(){
        menuBar = new JMenuBar();
    }
    public JMenu createMenu(String nameOfMenu, String description) {
        JMenu menu = new JMenu(nameOfMenu);
        menu.setMnemonic(KeyEvent.VK_V);
        menu.getAccessibleContext().setAccessibleDescription(
                description);
        return menu;
    }

    public JMenuItem createLookAndFeelItem(MainApplicationFrame appFrame){
        JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) -> {
            appFrame.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            appFrame.invalidate();
        });
        return systemLookAndFeel;
    }

    public JMenuItem createCrossplatformLookAndFeelItem(MainApplicationFrame appFrame){
        JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
        crossplatformLookAndFeel.addActionListener((event) -> {
            appFrame.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            appFrame.invalidate();
        });
        return crossplatformLookAndFeel;
    }

    public JMenuItem createTestMenuJMenuItem(MainApplicationFrame appFrame){
        JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> {
            Logger.debug("Новая строка");
        });
        return addLogMessageItem;
    }

    public void addJMenu(JMenu menu){
        this.menuBar.add(menu);
    }
    public JMenuBar getJMenuBar(){return  menuBar;}
}