package gui;

import api.ControlSystem;
import api.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class UserGUI extends JFrame{
    private JFrame frame;
    private User user;
    private JPanel window;
    private JTabbedPane UserPane;
    private JList list1;

    public UserGUI(String username,JFrame frame){
        super();
        this.frame=frame;
        this.frame.dispose();
        this.setTitle("Content Management System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(275,600));
        setLocationRelativeTo(null);
        UserPane.add("Log in",new LoginGUI(this).getJpanel());
        UserPane.add("Sign up",new Register().getJpanel());
        this.add(UserPane);
        this.setVisible(true);
        this.user=new User(username,new ControlSystem());


        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list1.getSelectedValue().equals("Post")){

                    new  CreatePostGUI(user);
                }

            }
        });
    }
    public  static void main(String[] args){
        new UserGUI("makis",new JFrame());
    }
}
