package api;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String userID;
    boolean isAdmin=false;
    ArrayList<String> liked;
    ControlSystem system;
    public User(String id,ControlSystem system){
        userID=id;
        this.system=system;
        liked=new ArrayList<>();
    }
    public String AddContent(String type, String Title,Body body){
        Content b=null;
        switch (type){
            case ("Post"):{
                //Create Post
                BodyPost bp=((BodyPost)body);
                if (bp.getText().length()<Post.charLimitPost) {
                    Post p = new Post(Title, bp.getText(), userID);
                    b=p;
                }
                else {
                    return ("Max Limit of the Post's Text Exceeded by"+String.valueOf(bp.getText().length()-Post.charLimitPost)+"Characters.");
                }
            }
            case ("Article"):{
                //Create Article
                //TODO
            }
        }
        if (!b.equals(null)){
            if (system.AddContent(b)){
                return "Added successfully,";
            }
            return "You already have content with the same title.";
        }
        else{
            return "Incorrect type. Addition failed.";
        }
    }
    public String AddComment(String text,Content content){
        Comment comment=new Comment(text,userID);
        if (system.AddComment(comment,content.getID())){
            return "Added successfully.";
        }
        else{
            return "This comment already exist.";
        }
    }
    public String EditComment(Comment comment,String text) {
        if (comment.getUser().equals(userID) || isAdmin) {
            comment.Edit(text);
            return ("Comment has been edited.");
        }
        return ("You do not have sufficient access right to edit this comment.");
    }
    public String DeleteComment(Comment comment){
        if (comment.getUser().equals(userID) || isAdmin) {
            system.DeleteComment(comment);
            return ("Comment has been deleted.");
        }
        return ("You do not have sufficient access right to delete this comment.");
    }
    public String DeleteContent(Content content){
        if (content.getUser().equals(userID) || isAdmin) {
            system.DeleteContent(content);
            return ("Content has been deleted.");
        }
        return ("You do not have sufficient access right to delete this content.");
    }
    public String EditContent(Content content,String title,Body body,HashMap<String,String> extras){
        if (content.getUser().equals(userID)||isAdmin){
            if(title!=null){
                content.setTitle(title);
            }
            String type= content.getID().split("#")[0];
            if (extras.isEmpty()){return ("Content has been edited.");}
            switch (type){
                case ("Post"):{
                    return ("Content has been edited.");
                }
                case ("Article"):{
                    for (String s: extras.keySet()) {
                        if (s.equals("Author")){
                            ((Article) content).setAuthor(extras.get(s));
                        }
                    }
                    return ("Content has been edited.");
                }
            }
        }
        return ("You do not have sufficient access right to edit this content.");
    }

    /**
     * @param content The content that will be liked
     */
    public void LikeContent(Content content){
    }

    /**
     * @param content The content that will be disliked
     */
    public void DislikeContent(Content content){
    }
}
