package gui;

import api.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


    public class Ban extends JPanel implements ActionListener {
    JButton Ban;
    JTextField UserToBan;
    JLabel BanLabel,formNotification;
    JFrame frame;

    Admin admin;
    public Ban(JFrame frame, Admin admin){
        super();
        this.frame=frame;
        this.admin=admin;
        frame.setTitle("Create Post");
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(400,475));
        frame.setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(30,30,30,30);
        this.setBackground(new Color(255,255,255));

        constraints.insets=new Insets(30,15,15,15);
        constraints.gridy=1;


        formNotification=new JLabel("Ban users");
        formNotification.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        this.add(formNotification,constraints);
        constraints.insets=new Insets(15,15,15,15);
        constraints.gridy++;
        BanLabel=new JLabel("Username to ban");
        BanLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        this.add(BanLabel,constraints);
        constraints.gridx++;
        constraints.gridy++;
        UserToBan=new JTextField(12);
        UserToBan.setBackground(new Color(164,244,199));
        UserToBan.setForeground(new Color(0,0,0));
        this.add(UserToBan,constraints);
        constraints.gridx--;
        constraints.gridy++;

        ImageIcon ii = new ImageIcon("assets/slash.png");
        Ban=new JButton(ii);
        Image img = ii.getImage().getScaledInstance(-1, 120, Image. SCALE_SMOOTH);
        ImageIcon rI = new ImageIcon(img);
        Ban.setIcon(rI);
        Ban.setBackground(new Color(255,255,255));
        Ban.setForeground(new Color(0,0,0));
        Ban.setBorder(null);
        this.add(Ban,constraints);

        Ban.addActionListener(this);
        Ban.setActionCommand("Ban");

        this.setVisible(true);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ban")) {
            admin.Ban(UserToBan.getText());
        }

    }
}
