package gui;

import javax.swing.*;

public class MenuBar {

    private JMenuBar menuBar;

    public MenuBar(){

        menuBar = new JMenuBar();

    }

    public void addMenu(){
        menuBar.add(createMenu());
    }

    private JMenu createMenu(){
        JMenu menu = new JMenu();
        return menu;
    }

    public JMenuBar getJMenuBar(){return  menuBar;}
}