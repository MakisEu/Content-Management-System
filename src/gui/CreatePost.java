package gui;

import api.BodyPost;
import api.ControlSystem;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePost extends JFrame implements ActionListener {

    User user;
    JButton upload;
    JTextField titlePost,textPost;
    JLabel titleLabel,textLabel,formNotification;

    public CreatePost(User user){
        super();
        this.user=user;
        this.setTitle("Create Post");
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(400,475));
        this.setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(30,30,30,30);
        this.setBackground(new Color(255,255,255));

        constraints.insets=new Insets(30,15,15,15);
        constraints.gridy=1;


        formNotification=new JLabel("Create a post.");
        formNotification.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        this.add(formNotification,constraints);
        constraints.insets=new Insets(15,15,15,15);
        constraints.gridy++;
        textLabel=new JLabel("Title of the post");
        textLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        this.add(textLabel,constraints);
        constraints.gridx++;
        constraints.gridy++;
        titlePost=new JTextField(12);
        titlePost.setBackground(new Color(164,244,199));
        titlePost.setForeground(new Color(0,0,0));
        this.add(titlePost,constraints);
        constraints.gridx--;
        constraints.gridy++;
        textLabel=new JLabel("The text of the post");
        textLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        this.add(textLabel,constraints);
        constraints.gridx++;
        constraints.gridy++;

        textPost=new JTextField(12);
        textPost.setBackground(new Color(164,244,199));
        textPost.setForeground(new Color(0,0,0));
        this.add(textPost,constraints);
        constraints.gridy++;
        constraints.gridx--;
        ImageIcon ii = new ImageIcon("assets/upload_button.png");
        upload=new JButton(ii);
        Image img = ii.getImage().getScaledInstance(-1, 120, Image. SCALE_SMOOTH);
        ImageIcon rI = new ImageIcon(img);
        upload.setIcon(rI);
        upload.setBackground(new Color(255,255,255));
        upload.setForeground(new Color(0,0,0));
        upload.setBorder(null);
        this.add(upload,constraints);

        upload.addActionListener(this);
        upload.setActionCommand("Upload");

        this.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Upload")){
            String title=titlePost.getText();
            String text=textPost.getText();
            if (title.equals("") || text.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Not all fields are filled", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String s=user.AddContent("Post",title,new BodyPost(text),null);
                if (s.equals("Added successfully.")){
                    JOptionPane.showMessageDialog(this, "Post has been created successfully.", "Congratulations!.", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, s, "An error has occurred!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
    public static void main(String[] args){
        new CreatePost(new User("mew",new ControlSystem()));
    }
}
