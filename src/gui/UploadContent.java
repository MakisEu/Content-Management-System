package gui;

import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadContent extends JPanel implements ActionListener {
    JFrame frame;
    GridBagConstraints constraints;
    JLabel pseudoTitle;
    JButton Post,Article;

    User user;
    public  UploadContent(JFrame frame, User user){
        this.frame=frame;
        this.user=user;
        this.setLayout(new GridBagLayout());
        constraints=new GridBagConstraints();
        constraints.insets = new Insets(30,30,30,30);
        this.setBackground(new Color(255,255,255));
        this.setForeground(new Color(0,0,0));

        pseudoTitle=new JLabel("Upload Content");
        pseudoTitle.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        pseudoTitle.setBackground(new Color(255,255,255));
        pseudoTitle.setForeground(new Color(0,0,0));
        this.add(pseudoTitle,constraints);
        constraints.gridy++;
        constraints.gridy++;
        constraints.gridy++;
        constraints.insets = new Insets(15,15,15,15);
        Post=new JButton("Post");
        Post.setActionCommand("Post");
        Post.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        Post.setBackground(new Color(255,255,255));
        Post.setForeground(new Color(0,0,0));
        this.add(Post,constraints);
        constraints.gridy++;
        constraints.insets = new Insets(15,15,15,15);
        Article=new JButton("Article");
        Article.setActionCommand("Article");
        Article.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        Article.setBackground(new Color(255,255,255));
        Article.setForeground(new Color(0,0,0));
        this.add(Article,constraints);

        Post.addActionListener(this);
        Article.addActionListener(this);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Post")){
            new CreatePost(user);
            
        } else if (e.getActionCommand().equals("Article")) {
            new  CreateArticle(user);
        }

    }
}
