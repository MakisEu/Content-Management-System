package gui;

import api.ControlSystem;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUINew extends JPanel implements ActionListener {
    JFrame frame;
    JLabel icon,username,password;
    JButton loginButton;
    JTextField uname;
    JPasswordField pwd;
    public LoginGUINew(JFrame frame) {
        this.frame=frame;
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(30,30,30,30);
        this.setBackground(new Color(255,255,255));

        ImageIcon imageIcon = new ImageIcon("assets/login.png");
        icon=new JLabel(imageIcon);
        Image image = imageIcon.getImage().getScaledInstance(-1, 180, Image. SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        icon.setIcon(resizedIcon);
        icon.setBorder(null);
        this.add(icon);
        //constraints.gridx=1;
        constraints.insets=new Insets(30,15,15,15);
        constraints.gridy=1;
        username=new JLabel("Username");
        username.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        this.add(username,constraints);
        constraints.insets=new Insets(15,15,15,15);
        constraints.gridy=2;
        uname=new JTextField(12);
        uname.setBackground(new Color(164,244,199));
        uname.setForeground(new Color(0,0,0));
        this.add(uname,constraints);
        constraints.gridy++;
        password=new JLabel("Password");
        password.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        this.add(password,constraints);
        constraints.gridy++;
        pwd=new JPasswordField(12);
        pwd.setBackground(new Color(255,72,79));
        pwd.setForeground(new Color(0,0,0));
        this.add(pwd,constraints);
        constraints.gridy++;
        ImageIcon ii = new ImageIcon("assets/login_button.png");
        loginButton=new JButton(imageIcon);
        Image img = ii.getImage().getScaledInstance(-1, 120, Image. SCALE_SMOOTH);
        ImageIcon rI = new ImageIcon(img);
        loginButton.setIcon(rI);
        loginButton.setBackground(new Color(255,255,255));
        loginButton.setForeground(new Color(0,0,0));
        loginButton.setBorder(null);
        this.add(loginButton,constraints);

        loginButton.addActionListener(this);
        loginButton.setActionCommand("Login");

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            String Username = uname.getText();
            String Password = pwd.getText();
            api.Login login = new api.Login(new ControlSystem());
            if (Username.equals("") || Password.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Not all fields are filled", JOptionPane.ERROR_MESSAGE);
            } else {
                String s = login.Log_in(Username, Password);
                if (s.equals("Wrong username or password!")) {
                    JOptionPane.showMessageDialog(this, s, "Mismatched Username/Password", JOptionPane.ERROR_MESSAGE);
                } else {
                    String type = s.split("#")[1];
                    s = s.split("#")[0];
                    JOptionPane.showMessageDialog(this, s, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    if (type.equals("User")) {
                        new UserGUI(Username, null);
                        frame.dispose();
                    }
                }
            }

        }
    }
}
