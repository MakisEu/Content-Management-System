package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class User {
    String userID;
    boolean isAdmin=false;
    ArrayList<String> liked;
    ArrayList<Vector<String>> myComments;
    ControlSystem system;
    public User(String id,ControlSystem system){
        userID=id;
        this.system=system;
        liked=new ArrayList<>();
        myComments=new ArrayList<>();
    }
    public String AddContent(String type, String Title,Body body,HashMap<String,String> extras){
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
                BodyArticle ba=((BodyArticle) body);
                String author=null;
                if (extras!=null && extras.get("Author")!=null){
                    author=extras.get("Author");
                }
                if (ba.getText().length()<Post.charLimitPost) {
                    Article p = new Article(author,Title, ba.getText(), userID);
                    b=p;
                }
                else {
                    return ("Max Limit of the Post's Text Exceeded by"+String.valueOf(ba.getText().length()-Post.charLimitPost)+"Characters.");
                }

            }
        }
        if (!(b==null)){
            if (system.AddContent(b)){
                return "Added successfully.";
            }
            return "This content already exists.";
        }
        else{
            return "Incorrect type. Addition failed.";
        }
    }
    public String AddComment(String text,String contentID){
        Comment comment=new Comment(text,userID);
        String s=system.AddComment(comment,contentID);
        if (s.contains("#")){
            Vector v=new Vector<>();
            v.add(contentID);
            v.add(s);
            myComments.add(v);
            return "Added successfully.";
        }
        else{
            return s;
        }
    }
    public String EditComment(String commentID,String text) {
        Comment comment=this.getComment(commentID);
        if (comment==null){
            return "This comment does not exist";
        }
        if (comment.getUser().equals(userID) || isAdmin) {
            comment.Edit(text);
            return ("Comment has been edited.");
        }
        return ("You do not have sufficient access right to edit this comment.");
    }
    public String DeleteComment(String commentID,String contentID){
        Comment comment=getComment(commentID);
        if (comment==null){
            return "This comment does not exist";
        }
        if (comment.getUser().equals(userID) || isAdmin) {
            system.DeleteComment(commentID,contentID);
            return ("Comment has been deleted.");
        }
        return ("You do not have sufficient access right to delete this comment.");
    }
    public String DeleteContent(String contentID){
        Content content=getContent(contentID);
        if (content!=null && content.getUser().equals(userID) || isAdmin) {
            system.DeleteContent(content);
            return ("Content has been deleted.");
        }
        return ("You do not have sufficient access right to delete this content or it doesn't exist.");
    }
    public String EditContent(String contentId,String title,Body body,HashMap<String,String> extras){
        Content content=getContent(contentId);
        if (content.getUser().equals(userID)||isAdmin){
            if(title!=null){
                content.setTitle(title);
            }
            String type= content.getID().split("#")[0];
            if (extras.isEmpty()){return ("Content has been edited.");}
            switch (type){
                case ("Post"):{
                    if (body!=null) {
                        ((BodyPost)content.getBody()).setText(((BodyPost)body).getText());
                    }
                    return ("Content has been edited.");
                }
                case ("Article"):{
                    if (body!=null) {
                        ((BodyArticle)content.getBody()).setText(((BodyArticle)body).getText());
                    }
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
    public Comment getComment(String commentID){
        Comment c=null;
        for (Vector<String> v:myComments){
            if (v.get(1)==commentID) {
                if (ControlSystem.comments.get(v.get(0))!=null) {
                    ArrayList<Comment> coms = ControlSystem.comments.get(v.get(0)).get(v.get(1).split("#")[0]);
                    if (coms != null) {
                        for (Comment com : coms) {
                            if (com.getId() == commentID) {
                                return com;
                            }
                        }
                        return c;
                    }
                }
                else {
                    myComments.remove(v);
                }
            }
        }
        return c;
    }
    public Content getContent(String contentID){
        return ControlSystem.content.get(userID).get(contentID);
    }
}
