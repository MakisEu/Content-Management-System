package gui;

import api.BodyPost;
import api.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePostGUI extends JFrame {
    private JButton uploadButton;
    private JTextField Title;
    private JTextField Text;
    private JPanel CreatePostPanel;
    private User user;

    public CreatePostGUI(User user1) {
        user=user1;

    uploadButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title=Title.getText();
            String text=Text.getText();
            if (title.equals("")|| text.trim().equals("")){
                JOptionPane.showMessageDialog(CreatePostPanel, "Please fill all fields", "Not all fields are filled", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String message=user.AddContent("Post",title,new BodyPost(text),null);
                if (message.equals("Added successfully.")){
                    JOptionPane.showMessageDialog(CreatePostPanel, "This Post has been added successfully!", "Content added successfully!", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(CreatePostPanel, message, "Something went wrong!", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    });
}
    public JPanel getPanel(){
        return CreatePostPanel;
    }
}
