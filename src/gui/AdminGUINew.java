package gui;

import api.Admin;
import api.ControlSystem;

import javax.swing.*;
import java.awt.*;

public class AdminGUINew extends JFrame{
    Admin admin;
    JTabbedPane tabs;

    public AdminGUINew(String username){
        super();
        admin=new Admin(username,new ControlSystem());
        this.setTitle("Content Management System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new Color(255,255,255));
        this.setForeground(new Color(0,0,0));

        tabs=new JTabbedPane();
        tabs.add("Upload",new UploadContent(this,admin));

        //new
        tabs.add("Ban",new Ban(this,admin));
        tabs.add("Unban",new Unban(this,admin));

        this.add(tabs);
        this.setPreferredSize(new Dimension(1000,1000));
        this.setMinimumSize(new Dimension(400,400));


        setLocationRelativeTo(null);
        this.setVisible(true);
    }









    public static void main(String[] args){
        new AdminGUINew("me");
    }

}
