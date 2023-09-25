package gui;

import api.ControlSystem;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Search extends JPanel implements ActionListener {

    JFrame frame;

    JLabel pseudoTitle;

    GridBagConstraints constraints;
    JPanel titlePane,textPane,userPane,radioButtons,authorPane;
    JLabel titleLabel,textLabel,userLabel,authorLabel;
    JTextField titleField,textField,userField,authorField;
    JRadioButton post,article,all;

    JButton search;

    User user;
    public Search(JFrame j,User user){
        frame=j;
        this.user=user;
        this.setLayout(new GridBagLayout());
        constraints=new GridBagConstraints();
        constraints.insets = new Insets(15,15,15,15);
        this.setBackground(new Color(255,255,255));
        this.setForeground(new Color(0,0,0));

        pseudoTitle=new JLabel("Search Content");
        pseudoTitle.setFont(new Font("Source Code Pro", Font.BOLD, 32));
        pseudoTitle.setBackground(new Color(255,255,255));
        pseudoTitle.setForeground(new Color(255, 0, 139, 255));
        this.add(pseudoTitle,constraints);
        constraints.gridy++;
        constraints.gridy++;

        titlePane=new JPanel(new FlowLayout());
        titlePane.setBackground(new Color(255,255,255));
        titlePane.setForeground(new Color(0, 0, 0));

        titleLabel=new JLabel("Title     ");
        titleLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        titleLabel.setBackground(new Color(255,255,255));
        titleLabel.setForeground(new Color(0, 0, 0));

        titleField=new JTextField(12);
        titleField.setBackground(new Color(52, 255, 137));
        titleField.setForeground(new Color(0, 0, 0));
        titlePane.add(titleLabel);
        titlePane.add(titleField);

        this.add(titlePane,constraints);
        constraints.gridy++;

        textPane=new JPanel(new FlowLayout());
        textPane.setBackground(new Color(255,255,255));
        textPane.setForeground(new Color(0, 0, 0));

        textLabel=new JLabel("Text      ");
        textLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        textLabel.setBackground(new Color(255,255,255));
        textLabel.setForeground(new Color(0, 0, 0));

        textField=new JTextField(12);
        textField.setBackground(new Color(52, 255, 137));
        textField.setForeground(new Color(0, 0, 0));
        textPane.add(textLabel);
        textPane.add(textField);

        this.add(textPane,constraints);
        constraints.gridy++;

        userPane=new JPanel(new FlowLayout());
        userPane.setBackground(new Color(255,255,255));
        userPane.setForeground(new Color(0, 0, 0));

        userLabel=new JLabel("User      ");
        userLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        userLabel.setBackground(new Color(255,255,255));
        userLabel.setForeground(new Color(0, 0, 0));

        userField=new JTextField(12);
        userField.setBackground(new Color(52, 255, 137));
        userField.setForeground(new Color(0, 0, 0));
        userPane.add(userLabel);
        userPane.add(userField);

        this.add(userPane,constraints);
        constraints.gridy++;

        radioButtons=new JPanel(new FlowLayout());
        radioButtons.setBackground(new Color(255,255,255));
        radioButtons.setForeground(new Color(0, 0, 0));


        all=new JRadioButton("All");
        all.setActionCommand("User");
        all.setBackground(new Color(255,255,255));
        all.setForeground(new Color(0, 0, 0));
        all.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        all.setActionCommand("all");
        all.setSelected(true);

        post=new JRadioButton("Post");
        post.setActionCommand("User");
        post.setBackground(new Color(255,255,255));
        post.setForeground(new Color(0, 0, 0));
        post.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        post.setActionCommand("post");

        article=new JRadioButton("Article");
        article.setActionCommand("User");
        article.setBackground(new Color(255,255,255));
        article.setForeground(new Color(0, 0, 0));
        article.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        post.setActionCommand("article");

        ButtonGroup bg=new ButtonGroup();
        bg.add(all);
        bg.add(post);
        bg.add(article);

        radioButtons.add(all);
        radioButtons.add(post);
        radioButtons.add(article);


        this.add(radioButtons,constraints);
        constraints.gridy++;

        authorPane=new JPanel(new FlowLayout());
        authorPane.setBackground(new Color(255,255,255));
        authorPane.setForeground(new Color(0, 0, 0));

        authorLabel=new JLabel("Author    ");
        authorLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        authorLabel.setBackground(new Color(255,255,255));
        authorLabel.setForeground(new Color(0, 0, 0));

        authorField=new JTextField(12);
        authorField.setBackground(new Color(52, 255, 137));
        authorField.setForeground(new Color(0, 0, 0));
        authorPane.add(authorLabel);
        authorPane.add(authorField);

        this.add(authorPane,constraints);
        constraints.gridy++;
        authorPane.setVisible(false);
        constraints.gridy++;


        ImageIcon ii = new ImageIcon("assets/search.png");
        search=new JButton(ii);
        Image img = ii.getImage().getScaledInstance(-1, 60, Image. SCALE_SMOOTH);
        ImageIcon rI = new ImageIcon(img);
        search.setIcon(rI);
        search.setBackground(new Color(255,255,255));
        search.setForeground(new Color(0,0,0));
        search.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        search.setText("Search");
        search.setActionCommand("Search");
        this.add(search,constraints);



        all.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    authorPane.setVisible(false);
                    // Your selected code here.
                }
            }
        });
        post.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    authorPane.setVisible(false);
                    // Your selected code here.
                }
            }
        });
        article.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    authorPane.setVisible(true);
                    // Your selected code here.
                }
            }
        });


        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args){
        new Search(null,new User("me",new ControlSystem()));
    }
}
