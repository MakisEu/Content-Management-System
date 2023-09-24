package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGui extends JPanel implements ActionListener {
JFrame frame;
JLabel icon,username,password,types;
JButton signup;
JPasswordField pwd;
JTextField uname;
JRadioButton user,admin;
JPanel radiobuttons;
public  RegisterGui(JFrame frame1) {
    frame=frame1;
    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(30,30,30,30);
    this.setBackground(new Color(255,255,255));
    this.setFont(new Font("Source Code Pro", Font.BOLD, 14));

    ImageIcon imageIcon = new ImageIcon("assets/signup.png");
    icon=new JLabel(imageIcon);
    Image image = imageIcon.getImage().getScaledInstance(-1, 200, Image. SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(image);
    icon.setIcon(resizedIcon);
    icon.setBorder(null);
    this.add(icon);
    constraints.gridy++;

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
    types=new JLabel("Type of account");
    types.setFont(new Font("Source Code Pro", Font.BOLD, 24));
    this.add(types,constraints);
    constraints.gridy++;



    radiobuttons=new JPanel();
    radiobuttons.setBackground(new Color(255,255,255));
    radiobuttons.setLayout(new FlowLayout());
    radiobuttons.setFont(new Font("Source Code Pro", Font.BOLD, 14));


    ButtonGroup typesGroup=new ButtonGroup();
    user=new JRadioButton("User");
    user.setFont(new Font("Source Code Pro", Font.BOLD, 14));
    user.setBackground(new Color(255,255,255));
    user.setSelected(true);
    user.setActionCommand("UserChecked");
    admin=new JRadioButton("Admin");
    admin.setFont(new Font("Source Code Pro", Font.BOLD, 14));
    admin.setBackground(new Color(255,255,255));
    admin.setActionCommand("AdminChecked");
    typesGroup.add(user);
    typesGroup.add(admin);
    radiobuttons.add(user);
    radiobuttons.add(admin);
    this.add(radiobuttons,constraints);
    constraints.gridy++;
    ImageIcon ii = new ImageIcon("assets/sign_up_button.png");
    signup=new JButton(imageIcon);
    Image img = ii.getImage().getScaledInstance(-1, 60, Image. SCALE_SMOOTH);
    ImageIcon rI = new ImageIcon(img);
    signup.setIcon(rI);
    signup.setBackground(new Color(255,255,255));
    signup.setForeground(new Color(0,0,0));
    signup.setFont(new Font("Source Code Pro", Font.BOLD, 14));
    signup.setText("Sign Up");
    this.add(signup,constraints);

    signup.addActionListener(this);
    signup.setActionCommand("Sign Up");

    this.setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
