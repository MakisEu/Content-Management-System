package gui;

import javax.swing.*;
import java.awt.*;


public class StartScreen extends JFrame{
    private JTabbedPane tabbedPane1;

    public  StartScreen(){
        super();
        this.setTitle("Content Management System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(275,600));
        setLocationRelativeTo(null);
        tabbedPane1.add("Log in",new LoginGUI(this).getJpanel());
        this.add(tabbedPane1);
        this.setVisible(true);
    }
    public static void main(String[] args){
        StartScreen startScreen=new StartScreen();
    }
}
