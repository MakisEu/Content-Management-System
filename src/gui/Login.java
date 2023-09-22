package gui;

import api.ControlSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {
    static ControlSystem cs=new ControlSystem();
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField dTextField;
    private JPasswordField dsPasswordField;
    private JButton button1;
public Login(JFrame parent) {
    super(parent);
    setTitle("Login");
    setContentPane(panel1);
    setMinimumSize(new Dimension(275,600));
    setModal(true);
    setLocationRelativeTo(parent);
    button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String Username=dTextField.getText();
            String Password=dsPasswordField.getText();
            api.Login login=new api.Login(cs);
            if (Username.equals("") || Password.equals("")){
                JOptionPane.showMessageDialog(panel1, "Please fill all fields", "Not all fields are filled", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String s = login.Log_in(Username, Password);
                if (s.equals("Wrong username or password!")) {
                    JOptionPane.showMessageDialog(panel1, s, "Mismatched Username/Password", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(panel1, s, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    });
    setVisible(true);
}
public static void main(String[] args){
    Login startScreen=new Login(null);
}
}
