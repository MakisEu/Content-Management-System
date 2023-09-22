package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * The class for a normal user
 */
public class User {
    String userID;
    boolean isAdmin=false;
    //contend id, liked/disliked
    HashMap<String,Integer> liked;
    ArrayList<Vector<String>> myComments;
    ControlSystem system;

    /**
     * Base constructor of User
     * @param id the unique ID/username of the user
     * @param system the control system
     */
    public User(String id,ControlSystem system){
        userID=id;
        this.system=system;
        liked=new HashMap<>();
        myComments=new ArrayList<>();
    }

    /**
     * Creates and adds a content for the user
     * @param type      the type of the content
     * @param Title     the title of the content
     * @param body      the body of the content
     * @param extras    the extra fields of the content
     * @return          Control string tha describes what happened
     */
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

    /**
     * Adds a comment to a content
     * @param text      The text of the comment
     * @param contentID The content ID of the content it will be added to
     * @return          The control string of ControlSystem.AddComment() or that the operation was completed successfully
     */
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

    /**
     * Edits a comment of the user
     * @param commentID The id of the comment that wll be edited
     * @param text      The text that will replace the original
     * @return          Control string of what happened
     */
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

    /**
     * Deletes a comment of the user
     * @param commentID the id of the comment that will be deleted
     * @param contentID the id of the content the comment belongs to
     * @return          Control string of what happened
     */
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

    /**
     * Deletes a content of the user
     * @param contentID the id of the content that will be deleted
     * @return          Control string of what happened
     */
    public String DeleteContent(String contentID){
        Content content=getContent(contentID);
        if (content!=null && content.getUser().equals(userID) || isAdmin) {
            system.DeleteContent(content);
            return ("Content has been deleted.");
        }
        return ("You do not have sufficient access right to delete this content or it doesn't exist.");
    }

    /**
     * Edit's a content of the user
     * @param contentId The id of the content that will be edited
     * @param title     The new Title of the content.  If it is null, it will not get changed
     * @param body      The new body of the content.   If it is null, it will not get changed
     * @param extras    The new extra fields of the content. If an extra field is not in extras, it will not be changed
     * @return          Control string of what happened
     */
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
     * Likes a comment
     * @param contentId The id of the content that will be liked/disliked
     */
    public boolean LikeContent(String contentId,boolean isLiked){
        Integer contain=liked.get(contentId);
        if (ControlSystem.content.get(contentId.split("#")[1]) !=null){
            Content content=ControlSystem.content.get(contentId.split("#")[1]).get(contentId);
            if (content!=null){
                if (contain==null) {
                    if (isLiked){
                        liked.put(contentId,1);
                        content.Like();
                    }
                    else{
                        liked.put(contentId,-1);
                        content.Dislike();
                    }
                }
                else if (contain==1 && !isLiked){
                    content.Dislike();
                    liked.remove(contentId);

                }
                else if (contain==-1 && isLiked) {
                    content.Like();
                    liked.remove(contentId);
                }
                else{
                    return false;
                }
                return true;
            }
            else{
                if (contain!=null){
                    liked.remove(contentId);
                }
            }
        }
        else{
            if (contain!=null){
                liked.remove(contentId);
            }
        }
        return false;
    }

    /**
     * returns a comment
     * @param commentID the id of the comment that will be returned
     * @return          the comment
     */
    public Comment getComment(String commentID){
        Comment c=null;
        for (Vector<String> v:myComments){
            if (v.get(1).equals(commentID)) {
                if (ControlSystem.comments.get(v.get(0))!=null) {
                    ArrayList<Comment> coms = ControlSystem.comments.get(v.get(0)).get(v.get(1).split("#")[0]);
                    if (coms != null) {
                        for (Comment com : coms) {
                            if (com.getId().equals(commentID)) {
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

    /**
     * Returns a content
     * @param contentID The id of the content that will be returned
     * @return          The content
     */
    public Content getContent(String contentID){
        return ControlSystem.content.get(userID).get(contentID);
    }
}
