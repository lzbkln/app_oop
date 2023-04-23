package View;

import log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBarView {

    private JMenuBar menuBar;

    public MenuBarView(){
        menuBar = new JMenuBar();
    }
    public JMenu createMenu(String nameOfMenu, String description) {
        JMenu menu = new JMenu(nameOfMenu);
        menu.setMnemonic(KeyEvent.VK_V);
        menu.getAccessibleContext().setAccessibleDescription(
                description);
        return menu;
    }

    public JMenuItem createLookAndFeelItem(MainDrawer appFrame){
        JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) -> {
            appFrame.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            appFrame.invalidate();
        });
        return systemLookAndFeel;
    }

    public JMenuItem createCrossplatformLookAndFeelItem(MainDrawer appFrame){
        JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
        crossplatformLookAndFeel.addActionListener((event) -> {
            appFrame.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            appFrame.invalidate();
        });
        return crossplatformLookAndFeel;
    }

    public JMenuItem createTestMenuJMenuItem(MainDrawer appFrame){
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