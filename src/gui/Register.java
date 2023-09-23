package gui;

import api.ControlSystem;
import api.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JPanel{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JRadioButton userRadioButton;
    private JButton SIGNUPButton;
    private JRadioButton adminRadioButton;
    private JPanel jPanel1;

    public Register() {
    SIGNUPButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String Username=textField1.getText();
            String Password=passwordField1.getText();
            Login login=new Login(new ControlSystem());
            if (Username.equals("") || Password.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill all fields", "Not all fields are filled", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String type=null;
                if (userRadioButton.isEnabled()) {
                    type="User";
                }
                else {
                    type="Admin";
                }
                String s = login.Sign_up(Username, Password,type);
                s = s.split("#")[0];
                if (s.equals("This username is already taken")) {
                    JOptionPane.showMessageDialog(null, "This username is already exists!", "Change Username!", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Account has been created successfully!", "Registration successful!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    });
    this.setVisible(true);
}
public JPanel getJpanel(){
    return jPanel1;
}
public static void main(String[] args){
    new Register();
}
}
