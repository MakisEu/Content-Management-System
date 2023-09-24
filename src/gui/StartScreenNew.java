package gui;

import api.ControlSystem;
import api.Login;

import javax.swing.*;
import java.awt.*;

public class StartScreenNew extends JFrame {
    JTabbedPane tabs;
    Login login;

    public StartScreenNew(){
        super();
        login=new Login(new ControlSystem());
        this.setTitle("Content Management System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,800));
        setLocationRelativeTo(null);
        tabs=new JTabbedPane();
        tabs.setBackground(new Color(164,244,199));
        tabs.add("Log in",new LoginGUINew(this,login));
        tabs.add("Sign up",new RegisterGui(this,login));
        //tabs.add("Accommodation Manager",new AccommodationManagerProviderGui().startGUI(service));
        this.add(tabs);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new StartScreenNew();
    }
}
