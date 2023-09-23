package gui;

import api.ControlSystem;
import api.User;

import javax.swing.*;

public class UserGUI {
    private JFrame frame;
    private User user;
    public UserGUI(String username){
        this.user=new User(username,new ControlSystem());

    }
}
