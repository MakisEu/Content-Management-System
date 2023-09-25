package gui;

import api.BodyArticle;
import api.ControlSystem;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CreateArticle extends JFrame implements ActionListener {

    User user;
    JButton upload;
    JTextField titleArticle,textArticle,authorArticle;
    JLabel titleLabel,textLabel,formNotification,authorLabel;

    public CreateArticle(User user){
        super();
        this.user=user;
        this.setTitle("Create Article");
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(400,575));
        this.setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(30,30,30,30);
        this.setBackground(new Color(255,255,255));

        constraints.insets=new Insets(30,15,15,15);
        constraints.gridy=1;


        formNotification=new JLabel("Create an Article.");
        formNotification.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        this.add(formNotification,constraints);
        constraints.insets=new Insets(15,15,15,15);
        constraints.gridy++;
        titleLabel=new JLabel("Title of the Article");
        titleLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        this.add(titleLabel,constraints);
        constraints.gridx++;
        constraints.gridy++;
        titleArticle=new JTextField(12);
        titleArticle.setBackground(new Color(164,244,199));
        titleArticle.setForeground(new Color(0,0,0));
        this.add(titleArticle,constraints);
        constraints.gridx--;
        constraints.gridy++;
        textLabel=new JLabel("The text of the Article");
        textLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        this.add(textLabel,constraints);
        constraints.gridx++;
        constraints.gridy++;

        textArticle=new JTextField(12);
        textArticle.setBackground(new Color(164,244,199));
        textArticle.setForeground(new Color(0,0,0));
        this.add(textArticle,constraints);
        //constraints.gridx--;
        constraints.gridy++;
        authorLabel=new JLabel("The author of the Article");
        authorLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        this.add(authorLabel,constraints);
        //constraints.gridx++;
        constraints.gridy++;

        authorArticle=new JTextField(12);
        authorArticle.setBackground(new Color(164,244,199));
        authorArticle.setForeground(new Color(0,0,0));
        this.add(authorArticle,constraints);


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
            String title=titleArticle.getText();
            String text=textArticle.getText();
            String author=authorArticle.getText();
            if (title.equals("") || text.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Not all fields are filled", JOptionPane.ERROR_MESSAGE);
            }
            else{
                HashMap<String,String> extras=null;
                if (!author.equals("")){
                    extras=new HashMap<>();
                    extras.put("Author",author);
                }
                String s=user.AddContent("Article",title,new BodyArticle(text),extras);
                if (s.equals("Added successfully.")){
                    JOptionPane.showMessageDialog(this, "Article has been created successfully.", "Congratulations!.", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, s, "An error has occurred!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
    public static void main(String[] args){
        new CreateArticle(new User("mew",new ControlSystem()));
    }
}
