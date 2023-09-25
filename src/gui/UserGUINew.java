package gui;

import api.ControlSystem;
import api.User;

import javax.swing.*;
import java.awt.*;

public class UserGUINew extends JFrame {
    User user;
    JTabbedPane tabs;

    public UserGUINew(String username){
        super();
        user=new User(username,new ControlSystem());
        this.setTitle("Content Management System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new Color(255,255,255));
        this.setForeground(new Color(0,0,0));

        tabs=new JTabbedPane();
        tabs.add("Search",new Search(this,user));
        tabs.add("Upload",new UploadContent(this,user));

        this.add(tabs);
        this.setPreferredSize(new Dimension(1000,1000));
        this.setMinimumSize(new Dimension(400,560));
        setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public static void main(String[] args){
        new UserGUINew("me");
    }
}
