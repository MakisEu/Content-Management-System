package gui;

import api.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Unban extends JPanel implements ActionListener {
    JButton Unban;
    JTextField UserToUnban;
    JLabel UnbanLabel,formNotification;
    JFrame frame;

    Admin admin;
    public Unban(JFrame frame, Admin admin){
        super();
        this.frame=frame;
        this.admin=admin;
        frame.setTitle("Unban a user");
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(400,475));
        frame.setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(30,30,30,30);
        this.setBackground(new Color(255,255,255));

        constraints.insets=new Insets(30,15,15,15);
        constraints.gridy=1;


        formNotification=new JLabel("Unban users");
        formNotification.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        this.add(formNotification,constraints);
        constraints.insets=new Insets(15,15,15,15);
        constraints.gridy++;
        UnbanLabel=new JLabel("Username to Unban");
        UnbanLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        this.add(UnbanLabel,constraints);
        constraints.gridx++;
        constraints.gridy++;
        UserToUnban=new JTextField(12);
        UserToUnban.setBackground(new Color(164,244,199));
        UserToUnban.setForeground(new Color(0,0,0));
        this.add(UserToUnban,constraints);
        constraints.gridx--;
        constraints.gridy++;

        ImageIcon ii = new ImageIcon("assets/check.png");
        Unban=new JButton(ii);
        Image img = ii.getImage().getScaledInstance(-1, 120, Image. SCALE_SMOOTH);
        ImageIcon rI = new ImageIcon(img);
        Unban.setIcon(rI);
        Unban.setBackground(new Color(255,255,255));
        Unban.setForeground(new Color(0,0,0));
        Unban.setBorder(null);
        this.add(Unban,constraints);

        Unban.addActionListener(this);
        Unban.setActionCommand("Unban");

        this.setVisible(true);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Unban")) {
            admin.UnBan(UserToUnban.getText());
        }

    }
}
